package chess;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {

    Socket socket;
    ChessModel model;
    String clientIP;
    Player player;

    public Server (int port, ChessModel model){
        System.out.print("server has port: " + port);

        this.model = model;
        player = model.currentPlayer();

        try {
            ServerSocket server = new ServerSocket(port);
            socket = server.accept();
            DataInputStream clientInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
           // String message;
//            while (true) {
//                String message = clientInput.readUTF();
//                if (message.equals("eof")) {
//                    break;
//                }
//
//
//               // StringTokenizer tokens = new StringTokenizer(message);
//                ///String player = tokens.nextToken();
//
////                if (player.equals("BLACK")){
////                    player = Player.BLACK.toString();
////                }else{
////                    player = Player.WHITE.toString();
////                }
//
//                clientIP = message;
//                        //tokens.nextToken();
//
//
//            }



        }catch (IOException e){
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

        System.out.println("Server: " + player + clientIP);

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


//    public String getClientIP(){
//        return socket.getInetAddress();
//    }

    public InetAddress getClientIP() {
        return socket.getInetAddress();
    }

   public Player getPlayer(){
        return player;
   }

}
