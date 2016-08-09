/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.graphics.animation;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import game.state.AbstractGameState;

/**
 *
 * @author Dave
 */
public abstract class AbstractAnimation extends AbstractGameState implements Animation {
    
    /**
     * Flag to determine if the animation is processing or not.
     */
    private boolean isAnimating;
    
    @Override
    public void initialize(AppStateManager manager, Application app) {
        super.initialize(manager, app);
        this.setAnimating(true);
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager) {
        this.setEnabled(false);
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
    
    @Override
    public void setAnimating(boolean animating) {
        this.isAnimating = animating;
    }
    
    @Override
    public boolean isAnimating() {
        return isAnimating;
    }
}
