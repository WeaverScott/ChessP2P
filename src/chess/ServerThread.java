package chess;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{

    private Socket socket;
    ChessModel model;
    ChessPanel panel;       // added panel to allow for board updating

    public ServerThread(Socket socket, ChessModel model, ChessPanel panel){
        this.socket = socket;
        this.model = model;
        this.panel = panel;
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

                    Move newMove = new Move(fromRow, fromCol, toRow, toCol);    // move from other client

                    // make move happen on this client
                    model.move(newMove);
                    model.setLastMove(newMove);
                    model.rookCastling(newMove);
                    model.pawnPromoted(newMove);
                    model.setNextPlayer();      // change current player to next
                    panel.displayBoard();       // update board view
                }



                //TODO: use ChessModel model to move other player's pieces on board





            }catch (IOException e){
                System.err.println("Could not get input stream");
                System.exit(-1);
            }

        }
    }

}
