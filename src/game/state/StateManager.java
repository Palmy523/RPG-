/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import component.state.BattleStateModel;
import de.lessvoid.nifty.Nifty;
import game.Main;
import game.audio.AudioManager;
import game.state.battle.BattleState;
import game.state.menu.MainMenuState;
import game.state.menu.battle.BattleLostMenu;

/**
 *
 * @author Dave
 */
public class StateManager {

    private static StateManager instance;
    private Main app;
    private AppStateManager manager;
    private Nifty nifty;

    public StateManager(Application app) {
        this.app = (Main) app;
        manager = app.getStateManager();
        nifty = this.app.getNifty();
    }

    public void loadStartMenu() {
        MainMenuState mainMenuState = new MainMenuState();
        GameState.getInstance().setCurrentState(mainMenuState);
        manager.attach(mainMenuState);
    }

    public void loadBattle(BattleStateModel battleModel) {
        manager = app.getStateManager();
        BattleState state = new BattleState(battleModel);
        manager.attach(state);
        GameState.getInstance().setCurrentState(state);
    }
    
    public void loadBattleLostState(BattleState state) {
        nifty.exit();
        state.setEnabled(false);
        AudioManager.getInstance().playBackgroundMusic(new AudioNode(app.getAssetManager(), 
                "Sounds/Music/Character Themes and Atmospheric/Suikoden 1 - This is Just a Rumor.ogg"));
        manager.attach(new BattleLostMenu());
    }

    public static StateManager getStateManager() {
        if (instance == null) {
            instance = new StateManager(Main.app);
        }
        return instance;
    }
}
