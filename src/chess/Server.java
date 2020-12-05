package chess;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {

    Socket socket;
    ChessModel model;
   // String clientIP;
    Player player;
    ServerThread thread;

    public Server (int port, ChessModel model){
        System.out.println("(server begin) server has port: " + port);

        this.model = model;
        player = model.currentPlayer();

        try {
            //ServerSocket server = new ServerSocket();
            ServerSocket server = new ServerSocket(port);
            socket = server.accept();
         //   DataInputStream clientInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            thread = new ServerThread(socket, model);
            Thread t = new Thread(thread);
            t.start();



        }catch (IOException e){
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

        System.out.println("(server end constructor) Server: " + player);

       // this.run();

    }


//    public String getClientIP(){
//        return socket.getInetAddress();
//    }




    public InetAddress getClientIP() {
        System.out.println("get ip function entered");
        return socket.getInetAddress();
    }

//    public int getOtherPort(){
//        return
//    }
//
//    //private int send




   public Player getPlayer(){
        return player;
   }

}


