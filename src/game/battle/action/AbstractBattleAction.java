/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.action;

import component.battle.combatant.Combatant.AttackType;
import game.battle.graphics.CombatantNode;
import game.graphics.animation.AbstractAnimation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dave
 */
public abstract class AbstractBattleAction implements BattleAction {

    private CombatantNode actor;
    private TargetSelectionType attackType;
    private List<CombatantNode> targets;
    private boolean isProcessing;
    private AbstractAnimation moveAnimation;
    private AbstractAnimation actionAnimation;
    private AbstractAnimation recipientAnimation;
    
    public AbstractBattleAction(CombatantNode node) {
        targets = new ArrayList<>();
        actor = node;
        AttackType type = node.getCombatant().getAttackType();
        if (type == AttackType.ALL) {
            attackType = TargetSelectionType.ALL_ENEMIES;
        } else {
            attackType = TargetSelectionType.ENEMY_SINGLE;
        }
    }

    @Override
    public TargetSelectionType getTargetSelectionType() {
        return attackType;
    }

    @Override
    public void setAttackType(TargetSelectionType type) {
        this.attackType = type;
    }

    @Override
    public List<CombatantNode> getTargets() {
        return targets;
    }

    @Override
    public void setTargets(List<CombatantNode> targets) {
        this.targets = targets;
    }

    @Override
    public CombatantNode getActor() {
        return actor;
    }

    @Override
    public void setActor(CombatantNode actor) {
        this.actor = actor;
    }


    @Override
    public boolean isProcessing() {
        return isProcessing;
    }

    @Override
    public void setProcessing(boolean processing) {
        this.isProcessing = processing;
    }

    @Override
    public AbstractAnimation getMoveAnimation() {
        return moveAnimation;
    }

    @Override
    public void setMoveAnimation(AbstractAnimation animation) {
        this.moveAnimation = animation;
    }

    @Override
    public AbstractAnimation getActionAnimation() {
        return actionAnimation;
    }

    @Override
    public void setActionAnimation(AbstractAnimation animation) {
        this.actionAnimation = animation;
    }

    @Override
    public AbstractAnimation getRecipientAnimation(AbstractAnimation animation) {
        return this.recipientAnimation;
    }

    @Override
    public void setRecipientAnimation(AbstractAnimation animation) {
        this.recipientAnimation = animation;
    }
    
    

    
    
    
}
