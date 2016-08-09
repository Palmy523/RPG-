/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.graphics.animation;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import game.Game;
import game.battle.graphics.CombatantNode;
import game.graphics.animation.AbstractAnimation;

/**
 * Moves a CombatantNode within specified distance of another spatial target.
 * 
 * @author Dave
 */
public class MoveAnimation extends AbstractAnimation {

    /**
     * The CombatantNode to be moved.
     */
    private CombatantNode actor;
    
    /**
     * The target spatial to move the actor to.
     */
    private Spatial target;
    
    /**
     * The specified distance from the target to stop.
     */
    private float distance = 0;
    
    /**
     * Percentage of movement completed.
     */
    float interp = 0;
    
    /**
     * The movement speed of the actor.
     */
    private float movementSpeed = 1.0f;
    
    /**
     * Stores collision results from the ray of the actor to the spatial to 
     * determine distance from.
     */
    private CollisionResults results;
    
    /**
     * The ray of movement from the actor to the spatial.
     */
    private Ray ray;

    public MoveAnimation(CombatantNode actor, Spatial target, float distance) {
        this.actor = actor;
        this.target = target;
        this.distance = distance;
    }

    @Override
    public void initialize(AppStateManager manager, Application app) {
        super.initialize(manager, app);
        results = new CollisionResults();
        this.setAnimating(true);
    }

    /**
     * Updates the display moving the actor at intervals determined by the speed
     * towards the spatial target.
     * 
     * @param tpf 
     */
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isAnimating()) {
            Vector3f direction = new Vector3f().interpolate(actor.getStartingPosition(), target.getLocalTranslation(), interp);
            actor.setLocalTranslation(direction);
            interp += movementSpeed * tpf;
            ray.setOrigin(actor.getCombatantGeom().getWorldTranslation());
            ray.setDirection(target.getWorldTranslation());

            Game.app.getRootNode().collideWith(ray, results);

            if (results.size() > 0) {
                for (CollisionResult result : results) {
                    Geometry collision = result.getGeometry();
                    if (collision == target && result.getDistance() < distance) {
                        this.setAnimating(false);
                    }
                }

            }
        }
    }
}
