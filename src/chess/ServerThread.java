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

        while(true){

            try {
                DataInputStream inFromClient = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

                while(true){
                    String moveIn = inFromClient.readUTF();
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
