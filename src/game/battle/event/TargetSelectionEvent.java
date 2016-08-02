/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.event;

import game.battle.action.BattleAction;
import game.battle.action.BattleAction.TargetSelectionType;
import game.event.GameEvent;

/**
 *
 * @author Dave
 */
public class TargetSelectionEvent implements GameEvent {
    
    
    private BattleAction action;
    private TargetSelectionType selectionType;
    
    public TargetSelectionEvent() {
        
    }

    public TargetSelectionType getTargetSelectionType() {
        return selectionType;
    }

    public void setTargetSelectionType(TargetSelectionType selectionType) {
        this.selectionType = selectionType;
    }

    public BattleAction getAction() {
        return action;
    }

    public void setAction(BattleAction action) {
        this.action = action;
    }

}
