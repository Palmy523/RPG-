/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import configuration.GameState;
import java.awt.Dialog;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dave
 */
public class StartMenu {
    
    private GameState state;
    private JFrame frame = new JFrame();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JTextField name1 = new JTextField();
    private JTextField health1 = new JTextField();
    private JTextField atk1 = new JTextField();
    private JTextField def1 = new JTextField();
    private JTextField spd1 = new JTextField();
    
    public StartMenu() {
        state = new GameState();
        frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(600, 800));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        panel1.add(name1);
        panel1.add(health1);
        
    }
    
}
