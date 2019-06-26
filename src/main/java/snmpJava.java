import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class snmpJava {

    public static void  main (String[] args) throws IOException {


        TransportMapping transportMapping = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transportMapping);
        transportMapping.listen();      //Snmp and SNMP transport mapping port is ready to listen...

        CommunityTarget target = new CommunityTarget();
        target.setAddress(new UdpAddress("172.16.60.201/161")); //  snmp trap: 161. port
        target.setRetries(2);
        target.setTimeout(2000);
        target.setCommunity(new OctetString("public"));
        target.setVersion(SnmpConstants.version2c); //target/destination is defined...
                                                    //snmp version is defined...

        PDU pdu = new PDU();
        pdu.setType(PDU.GET);
        pdu.setMaxRepetitions(1);
        pdu.setNonRepeaters(0); //is a object of SNMP Class for snmp function---
                                //defined SNMP command(get,next,getBulk etc)...

        VariableBinding[] array = {
                new VariableBinding(new OID(".1.3.6.1.2.1.1.1.0")),             //systemDescription
                new VariableBinding(new OID(".1.3.6.1.2.1.1.5.0")),             //systemName
                new VariableBinding(new OID(".1.3.6.1.2.1.1.3.0")),             //systemuptime
                new VariableBinding(new OID(".1.3.6.1.2.1.25.1.1.0")),          //hardware system up time
                new VariableBinding(new OID(".1.3.6.1.2.1.25.6.2.0")),          //last software update time
                new VariableBinding(new OID(".1.3.6.1.2.1.25.2.2.0")),          //memory size
                new VariableBinding(new OID(".1.3.6.1.2.1.25.3.6.1.4.35"))      //storage capacity

        };      //OID array can be stored in VariableBinding[]

        pdu.addAll(array);  //OID numbers add into PDU Object

        ResponseEvent event = snmp.send(pdu,target);    //data is sent/received for reply message
        PDU response =  event.getResponse();            //answer or response message gets...

        if(response==null){
            System.out.println("TIMEOUT!!!");
        }else{
            if(response.getErrorStatus() == PDU.noError){                        //if without errors
                List<? extends VariableBinding> vbs = response.getVariableBindings();
                for (VariableBinding vs: vbs) {                                    //foreach
                    System.out.println(vs.getVariable().toString());               //printing all response for each OID...
                }
            }
            else{
                System.out.println("Error: "+response.getErrorStatusText());        /// if with errors
            }
        }
    }

}

//OUTPUT:
//  Hardware: Intel64 Family 6 Model 60 Stepping 3 AT/AT COMPATIBLE - Software: Windows Version 6.3 (Build 9600 Multiprocessor Free)  //systemDescription
//  PELCO-Q7SRR253L //systemName
//  20:40:31.31 //systemuptime
//  31 days, 21:13:10.42  //hardware system up time
//  20:40:31.31 //last software update time
//  33510324 //memory size
//  2420448 //storage capacity
//OUTPUT:
