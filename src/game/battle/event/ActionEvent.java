/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.event;

import game.battle.action.BattleAction;
import game.battle.graphics.CombatantNode;
import game.event.GameEvent;
import java.util.List;

/**
 *
 * @author Dave
 */
public class ActionEvent implements GameEvent{
    
    private CombatantNode actor;
    private BattleAction action;
    private List<CombatantNode> targets;
    
    public void ActionEvent() {
        
    }

    public CombatantNode getActor() {
        return actor;
    }

    public void setActor(CombatantNode combatant) {
        this.actor = combatant;
    }

    public BattleAction getAction() {
        return action;
    }

    public void setAction(BattleAction action) {
        this.action = action;
    }

    public List<CombatantNode> getTargets() {
        return targets;
    }

    public void setTargets(List<CombatantNode> targets) {
        this.targets = targets;
    }
    
    
    
    
    
    
}
