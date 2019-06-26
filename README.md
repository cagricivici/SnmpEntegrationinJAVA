# SnmpEntegrationinJAVA
SNMP Entegration with IP Based Devices


SnmpEntegrationforJava
SNMP v1 / 2 services are enabled on IP devices. The basic information of the camera is provided via SNMP in Java.

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

(Next step:) Snmp trap will be obtained from all cameras without waiting for the cameras' shakehands with ssh/tcp ports.(SYSTEM ENTEGRATION)
