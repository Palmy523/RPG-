/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.graphics.animation.attack;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import game.graphics.animation.AbstractAnimation;
import graphics.shapes.Sword;

/**
 *
 * @author Dave
 */
public class SlashFullRotation extends AbstractAnimation {

    private Sword sword;
    private float speed = 5.0f;
    private float interp;
    private int rotationDegrees = 0;
    private Quaternion startingRotation;
    private int rotationAmount = 120;
    public boolean disable = false;

    public SlashFullRotation(Sword sword) {
        this.sword = sword;
        this.startingRotation = sword.getLocalRotation();
    }

    @Override
    public void initialize(AppStateManager manager, Application app) {
        super.initialize(manager, app);
        interp = 0;
    }

    @Override
    public void update(float tpf) {
        if (disable) {
            this.setEnabled(false);
        } else {
            rotationDegrees += speed;
            sword.rotate(0, 0, FastMath.DEG_TO_RAD * speed);
        }

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
}
