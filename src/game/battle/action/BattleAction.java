/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.action;

import game.battle.graphics.CombatantNode;
import game.graphics.animation.AbstractAnimation;
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
    public boolean isProcessing();
    public void setProcessing(boolean processing);
    public AbstractAnimation getMoveAnimation();
    public void setMoveAnimation(AbstractAnimation animation);
    public AbstractAnimation getActionAnimation();
    public void setActionAnimation(AbstractAnimation animation);
    public AbstractAnimation getRecipientAnimation(AbstractAnimation animation);
    public void setRecipientAnimation(AbstractAnimation animation);
    
}
