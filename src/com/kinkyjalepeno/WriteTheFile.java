package com.kinkyjalepeno;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class WriteTheFile {

    private int serviceInterface;
    private int portGroupName;
    private int subnetPrefixLength;
    private String gatewayIpAddress;
    private String signallingAddress;
    private String mediaAddress;
    private String serviceAddress;
    private String customerIPAddress;
    private int vlanId;
    private String realm;
    private String adjacencyName;
    private int accountPort;
    private int customerPrefixLength;


    public WriteTheFile(int serviceInterface, int portGroupName, int subnetPrefixLength, String gatewayIpAddress, String signallingAddress,
                        String mediaAddress, String serviceAddress, int vlanId, String realm, String adjacencyName, int accountPort, String customerIPAddress, int customerPrefixLength) {


        this.serviceInterface = serviceInterface;
        this.portGroupName = portGroupName;
        this.subnetPrefixLength = subnetPrefixLength;
        this.gatewayIpAddress = gatewayIpAddress;
        this.signallingAddress = signallingAddress;
        this.mediaAddress = mediaAddress;
        this.serviceAddress = serviceAddress;
        this.vlanId = vlanId;
        this.realm = realm;
        this.adjacencyName = adjacencyName;
        this.accountPort = accountPort;
        this.customerIPAddress = customerIPAddress;
        this.customerPrefixLength = customerPrefixLength;


    }

    void writeScript() {

        Scanner sc = new Scanner(System.in);

        System.out.print("The script is ready to write - What shall I name it?: ");

        String scriptTitle = sc.nextLine();

        File file = new File(scriptTitle + ".txt");


        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {


            br.write("*********** Script for " + scriptTitle + "***********\n\n");

            br.write("config\n");
            br.write("system\n");
            br.write("service-interface serv" + serviceInterface + "\n");
            br.write("service-network " + serviceInterface + "\n");
            br.write("port-group-name PortGroup" + portGroupName + "\n");
            br.write("ipv4" + "\n");
            br.write("subnet-prefix-length " + subnetPrefixLength + "\n");
            br.write("gateway-ip-address " + gatewayIpAddress + "\n");
            br.write("local-ip-address " + signallingAddress + "\n");
            br.write("service-address " + serviceAddress + "\n\n");
            br.write("end\n\n\n");

            br.write("config\n");
            br.write("system\n");
            br.write("service-interface serv" + serviceInterface + "\n");
            br.write("ipv4" + "\n");
            br.write("local-ip-address " + mediaAddress + "\n");
            br.write("probes-source-style specific-source" + "\n");
            br.write("no activate " + "\n");
            br.write("vlan-id " + vlanId + "\n");
            br.write("network-security trusted" + "\n");
            br.write("criticality 1000" + "\n\n");
            br.write("end\n\n\n");

            br.write("config\n");
            br.write("sbc\n");
            br.write("media" + "\n");
            br.write("media-address ipv4 " + mediaAddress + " service-network " + serviceInterface + "\n");
            br.write("realm " + realm + "\n\n");
            br.write("end\n\n\n");

            // Adjacency data set
            br.write("config\n");
            br.write("sbc\n");
            br.write("signaling" + "\n");
            br.write("adjacency sip " + adjacencyName + "\n");
            br.write("deactivation-mode normal" + "\n");
            br.write("account Port" + accountPort + "\n");
            br.write("call-media-policy" + "\n");
            br.write("media-bypass-policy forbid" + "\n");
            br.write("interop " + "\n");
            br.write("message-manipulation " + "\n");
            br.write("edit-profiles outbound addAllowUPDATE" + "\n");
            br.write("force-signaling-peer all-requests\n");
            br.write("adjacency-type preset-peering\n");
            br.write("privacy trusted" + "\n");
            br.write("realm " + realm + "\n");
            br.write("service-address " + serviceAddress + "\n");
            br.write("signaling-local-port 5060" + "\n");
            br.write("remote-address-range ipv4 " + customerIPAddress + " prefix-len " + customerPrefixLength + "\n");
            br.write("signaling-peer " + customerIPAddress + "\n");
            br.write("dynamic-routing-domain-match " + customerIPAddress + "\n");
            br.write("signaling-peer-port 5060" + "\n");
            br.write("statistics-setting detail" + "\n");
            br.write("default-interop-profile Peer " + "\n");
            br.write("no activate " + "\n\n");
            br.write("end\n\n\n");

            System.out.println("THE FILL HAS BEEN WRITTEN\n\n\n");


        } catch (IOException e) {
            System.err.print("\nUnable to write file " + file.toString());
        }


    }

}
