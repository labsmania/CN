import java.io.*;
import java.net.*;

class Ser {
    public static void main(String args[]) throws Exception {
       
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] sendBuffer = new byte[1024];
        byte[] receiveBuffer = new byte[1024];

        System.out.println("Server is running...");

        while (true) {
           
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            serverSocket.receive(receivePacket);

            InetAddress clientIP = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            String clientData = new String(receivePacket.getData(), 0, receivePacket.getLength()); 
            System.out.println("Client: " + clientData);

            System.out.print("Server: ");
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(System.in));
            String serverData = serverReader.readLine();
            sendBuffer = serverData.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientIP, clientPort);
            serverSocket.send(sendPacket);
        }
    }
}
