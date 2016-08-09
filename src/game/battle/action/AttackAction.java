/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.action;

import com.jme3.math.Vector3f;
import component.battle.combatant.Combatant;
import game.battle.BattleManager;
import game.battle.BattleState;
import game.battle.graphics.CombatantNode;
import game.battle.graphics.animation.MoveAnimationVector;

/**
 *
 * @author Dave
 */
public class AttackAction extends AbstractBattleAction {

    public AttackAction(CombatantNode actor) {
        super(actor);
        Vector3f actorPosition = actor.getStartingPosition();
        Vector3f movementVector;
        if (actor.getCombatant().getType() == Combatant.CombatantType.Enemy) {
            movementVector = new Vector3f(actorPosition.x + 2.0f, actorPosition.y, actorPosition.z);
        } else {
            movementVector = new Vector3f(actorPosition.x - 2.0f, actorPosition.y, actorPosition.z);
        }
        MoveAnimationVector moveAnimation = new MoveAnimationVector(actor, movementVector);
        this.setMoveAnimation(moveAnimation);
        this.setActionAnimation(actor.getAttackAnimation());
    }
    
    @Override
    public void processAction() {
        for (CombatantNode target : this.getTargets()) {
            BattleManager.getInstance().attack(this.getActor(), target);
        }
        BattleState.getInstance().resetState(this.getActor());
    }
    
}
