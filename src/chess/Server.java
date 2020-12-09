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

    Socket socket;
    ChessModel model;
    ChessPanel panel;       // panel added to allow for board updating
    Player player;
    ServerThread thread;

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

    public InetAddress getOtherPlayerIP() {
        return socket.getInetAddress();
    }

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


