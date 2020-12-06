package chess;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    Socket socket;

    int port;

    //version of the constructor for when were joining a game, the only difference is the type of IPaddr
    public Client (int port, String IPaddr){
        this.port = port;

        try {
            socket = new Socket(IPaddr, port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

    }

    //version of the constructor for when were starting a new game
    public Client (int port, InetAddress IPaddr){
        this.port = port;

        try {
            socket = new Socket(IPaddr, port);

        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

    }

    //call this in ChessPanel to send the local player's move to the other player
    public void sendToOtherPlayer(int fromRow, int fromColumn, int toRow, int toColumn){

        String moveOut = Integer.toString(fromRow) + Integer.toString(fromColumn) + Integer.toString(toRow) + Integer.toString(toColumn);

        try {
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            outToServer.writeUTF(moveOut);

            outToServer.writeUTF("eof"); //lets server know message is over
        } catch (IOException e){
            System.err.println("Could not get output stream");
            System.exit(-1);
        }
    }


}
