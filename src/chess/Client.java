package chess;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    Socket socket;
    //Player player;
    int port;
    //String ServerstringIPaddr;
   // InetAddress ServerIPaddr;

    public Client (int port, String IPaddr){
        this.port = port;
       // this.player = player;
       // ServerIPaddr = IPaddr;
        System.out.println("client begin (joiner version)");






        try {
            socket = new Socket(IPaddr, port);
//            String message = Inet4Address.getLocalHost();
//            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
//            outToServer.writeUTF(message);
//            outToServer.writeUTF("eof");
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

        //send current player color

        System.out.print("(client end) client has: " + port + " " + IPaddr);

    }

    public Client (int port, InetAddress IPaddr){
        this.port = port;
        // this.player = player;
        //ServerIPaddr = IPaddr;

        System.out.println("client begin (starter version)");




        try {
            socket = new Socket(IPaddr, port);
//            String message = Inet4Address.getLocalHost();
//            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
//            outToServer.writeUTF(message);
//            outToServer.writeUTF("eof");
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

        //send current player color

        System.out.print(" (cleint end) client has: " + port + " " + IPaddr);


    }


    public void sendToOtherPlayer(int fromRow, int fromColumn, int toRow, int toColumn){

        String moveOut = Integer.toString(fromRow) + Integer.toString(fromColumn) + Integer.toString(toRow) + Integer.toString(toColumn);
        try {
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            outToServer.writeUTF(moveOut);
        }catch (IOException e){
            System.err.println("Could not get output stream");
            System.exit(-1);
        }
    }


}
