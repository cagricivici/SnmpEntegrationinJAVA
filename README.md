# SnmpEntegrationinJAVA
SNMP Entegration with IP Based Devices


SnmpEntegrationforJava
SNMP v1 / 2 services are enabled on IP devices. The basic information of the camera is provided via SNMP in Java. The system information of NAS recoder device is provided via SNMP in Java.

On the PC used, Task Manager-> Service opens. Check that the SNMP Service is running.

If the PC does not have SNMP service, the Powershell application must be run in administrator mode...

Things to do:

Open Powershell with Administrator credentials

issue the following commands:

Get-WindowsCapability -Online -Name "SNMP *"

Will show Not Present

Add-WindowsCapability -Online -Name "SNMP.Client ~~~~ 0.0.1.0"

Get-WindowsCapability -Online -Name "SNMP *"

Should now show Installed

(check this link: https://answers.microsoft.com/en-us/windows/forum/all/snmp-missing-from-windows-features-in-windows-10/6a22f52b-3a88-419c-a54e-d26f18cbafee?page=1)

P.S:** If used java 1.8.212 version, this error occurs: "java.lang.UnsupportedClassVersionError: org/snmp4j/TransportMapping has been compiled by a more recent version of the Java Runtime (class file version 53.0), this version of the Java Runtime only recognizes class file versions up to 52.0". Therefore Java 1.12 version should be attached for this project...


After Compiling;

OUTPUT:
  Hardware: Intel64 Family 6 Model 60 Stepping 3 AT/AT COMPATIBLE - Software: Windows Version 6.3 (Build 9600 Multiprocessor Free)  //systemDescription
  PELCO-Q7SRR253L //systemName
  20:40:31.31 //systemuptime
  31 days, 21:13:10.42  //hardware system up time
  20:40:31.31 //last software update time
  33510324 //memory size
  2420448 //storage capacity
OUTPUT:


