/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.event.battle;

import game.event.GameEvent;
import game.state.battle.BattleState;

/**
 *
 * @author Dave
 */
public abstract class BattleEvent implements GameEvent {
    
    private BattleState battleState;
    private boolean processing = false;
    
    public BattleEvent(BattleState battleState) {
        this.battleState = battleState;
    }

    public BattleState getBattleState() {
        return battleState;
    }

    public void setBattleState(BattleState battleState) {
        this.battleState = battleState;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setIsProcessing(boolean isProcessing) {
        this.processing = isProcessing;
    }
    
    
}
