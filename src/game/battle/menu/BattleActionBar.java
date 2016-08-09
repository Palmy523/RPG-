/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.menu;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import controller.menu.AbstractMenuController;
import game.Game;
import game.battle.BattleState;
import game.battle.action.AttackAction;
import game.battle.graphics.CombatantNode;
import game.error.GameException;

/**
 *
 * @author Dave
 */
public class BattleActionBar extends AbstractMenuController {

    private ActionListener actionListener;
    private boolean actionProcessing;
    private BattleState state;
    private CombatantNode actor;

    public BattleActionBar() {
    }

    @Override
    public void initialize(AppStateManager manager, Application app) {
        super.initialize(manager, app);
        registerControls();
    }

    public void stateAttached() {
        registerControls();
    }

    public void stateDettached() {
        deregisterControls();
    }

    private void registerControls() {
        this.getApp().getInputManager().addMapping("Attack", new KeyTrigger(KeyInput.KEY_F));

        actionListener = new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (isPressed) {
                    if (name.equals("Attack") && !actionProcessing) {
                        AttackAction action = new AttackAction(actor);
                        try {
                            state.getEventBus().fireTargetSelectionEvent(action);
                        } catch (GameException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }
                }
            }
        };
        this.getApp().getInputManager().addListener(actionListener, new String[]{"Attack"});
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            this.getApp().getInputManager().removeListener(actionListener);
            this.getNifty().removeScreen(this.getMenuId());
        } else {
            if (this.getNifty() != null) {
                this.getNifty().addScreen(this.getMenuId(), this.getScreen());
                this.getNifty().update();
            }
            if (actionListener != null) {
                this.getApp().getInputManager().addListener(actionListener, new String[]{"Attack"});
            }
        }
    }

    private void deregisterControls() {
        this.getApp().getInputManager().removeListener(actionListener);
    }

    @Override
    public String getXMLResource() {
        return "Interface/Battle/BattleActionBar.xml";
    }

    @Override
    public String getMenuId() {
        return "battle_actionbar";
    }

    @Override
    public void onStartScreen() {
    }

    public BattleState getState() {
        return state;
    }

    public void setState(BattleState state) {
        this.state = state;
    }

    public CombatantNode getActor() {
        return actor;
    }

    public void setActor(CombatantNode actor) {
        this.actor = actor;
    }
}
