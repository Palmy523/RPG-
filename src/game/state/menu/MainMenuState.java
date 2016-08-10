/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state.menu;

import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import game.battle.component.BattleStateModel;
import controller.menu.AbstractMenuController;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import game.Game;
import game.audio.AudioManager;
import game.StateManager;

/**
 *
 * @author Dave
 */
public class MainMenuState extends AbstractMenuController {

    private Game app;
    private AppStateManager manager;
    private static String xmlResource = "Interface/MainMenuLayout.xml";
    private static String id = "main";
        
    @Override
    public void stateDetached(AppStateManager manager) {
    }
        
    @Override
    public void onStartScreen() {
        
    }

    @Override
    public void onEndScreen() {
        super.onEndScreen();
    }
    
    @Override
    public String getXMLResource() {
        return xmlResource;
    }
    
    @Override
    public String getMenuId() {
        return id;
    }
    
    @NiftyEventSubscriber(id="play")
    public void play(String id, ButtonClickedEvent event) {
        BattleStateModel model = Game.getGameState().getBattleStateModel();
        StateManager.getStateManager().loadBattle(model);
        this.getNifty().exit();
    }
    
    @NiftyEventSubscriber(id="exit")
    public void exit(String id, ButtonClickedEvent event) {
        System.exit(0);
    }
    
    @NiftyEventSubscriber(id="doSomethingElse")
    public void playMusic(String id, ButtonClickedEvent event) {
        StateManager.getStateManager().loadDoSomethingElse();
    }
    
    @NiftyEventSubscriber(id="options")
    public void openOptionsMenu(String id, ButtonClickedEvent event) {
        StateManager.getStateManager().loadOptionsMenu();
    }
}
