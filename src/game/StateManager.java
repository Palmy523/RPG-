/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import game.battle.component.BattleStateModel;
import de.lessvoid.nifty.Nifty;
import game.audio.AudioManager;
import game.battle.BattleState;
import game.state.menu.MainMenuState;
import game.battle.menu.BattleLostMenu;
import game.battle.menu.BattleWonMenu;
import game.state.menu.OptionsMenuState;

/**
 *
 * @author Dave
 */
public class StateManager {

    private static StateManager instance;
    private Game app;
    private AppStateManager manager;
    private Nifty nifty;
    private GameState gameState;

    public StateManager(Application app) {
        this.app = (Game) app;
        gameState = Game.getGameState();
        manager = app.getStateManager();
        nifty = this.app.getNifty();
    }

    public void loadStartMenu() {
        manager.detach(gameState.getCurrentState());
        MainMenuState mainMenuState = new MainMenuState();
        Game.getGameState().setCurrentState(mainMenuState);
        manager.attach(mainMenuState);
    }
    
    public void loadOptionsMenu() {
        nifty.exit();
        OptionsMenuState optionsMenuState = new OptionsMenuState();
        Game.getGameState().setCurrentState(optionsMenuState);
        manager.attach(optionsMenuState);
    }

    public void loadBattle(BattleStateModel battleModel) {
        manager = app.getStateManager();
        BattleState state = new BattleState(battleModel);
        manager.attach(state);
        Game.getGameState().setCurrentState(state);
    }
    
    public void loadBattleLostState(BattleState state) {
        state.setEnabled(false);
        AudioManager.getInstance().playBackgroundMusic(new AudioNode(app.getAssetManager(), 
                "Sounds/Music/Character Themes and Atmospheric/Suikoden 1 - This is Just a Rumor.ogg"));
        BattleLostMenu wahWahWah = new BattleLostMenu();
        gameState.setCurrentState(wahWahWah);
        manager.attach(wahWahWah);
        manager.detach(state);
    }
    
    public void loadBattleWonState(BattleState state) {
        try {
            nifty.exit();
        } catch (NullPointerException e) {
            System.out.println("Nifty screen is null");
        }
        state.setEnabled(false);
        AudioManager.getInstance().playBackgroundMusic(new AudioNode(app.getAssetManager(),
                "Sounds/Music/Event/Suikoden 1 - Joy Joy Time.ogg"));
        BattleWonMenu wonState = new BattleWonMenu();
        gameState.setCurrentState(wonState);
        manager.attach(wonState);
        manager.detach(state);
    }
    
    public void loadDoSomethingElse() {
        nifty.exit();
        Game.app.getFlyByCamera().setEnabled(true);
        AudioNode node = new AudioNode(Game.app.getAssetManager(), "Sounds/Music/Battle Music/Battle.ogg");
        node.setPositional(false);
        AudioManager.getInstance().playBackgroundMusic(node);
        DoSomethingElseState state = new DoSomethingElseState();
        gameState.setCurrentState(state);
        manager.attach(state);
    }

    public static StateManager getStateManager() {
        if (instance == null) {
            instance = new StateManager(Game.app);
        }
        return instance;
    }
}
