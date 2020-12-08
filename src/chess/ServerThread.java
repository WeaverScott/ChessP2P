package chess;

import java.io.*;
import java.net.Socket;

/**********************************************************************
 * A class that sends information to and from the server. 
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
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
                int fromRow = 0;
                int fromCol = 0;
                int toRow = 0;
                int toCol = 0;
                String promotion = null;
                while(true){
                    String command = inFromClient.readUTF();

                   // System.out.println(command);

                    if (command.equals("eof")){
                        break;
                    }

                    if (Character.isDigit(command.charAt(0))){
                        fromRow = Character.getNumericValue(command.charAt(0));
                        fromCol = Character.getNumericValue(command.charAt(1));
                        toRow = Character.getNumericValue(command.charAt(2));
                        toCol = Character.getNumericValue(command.charAt(3));

                    }
                    else {
                        promotion = command;
                    }

                }


                Move newMove = new Move(fromRow, fromCol, toRow, toCol);    // move from other client

                // make move happen on this client
                model.move(newMove);
                model.setLastMove(newMove);
                model.rookCastling(newMove);

               
                if (promotion != null){
                    model.pawnPromoted(newMove, promotion);
                }else{
                    model.pawnPromoted(newMove);
                }
                //model.pawnPromoted(newMove);
                model.setNextPlayer();      // change current player to next
                panel.displayBoard();       // update board view







            }catch (IOException e){
                System.err.println("Could not get input stream");
                System.exit(-1);
            }

        }
    }

}
