/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.error;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Dave
 */
public class GameException extends Exception {
    
    public GameException() {
        this("GameException occured.");
    }
    
    public GameException(String message) {
        super(message);
        displayErrorScreen();
    }
    
    private void displayErrorScreen() {
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setTitle("GameException");
        frame.setSize(new Dimension(300, 200));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, "A Game Exception has occured" + this.getMessage());
    }
    
    
    
}
