/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.graphics.animation;

import com.jme3.app.state.AbstractAppState;

/**
 *
 * @author Dave
 */
public interface Animation {
    
    public abstract boolean isAnimating();
    public abstract void setAnimating(boolean animating);
}
