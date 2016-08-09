/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import game.Game;

/**
 *
 * @author Dave
 */
public abstract class AbstractGameState extends AbstractAppState {
    
    private AppStateManager appStateManager;
    private Game game;
    
    @Override
    public void initialize(AppStateManager manager, Application app) {
        super.initialize(manager, app);
        this.game = (Game) app;
        this.appStateManager = manager;
    }
    
    public AppStateManager getStateManager() {
        return appStateManager;
    }
    
    public Game getApp() {
        return game;
    }
}
