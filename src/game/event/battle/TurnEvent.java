/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.event.battle;

import com.jme3.math.ColorRGBA;
import game.state.battle.BattleState;
import graphics.combatant.CombatantNode;

/**
 *
 * @author Dave
 */
public class TurnEvent extends BattleEvent {
    
    private CombatantNode combatantNode;
    private boolean awaitingUserInput = false;
    
    public TurnEvent(BattleState state, CombatantNode node) {
        super(state);
        this.combatantNode = node;
    }
    
    @Override
    public void fireEvent() {
        combatantNode.getCombatantGeom().getMaterial().setColor("Color", ColorRGBA.White);
    }

    public CombatantNode getCombatantNode() {
        return combatantNode;
    }

    public void setCombatantNode(CombatantNode combatantNode) {
        this.combatantNode = combatantNode;
    }

    public boolean isAwaitingUserInput() {
        return awaitingUserInput;
    }

    public void setAwaitingUserInput(boolean awaitingUserInput) {
        this.awaitingUserInput = awaitingUserInput;
    }
    
    
    
    
    
}
