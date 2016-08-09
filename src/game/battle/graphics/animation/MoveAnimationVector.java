/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.graphics.animation;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import game.battle.graphics.CombatantNode;
import game.graphics.animation.AbstractAnimation;

/**
 *
 * @author Dave
 */
public class MoveAnimationVector extends AbstractAnimation {

    private CombatantNode actor;
    private Vector3f position;
    private Vector3f startPosition;
    float interp = 0;
    private float movementSpeed = 5.0f;
    private CollisionResults results;
    private Ray ray;

    public MoveAnimationVector(CombatantNode actor, Vector3f position) {
        this.actor = actor;
        this.startPosition = actor.getLocalTranslation();
        this.position = position;
    }

    @Override
    public void initialize(AppStateManager manager, Application app) {
        super.initialize(manager, app);
        this.setAnimating(true);
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (this.isAnimating()) {
            Vector3f direction = new Vector3f().interpolate(actor.getStartingPosition(), position, interp);
            actor.setLocalTranslation(direction);
            interp += movementSpeed * tpf;
            if (interp >= 1.0f) {
                this.setAnimating(false);
            }
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            actor.setLocalTranslation(actor.getStartingPosition());
        }
    }

}
