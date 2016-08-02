/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.event;

import game.battle.graphics.CombatantNode;
import game.event.GameEvent;

/**
 *
 * @author Dave
 */
public class CombatantDeathEvent implements GameEvent {
    
    private CombatantNode combatantNode;
    
    public CombatantDeathEvent() {
        
    }

    public CombatantNode getCombatantNode() {
        return combatantNode;
    }

    public void setCombatantNode(CombatantNode node) {
        this.combatantNode = node;
    }
    
}
