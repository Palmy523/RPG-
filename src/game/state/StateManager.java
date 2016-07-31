/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state;

import com.jme3.app.Application;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import component.state.BattleStateModel;
import game.Main;
import game.state.battle.BattleState;
import game.state.menu.MainMenuState;

/**
 *
 * @author Dave
 */
public class StateManager {

    private static StateManager instance;
    private Main app;

    public StateManager(Application app) {
        this.app = (Main) app;
    }

    public void loadStartMenu() {
        MainMenuState mainMenuState = new MainMenuState();
        GameState.getInstance().setCurrentState(mainMenuState);
        app.getStateManager().attach(mainMenuState);
    }

    public void loadBattle(BattleStateModel battleModel) {
        AppStateManager manager = app.getStateManager();
        BattleState state = new BattleState(battleModel);
        manager.attach(state);
        GameState.getInstance().setCurrentState(state);
    }

    public static StateManager getStateManager() {
        if (instance == null) {
            instance = new StateManager(Main.app);
        }
        return instance;
    }
}
