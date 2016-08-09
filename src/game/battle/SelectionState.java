/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import game.Game;
import game.battle.action.BattleAction;
import game.battle.graphics.BattleScene;
import game.battle.graphics.CombatantNode;
import game.error.GameException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dave
 */
public class SelectionState extends AbstractAppState {

    protected static enum SelectionSide {

        ENEMY, ALLY
    }
    private SelectionSide selectionSide;
    private static int targetSelected = 0;
    private static boolean targetUpdate = true;
    private int numAllys;
    private int numEnemies;
    private BattleState state;
    private BattleScene scene;
    private Game app;
    private AppStateManager manager;
    private CombatantNode actor;
    private CombatantNode target;
    private BattleAction action;
    private ActionListener listener;

    public SelectionState(BattleState state) {
        this.state = state;
        this.scene = state.getScene();
        registerControls();
    }

    @Override
    public void initialize(AppStateManager manager, Application app) {
        this.app = (Game) app;
        this.manager = manager;
    }

    @Override
    public void update(float tpf) {
        CombatantNode node = null;
        switch (selectionSide) {
            case ALLY:
                try {
                    node = scene.getAllys().get(targetSelected);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println(e.getStackTrace());
                    targetSelected = 0;
                    return;
                }
                break;
            case ENEMY:
                try {
                    node = scene.getEnemies().get(targetSelected);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println(e.getStackTrace());
                    targetSelected = 0;
                    return;
                }
        }

        if (node == target) {
            return;
        }

        if (target != null) {
            target.select(false);
        }

        target = node;
        target.select(true);

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            Game.app.getInputManager().addListener(listener, new String[]{"CursorUp", "CursorDown", "SwitchSides", "ProcessAction"});
            if (target != null) {
                target.select(true);
            }
        } else {
            Game.app.getInputManager().removeListener(listener);
            if (target != null) {
                target.select(false);
            }
        }
    }

    private void registerControls() {
        Game.app.getInputManager().addMapping("CursorUp", new KeyTrigger(KeyInput.KEY_W));
        Game.app.getInputManager().addMapping("CursorDown", new KeyTrigger(KeyInput.KEY_S));
        Game.app.getInputManager().addMapping("SwitchSides", new KeyTrigger(KeyInput.KEY_A));
        Game.app.getInputManager().addMapping("SwitchSides", new KeyTrigger(KeyInput.KEY_D));
        Game.app.getInputManager().addMapping("ProcessAction", new KeyTrigger(KeyInput.KEY_SPACE));

        listener = new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (isPressed) {
                    switch (name) {
                        case "CursorUp":
                            cursorUp();
                            break;
                        case "CursorDown":
                            cursorDown();
                            break;
                        case "SwitchSides":
                            switchSides();
                            break;
                        case "ProcessAction":
                            processAction();
                            break;
                    }
                }

            }
        };

    }

    private void deregisterControls() {
        Game.app.getInputManager().removeListener(listener);
    }

    private void cursorDown() {
        System.out.println("Whatever man.");
        int selectionSize = (selectionSide == SelectionSide.ALLY) ? numAllys : numEnemies;
        targetSelected--;
        if (targetSelected < 0) {
            targetSelected = selectionSize;
        }
    }

    private void cursorUp() {
        int selectionSize = (selectionSide == SelectionSide.ALLY) ? numAllys : numEnemies;
        targetSelected++;
        if (targetSelected > selectionSize) {
            targetSelected = 0;
        }
    }

    private void switchSides() {
        switch (selectionSide) {
            case ALLY:
                selectionSide = SelectionSide.ENEMY;
                break;
            case ENEMY:
                selectionSide = SelectionSide.ALLY;
                break;
        }
    }

    private void processAction() {
        try {
            List<CombatantNode> targets = new ArrayList<>();
            targets.add(target);
            state.getEventBus().fireOnActionEvent(action.getActor(), action, targets);
        } catch (GameException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void setSelectionSide(SelectionSide side) {
        this.selectionSide = side;
    }

    public int getNumAllys() {
        return numAllys;
    }

    public void setNumAllys(int numAllys) {
        this.numAllys = numAllys;
    }

    public int getNumEnemies() {
        return numEnemies;
    }

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    public CombatantNode getActor() {
        return actor;
    }

    public void setActor(CombatantNode actor) {
        this.actor = actor;
    }

    public BattleAction getAction() {
        return action;
    }

    public void setAction(BattleAction action) {
        this.action = action;
    }
}
