/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.event;

import game.battle.BattleState;
import game.battle.graphics.CombatantNode;
import game.event.GameEvent;

/**
 *
 * @author Dave
 */
public class OnTurnEvent implements GameEvent {
    
    private CombatantNode combatantNode;
    
    public OnTurnEvent() {
    }

    public CombatantNode getCombatantNode() {
        return combatantNode;
    }

    protected void setCombatantNode(CombatantNode combatantNode) {
        this.combatantNode = combatantNode;
    }
    
}
