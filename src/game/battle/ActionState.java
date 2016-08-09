/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import game.graphics.animation.AbstractAnimation;
import game.state.AbstractGameState;

/**
 *
 * @author Dave
 */
public class ActionState extends AbstractGameState {
    
    private AbstractAnimation moveAnimation;
    private AbstractAnimation actionAnimation;
    private AbstractAnimation recipientAnimation;
    
    
    
    public ActionState(AbstractAnimation moveAnimation, 
            AbstractAnimation attackAnimation, AbstractAnimation recipientAnimation) {
        this.moveAnimation = moveAnimation;
        this.actionAnimation = attackAnimation;
        this.recipientAnimation = recipientAnimation;
    }
    
    @Override
    public void initialize(AppStateManager manager, Application app) {
        manager.attach(moveAnimation);
    }
    
    @Override
    public void update(float tpf) {
        if (!moveAnimation.isAnimating()) {
            this.getStateManager().attach(actionAnimation);
            this.getStateManager().attach(recipientAnimation);
        }
    }
    
}
