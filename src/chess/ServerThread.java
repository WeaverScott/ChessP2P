package chess;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**********************************************************************
 * A class that send information to and from the server.
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver 
 *********************************************************************/
public class ServerThread extends Thread{

    /** The socket that the serverthread is connecting to */
    private Socket socket;

    /** Model of the game */
    ChessModel model;

    /** Panel added to allow for board updating */
    ChessPanel panel; 

    //initializes the server thread
    public ServerThread(Socket socket, ChessModel model, ChessPanel panel){
        this.socket = socket;
        this.model = model;
        this.panel = panel;
    }

    /******************************************************************
     * Main method for running the game 
     *****************************************************************/
    public void run (){

        while(true){

            try {
                DataInputStream inFromClient = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                int fromRow = 0;
                int fromCol = 0;
                int toRow = 0;
                int toCol = 0;
                String promotion = null;
                boolean undo = false;
                ArrayList<String> board = new ArrayList<String>();
                boolean nextCommandIsLoading = false;
                
                while(true){

                    String command = inFromClient.readUTF();

                    System.out.println("got: " + command);

                    if (command.equals("eof")){
                        break;
                    }else if (nextCommandIsLoading){
                        board.add(command);
                    }else if (command.equals("undo")) { //undo has been opted out of
                        undo = true;
                    }
                    else if (command.equals("loading")){
                        nextCommandIsLoading = true;
                    } else {
                        if (Character.isDigit(command.charAt(0))){
                            fromRow = Character.getNumericValue(command.charAt(0));
                            fromCol = Character.getNumericValue(command.charAt(1));
                            toRow = Character.getNumericValue(command.charAt(2));
                            toCol = Character.getNumericValue(command.charAt(3));
                        } else {
                            promotion = command;
                        }
                    }
                }

                if (!undo) {
                    if (nextCommandIsLoading){
                        model.setLoadedBoard(board);
                    }else {
                        //move from other client
                        Move newMove = new Move(fromRow, fromCol, toRow, toCol);    

                        //make move happen on this client
                        panel.saveThisState();
                        model.move(newMove);
                        model.setLastMove(newMove);
                        model.rookCastling(newMove);

                        if (promotion != null) {
                            model.pawnPromoted(newMove, promotion);
                        } else {
                            model.move(newMove);
                        }
                        //change current player to next
                        model.setNextPlayer();      
                        System.out.println(model.currentPlayer());
                    }
                } else {
                    panel.undoStuff();
                    System.out.println(model.currentPlayer());
                }
                //update board view
                panel.displayBoard();       

            }catch (SocketException e){
                System.exit(0);
            }catch (IOException e) {
                System.err.println("Could not get input stream");
                System.err.println(e);
                System.exit(-1);
            }
        }
    }
}
