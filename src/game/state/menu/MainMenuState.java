/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state.menu;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import component.state.BattleStateModel;
import controller.menu.AbstractMenuController;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import game.Main;
import game.audio.AudioManager;
import game.state.GameState;
import game.state.StateManager;

/**
 *
 * @author Dave
 */
public class MainMenuState extends AbstractMenuController {

    private Main app;
    private AppStateManager manager;
    private static String xmlResource = "Interface/MainMenuLayout.xml";
    private static String id = "main";
        
    @Override
    public void stateDetached(AppStateManager manager) {
        System.out.println("DETACHED>>>>");
    }
        
    @Override
    public void onStartScreen() {
        
    }

    @Override
    public void onEndScreen() {
        System.out.println("SCREEN ENDED");
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
        BattleStateModel model = GameState.getInstance().getBattleStateModel();
        StateManager.getStateManager().loadBattle(model);
        this.getNifty().exit();
    }
    
    @NiftyEventSubscriber(id="exit")
    public void exit(String id, ButtonClickedEvent event) {
        System.exit(0);
    }
    
    @NiftyEventSubscriber(id="playMusic")
    public void playMusic(String id, ButtonClickedEvent event) {
        AudioNode node = new AudioNode(Main.app.getAssetManager(), "Sounds/Battle.ogg");
        node.setPositional(false);
        AudioManager.getInstance().playBackgroundMusic(node);
    }
}
