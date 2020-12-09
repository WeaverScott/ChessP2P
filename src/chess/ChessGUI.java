package chess;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**********************************************************************
 * A class that creates the GUI. 
 *
 * @author Christian Thompson, James Weitzmanm, Josh Hubbard, 
 *         Lauren Vanderklok, & Scott Weaver
 *********************************************************************/
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

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {

                Object[] buttons = {"Yes", "No"};

                int result = JOptionPane.showOptionDialog(null,
                        "Do you want to save the game?", null,
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, buttons, buttons[0]);

                if (result == JOptionPane.YES_OPTION){
                    panel.saveGame();
                }

                super.windowClosing(windowEvent);
                panel.close();
                System.exit(0);
            }
        });

    }
}