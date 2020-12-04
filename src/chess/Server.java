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

    public Server (int port, ChessModel model){
        System.out.print("(server begin) server has port: " + port);

        this.model = model;
        player = model.currentPlayer();

        try {
            //ServerSocket server = new ServerSocket();
            ServerSocket server = new ServerSocket(port);
            socket = server.accept();
         //   DataInputStream clientInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));



        }catch (IOException e){
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

        System.out.println("(server end) Server: " + player);

       // this.run();

    }


//    public String getClientIP(){
//        return socket.getInetAddress();
//    }

    private void run (){
        while(true){
            String moveIn;
            try {
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                moveIn = inFromClient.readLine();
                int fromRow = Character.getNumericValue(moveIn.charAt(0));
                int fromCol = Character.getNumericValue(moveIn.charAt(1));
                int toRow = Character.getNumericValue(moveIn.charAt(2));
                int toCol = Character.getNumericValue(moveIn.charAt(3));

                // Move move = new Move(fromRow,fromCol,toRow,toCol);
                // we need a way for the server class to communicate with the Chessmodel object intstantiated in ChessPanel
                //i think we should have Chesspanel instantiate server and client and pass chess model to server
                //while chesspanel calls a client method everytime the local player moves a piece but idk this is just the skeleton code




            }catch (IOException e){
                System.err.println("Could not get input stream");
                System.exit(-1);
            }




        }
    }


    public InetAddress getClientIP() {
        System.out.print("get ip function entered");
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
