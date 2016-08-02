/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.action;

import game.battle.graphics.CombatantNode;
import java.util.List;

/**
 *
 * @author Dave
 */
public interface BattleAction {
    public static enum TargetSelectionType {

        ALLY_SINGLE, ALLY_MULTIPLE, ENEMY_SINGLE,
        ENEMY_MULTIPLE, ALL_ENEMIES, ALL_ALLIES, ALL
    
    }
    
    public TargetSelectionType getTargetSelectionType();
    public void setAttackType(TargetSelectionType type);
    public CombatantNode getActor();
    public void setActor(CombatantNode actor);
    public void setTargets(List<CombatantNode> targets);
    public List<CombatantNode> getTargets();
    public void processAction();
    
}
