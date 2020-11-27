package Chess;

import javax.swing.*;
import java.awt.*;

/**
 * GUI for the game
 */

public class ChessGUI {

    public static void main(String[] args) {
        int port = 7777;
        int player = 0;
        //0 is white 1 is black
        String IPaddr = "localhost";
        JFrame frame = new JFrame("Chess Game");    // instantiate new JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // close window on clicking "x" in corner of window

        ChessPanel panel = new ChessPanel();    // instantiate ChessPanel to display game contents in the frame
        frame.getContentPane().add(panel);      // add panel to frame

        frame.setResizable(true);   // allow frame to be resizable
        frame.setPreferredSize(new Dimension(800, 637));    // set initial size of fram
        frame.pack();   // pack contents of frame
        frame.setVisible(true); // make frame visible
        frame.setLocationRelativeTo(null);  // place frame in the center of user's screen

        Client client = new Client (port, player, IPaddr);
        Server server = new Server (port, player);

    }
}
