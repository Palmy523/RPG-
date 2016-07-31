/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state.battle;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import component.state.BattleStateModel;
import game.Main;
import game.audio.AudioManager;
import graphics.Scene.SceneType;
import graphics.battle.BattleScene;

/**
 *
 * @author Dave
 */
public class BattleState extends AbstractAppState {
    
    private Main app;
    private AppStateManager manager;
    private BattleStateModel model;
    
    public BattleState(BattleStateModel model) {
        this.model = model;
    }
    
    @Override
    public void initialize(AppStateManager manager, Application app) {
        this.manager = manager;
        this.app = (Main) app;
        BattleScene scene = new BattleScene(model, SceneType.DEFAULT);
        AudioNode backgroundMusic = scene.getBackgroundMusic();
        backgroundMusic.setPositional(false);
        AudioManager.getInstance().playBackgroundMusic(backgroundMusic);
        this.app.getRootNode().attachChild(scene);
    }
}
