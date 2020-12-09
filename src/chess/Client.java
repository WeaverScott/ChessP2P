package chess;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**********************************************************************
 * A class that interacts with the server. 
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
public class Client {

    /** The socket that the client is connecting to*/
    Socket socket;

    /** The port that the client is sending information from */
    int port;

    /******************************************************************
     * A version of the constructor for when were were joining a game, 
     * the only difference is the type of IPaddr
     *****************************************************************/
    public Client (int port, String IPaddr){
        this.port = port;

        try {
            socket = new Socket(IPaddr, port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

    }

    /******************************************************************
     * A version of the constructor for when were starting a new game
     *****************************************************************/
    public Client (int port, InetAddress IPaddr){
        this.port = port;

        try {
            socket = new Socket(IPaddr, port);

        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

    }

    /******************************************************************
     * A method called in ChessPanel to send the local player's move 
     * to the other player.
     *****************************************************************/
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

    /******************************************************************
     * A method called in ChessPanel to send the local player's move 
     * and choice of promotion to the other player.
     *****************************************************************/
    public void sendToOtherPlayer(int fromRow, int fromColumn, int toRow, int toColumn, String promotion){
        String moveOut = Integer.toString(fromRow) + Integer.toString(fromColumn) + Integer.toString(toRow) + Integer.toString(toColumn);
        try {
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            outToServer.writeUTF(moveOut);
            outToServer.writeUTF(promotion);
            outToServer.writeUTF("eof"); //lets server know message is over
        } catch (IOException e){
            System.err.println("Could not get output stream");
            System.exit(-1);
        }

    }

    /******************************************************************
     * A method used for undoing moves.
     *****************************************************************/
    public void sendToOtherPlayer() {

        try {
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            outToServer.writeUTF("undo");
            outToServer.writeUTF("eof"); //lets server know message is over
        } catch (IOException e){
            System.err.println("Could not get output stream");
            System.exit(-1);
        }
    }
 
     /******************************************************************
     * A method that sends the pieces to the board.
     *****************************************************************/
    public void sendPiece(ArrayList <String> board){
        try {
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            outToServer.writeUTF("loading");
            for (String tile: board) {
                outToServer.writeUTF(tile);
            }
            //.writeUTF(location);
            outToServer.writeUTF("eof"); //lets server know message is over
        } catch (IOException e){
            System.err.println("Could not get output stream");
            System.exit(-1);
        }
    }

    /******************************************************************
     * A method that closes the game for the client.
     *****************************************************************/
    public void close(){
        try{
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            outToServer.writeUTF("close");
            outToServer.flush();
            outToServer.close();
            socket.close();
            System.exit(0);
        }catch(Exception e){
            System.err.println(e);
            System.err.println("unable to close");
        }
    }



}
