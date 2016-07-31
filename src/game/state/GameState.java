/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import component.state.BattleStateModel;
import component.state.GameStateModel;
import game.Main;
import game.state.menu.MainMenuState;

/**
 *
 * @author Dave
 */
public class GameState extends AbstractAppState {

    public static enum Type {Start, WorldMap, Battle, Environment};
    private GameStateModel model;
    private AppStateManager manager;
    private SimpleApplication app;
    private Type type;
    private static GameState instance;
    private static AppState currentState;
    private static BattleStateModel battleStateModel;
    
    
    public GameState() {
       setInstance(this);
    }
    
    @Override
    public void initialize(AppStateManager manager, Application app) {
        this.manager = manager;
        this.app = (Main) app;
        this.app.getStateManager().attach(new MainMenuState());
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
    
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
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

    public SimpleApplication getApp() {
        return app;
    }

    public void setApp(SimpleApplication app) {
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
    
    
    
    
    
}
