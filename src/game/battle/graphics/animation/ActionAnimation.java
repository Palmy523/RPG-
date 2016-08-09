/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.graphics.animation;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import game.battle.BattleState;
import game.battle.action.BattleAction;
import game.graphics.animation.AbstractAnimation;

/**
 *
 * @author Dave
 */
public class ActionAnimation extends AbstractAnimation {

    private boolean isAnimating;
    private BattleAction action;
    private boolean isActionProcessing = false;
    private AbstractAnimation moveAnimation;
    private AbstractAnimation actionAnimation;

    public ActionAnimation(BattleAction action) {
        this.action = action;
        moveAnimation = action.getMoveAnimation();
        actionAnimation = action.getActionAnimation();
    }

    @Override
    public void initialize(AppStateManager manager, Application app) {
        super.initialize(manager, app);
        this.setAnimating(true);
        moveAnimation.setAnimating(true);
        manager.attach(moveAnimation);
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (!moveAnimation.isAnimating()) {
            if (!isActionProcessing) {
                isActionProcessing = true;
                actionAnimation.setAnimating(true);
                this.getStateManager().attach(actionAnimation);
                action.processAction();
            }
            if (!actionAnimation.isAnimating()) {
                moveAnimation.setEnabled(false);
                this.getStateManager().detach(moveAnimation);
                actionAnimation.setEnabled(false);
                this.getStateManager().detach(actionAnimation);
            }
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public boolean isAnimating() {
        return isAnimating;
    }
}
