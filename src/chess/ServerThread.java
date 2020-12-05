package chess;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{

    private Socket socket;
    ChessModel model;

    public ServerThread(Socket socket, ChessModel model){
        this.socket = socket;
        this.model = model;
    }

    public void run (){
        System.out.println("entered run method in thread");
        System.out.println("this is just to prove that the most recent version of Serverthread is being used 0");
        while(true){
            String moveIn;
            try {
                DataInputStream inFromClient = new DataInputStream(new BufferedInputStream(socket.getInputStream()));


                while(true){
                    moveIn = inFromClient.readUTF();
                    System.out.println("server thread got: " + moveIn);
                    if (moveIn.equals("eof")){
                        break;
                    }


                    int fromRow = Character.getNumericValue(moveIn.charAt(0));
                    int fromCol = Character.getNumericValue(moveIn.charAt(1));
                    int toRow = Character.getNumericValue(moveIn.charAt(2));
                    int toCol = Character.getNumericValue(moveIn.charAt(3));
                }



                //TODO: use ChessModel model to move other player's pieces on board





            }catch (IOException e){
                System.err.println("Could not get input stream");
                System.exit(-1);
            }




        }
    }


}
