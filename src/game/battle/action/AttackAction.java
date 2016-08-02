/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.action;

import component.battle.combatant.Combatant.AttackType;
import game.battle.BattleManager;
import game.battle.graphics.CombatantNode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dave
 */
public class AttackAction implements BattleAction {

    private CombatantNode actor;
    private TargetSelectionType attackType;
    private List<CombatantNode> targets;
    
    public AttackAction(CombatantNode node) {
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

    public List<CombatantNode> getTargets() {
        return targets;
    }

    public void setTargets(List<CombatantNode> targets) {
        this.targets = targets;
    }
    
    @Override
    public void processAction() {
        for (CombatantNode target : targets) {
            BattleManager.getInstance().attack(actor, target);
        }
    }

    public CombatantNode getActor() {
        return actor;
    }

    public void setActor(CombatantNode actor) {
        this.actor = actor;
    }
    
    

    
    
    
}
