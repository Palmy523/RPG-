/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.event.battle;

import game.state.battle.BattleManager;
import game.state.battle.BattleState;
import graphics.combatant.CombatantNode;

/**
 *
 * @author Dave
 */
public class EnemyTurnEvent extends TurnEvent {

    private CombatantNode combatantNode;
    private boolean eventFired = false;
    
    public EnemyTurnEvent(BattleState battleState, CombatantNode node) {
        super(battleState, node);
        this.combatantNode = node;
    }
    
    @Override
    public void fireEvent() {
        this.combatantNode.getAtbGauge().clearFill();
        BattleManager.getInstance().processNPCAttack(combatantNode, this.getBattleState());
        eventFired = true;
    }

    @Override
    public boolean isAwaitingUserInput() {
        return false;
    }

    @Override
    public void setAwaitingUserInput(boolean awaiting) {
    }

    @Override
    public boolean isEventFired() {
        return eventFired;
    }

    
}
