package com.kinkyjalepeno;


import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        Scanner sc = new Scanner(System.in);

        // The below are for the service interface and media binding
        while (true) {
            int serviceInterface;
            int portGroupName;
            int subnetPrefixLength;
            int vlanId;

            String gatewayIpAddress;
            String signallingAddress;
            String mediaAddress;
            String serviceAddress;
            String customerIPAddress;
            String realm;

            String quit;

            String adjacencyName;
            int accountPort;

            System.out.println("***************************************************");
            System.out.println("**************NuWave Script Generator**************");
            System.out.println("***********Created by Dave G - June 2022***********");
            System.out.println("\b\b");
            System.out.println("Please answer the questions below regarding the \b");
            System.out.println("variables required to generate the scripts.");
            System.out.println("\b\b");
            System.out.println("The next 6 questions will cover the Service Interface and media binding.");
            System.out.println("A couple more questions will follow for the Adjacency but you will be notified.");
            System.out.println("\b\b");

            System.out.print("What is the service interface number: ");
            serviceInterface = sc.nextInt();

            System.out.print("Port-Group-Name - PortGroup(1-4): ");
            portGroupName = sc.nextInt();

            System.out.print("Subnet Prefix length: ");
            subnetPrefixLength = sc.nextInt();

            System.out.print("Nasstar Gateway IP Address: ");
            gatewayIpAddress = sc.next();

            System.out.print("Nasstar Local / Signalling IP Address: ");
            signallingAddress = sc.next();

            System.out.print("Nasstar Media Ip Address: ");
            mediaAddress = sc.next();

            System.out.print("Service Address Label(example - NuWaveSigOutbound-VID1104: ");
            serviceAddress = sc.next();

            System.out.print("VLAN ID: ");
            vlanId = sc.nextInt();

            System.out.print("Realm Label(example - NuWaveOutbound-VID1104): ");
            realm = sc.next();

            System.out.println("\b\b");
            System.out.println("***********************************");
            System.out.println("Couple more for the Adjacency now:-");
            System.out.println("***********************************");
            System.out.println("\n");

            System.out.print("Adjacency SIP Label(example - ANG-NuWaveT1Outbound-VID1104):");
            adjacencyName = sc.next();

            System.out.print("Account Port (in the format 34 for 3 and 4): ");
            accountPort = sc.nextInt();

            System.out.print("What is the IP of the customer end: ");
            customerIPAddress = sc.next();

            // Time to send all data to the file writing class

            WriteTheFile write = new WriteTheFile(serviceInterface, portGroupName, subnetPrefixLength, gatewayIpAddress, signallingAddress, mediaAddress, serviceAddress,
                    vlanId, realm, adjacencyName, accountPort, customerIPAddress);

            write.writeScript();

            System.out.print("Would you like to run this again for another script?  Y/N: ");
            quit = sc.next();
            System.out.println("\n\n");

            if (quit.equals("n") || quit.equals("N")) {
                break;
            }
        }
        sc.close();


    }
}