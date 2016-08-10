/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.jme3.app.Application;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import game.battle.component.BattleStateModel;
import component.state.GameStateModel;
import component.user.GameOptions;
import game.state.AbstractGameState;
import game.state.menu.MainMenuState;

/**
 *
 * @author Dave
 */
public class GameState extends AbstractGameState {

    private String userId;
    public static enum Type {Start, WorldMap, Battle, Environment};
    private GameStateModel model;
    private AppStateManager manager;
    private Game app;
    private Type type;
    private static GameState instance;
    private static AppState currentState;
    private static BattleStateModel battleStateModel;
    private GameOptions options = new GameOptions();
    
    
    public GameState(String userId) {
        this.userId = userId;
    }
    
    @Override
    public void initialize(AppStateManager manager, Application app) {
        this.getApp().getStateManager().attach(new MainMenuState());
    }
    
    public GameState(GameStateModel model) {
        this.model = model;
    }

    public GameStateModel getModel() {
        return model;
    }

    public void setModel(GameStateModel model) {
        this.model = model;
    }
    
    public void setInstance(GameState state) {
        instance = state;
    }

    public AppStateManager getManager() {
        return manager;
    }

    public void setManager(AppStateManager manager) {
        this.manager = manager;
    }

    public void setApp(Game app) {
        this.app = app;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BattleStateModel getBattleStateModel() {
        return battleStateModel;
    }

    public void setBattleStateModel(BattleStateModel battleModel) {
        GameState.battleStateModel = battleModel;
    }

    public AppState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(AppState currentState) {
        GameState.currentState = currentState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GameOptions getOptions() {
        return options;
    }

    public void setOptions(GameOptions options) {
        this.options = options;
    }
    
    
    
    
    
}
