/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.event.battle;

import game.state.battle.BattleState;
import graphics.combatant.CombatantNode;

/**
 *
 * @author Dave
 */
public abstract class TurnEvent extends BattleEvent {
    
    private CombatantNode combatantNode;
    
    public TurnEvent(BattleState state, CombatantNode combatantNode) {
        super(state);
        this.combatantNode = combatantNode;
    }

    public CombatantNode getCombatantNode() {
        return combatantNode;
    }

    public void setCombatantNode(CombatantNode combatantNode) {
        this.combatantNode = combatantNode;
    }
    
    public abstract boolean isAwaitingUserInput();
    
    public abstract void setAwaitingUserInput(boolean awaiting);
    
    public abstract boolean isEventFired();
    
}
