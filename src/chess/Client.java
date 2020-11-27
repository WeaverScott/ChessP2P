package chess;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    Socket socket;
    Player player;
    int port;
    String IPaddr;

    public Client (int port, String IPaddr){
        this.port = port;
       // this.player = player;
        this.IPaddr = IPaddr;

        System.out.print("client has: " + port + " " + player + " " + IPaddr);



        try {
            socket = new Socket(IPaddr, port);
            String message = player + " " + IPaddr;
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            outToServer.writeUTF(message);
            outToServer.writeUTF("eof");
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

        //send current player color



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
