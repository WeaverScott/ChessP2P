package Chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    Socket socket;

    public Server (int port, int player){
        System.out.print("server has: " + port + " " + player);

        try {
            ServerSocket server = new ServerSocket(port);
            socket = server.accept();



        }catch (IOException e){
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }

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

}
