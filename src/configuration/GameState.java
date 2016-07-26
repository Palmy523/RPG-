/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import combatant.component.Combatant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dave
 */
public class GameState {
    private List<Combatant> enemies;
    private List<Combatant> allies;
    
    public GameState() {
        enemies = new ArrayList<>();
        allies = new ArrayList<>();
    }

    public List<Combatant> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Combatant> enemies) {
        this.enemies = enemies;
    }

    public List<Combatant> getAllies() {
        return allies;
    }

    public void setAllies(List<Combatant> allies) {
        this.allies = allies;
    }
    
    
    
    
    
    
}
