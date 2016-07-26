/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import combatant.component.Combatant;
import java.util.List;
import javax.swing.table.DefaultTableColumnModel;

/**
 *
 * @author Dave
 */
public class PlayerTableModel extends DefaultTableColumnModel {

    private List<Combatant> combatants;
    
    public PlayerTableModel(List<Combatant> combatants) {
        this.combatants = combatants;
    }
    
    
    
   
}
