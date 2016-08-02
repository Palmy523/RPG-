/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.event;

import game.battle.action.BattleAction;
import game.battle.graphics.CombatantNode;
import game.event.GameEvent;

/**
 *
 * @author Dave
 */
public class ReceivedActionEvent implements GameEvent {
    
    private BattleAction action;
    private CombatantNode combatantNode;
    
    public ReceivedActionEvent() {
        
    }

    public BattleAction getAction() {
        return action;
    }

    public void setAction(BattleAction action) {
        this.action = action;
    }

    public CombatantNode getCombatantNode() {
        return combatantNode;
    }

    public void setCombatantNode(CombatantNode node) {
        this.combatantNode = node;
    }
    
    
}
