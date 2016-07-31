/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component.state;

import com.jme3.app.Application;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.renderer.RenderManager;
import component.battle.combatant.Combatant;
import component.user.User;
import java.util.HashMap;

/**
 *
 * @author Dave
 */
public class GameStateModel {
    
    public static enum State {StartScreen, MainMenu, World, Battle, Environment}
    
    private User user;
    private State currentState;
    private HashMap<String, Combatant> allys;
    
    public GameStateModel() {
        allys = new HashMap<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public HashMap<String, Combatant> getAllys() {
        return allys;
    }

    public void setAllys(HashMap<String, Combatant> allys) {
        this.allys = allys;
    }
    
    
    
    
    
}
