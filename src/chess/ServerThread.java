package chess;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

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
                boolean undo = false;
                ArrayList<String> board = new ArrayList<String>();
                boolean nextCommandIsLoading = false;
               // boolean loadingBoard = false;
                while(true){

                    String command = inFromClient.readUTF();

                    System.out.println("got: " + command);

                    if (command.equals("eof")){
                        break;
                    }else if (nextCommandIsLoading){
                        board.add(command);
                        //nextCommandIsLoading = false;
                    }else if (command.equals("undo")) {
                        undo = true;
                    }
                    else if (command.equals("loading")){
                        nextCommandIsLoading = true;
                    } else {
                     //   undo = false;
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
                        Move newMove = new Move(fromRow, fromCol, toRow, toCol);    // move from other client

                        // make move happen on this client
                        panel.saveThisState();
                        model.move(newMove);
                        model.setLastMove(newMove);
                        model.rookCastling(newMove);


                        if (promotion != null) {
                            model.pawnPromoted(newMove, promotion);
                        } else {
                            model.pawnPromoted(newMove);
                        }
                        //model.pawnPromoted(newMove);
                        model.setNextPlayer();      // change current player to next
                        System.out.println(model.currentPlayer());
                    }
                } else {
                    panel.undoStuff();
                    System.out.println(model.currentPlayer());
                }
                panel.displayBoard();       // update board view




                //undo = false;




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
