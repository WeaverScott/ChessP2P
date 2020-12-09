package chess;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**********************************************************************
 * A class that acts as the server. 
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
public class Server {

    /** The socket that the server is running on*/
    Socket socket;

    /** Model of the game */
    ChessModel model;

    /** Panel added to allow for board updating */
    ChessPanel panel;

    /** A player */
    Player player;

    /**The thread that the game is running on */
    ServerThread thread;

    /******************************************************************
     * Server constructor for starting the game.
     *****************************************************************/
    public Server (int port, ChessModel model, ChessPanel panel){

        this.model = model;
        this.panel = panel;
        player = model.currentPlayer();

        try {

            ServerSocket server = new ServerSocket(port);
            socket = server.accept();

            //starts another thread so the server can listen for info from other player
            thread = new ServerThread(socket, model, panel);
            Thread t = new Thread(thread);
            t.start();


        }catch (IOException e){
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }
    }

    /******************************************************************
     * Helper method to get the IP address from the other player.
     *****************************************************************/
    public InetAddress getOtherPlayerIP() {
        return socket.getInetAddress();
    }

    /******************************************************************
     * A method used to clode the game being hosted by the server.
     *****************************************************************/
    public void close(){
        try{
            thread.interrupt();
            socket.close();
            System.exit(0);
        }catch (Exception e){
            System.err.println(e);
            System.err.println("unable to close");
        }
    }
}


