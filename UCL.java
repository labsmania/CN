import java.io.*;
import java.net.*;

class UCL {
    public static void main(String args[]) throws Exception {
   
        BufferedReader clientRead = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("127.0.0.1"); 

        byte[] sendBuffer = new byte[1024];
        byte[] receiveBuffer = new byte[1024];
        System.out.println("Client side");

        System.out.print("Enter message: ");
        String clientData = clientRead.readLine();
        sendBuffer = clientData.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ip, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        clientSocket.receive(receivePacket);

        String serverData = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Server: " + serverData);

        clientSocket.close();
    }
}
