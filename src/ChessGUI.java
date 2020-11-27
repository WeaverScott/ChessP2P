package chess;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ChessGUI {

    /*****************************************************************
     * A main method that displays the chess board and pieces.
     * @param args arguments
     *****************************************************************/
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(900, 637));
        frame.pack();
        frame.setVisible(true);
    }
}

//end of class