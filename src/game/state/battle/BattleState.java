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
import game.event.battle.BattleEventBus;
import game.event.battle.TurnEvent;
import graphics.combatant.CombatantNode;
import graphics.scene.Scene.SceneType;
import graphics.scene.battle.BattleScene;

/**
 *
 * @author Dave
 */
public class BattleState extends AbstractAppState {

    private Main app;
    private AppStateManager manager;
    private BattleStateModel model;
    private BattleScene scene;
    private BattleEventBus eventBus;
    private TurnEventQueue turnQueue;
    private TurnEventQueue enemyTurnQueue;
    private TurnEvent currentTurn;

    public BattleState(BattleStateModel model) {
        this.model = model;
        eventBus = new BattleEventBus();
        turnQueue = new TurnEventQueue(model.getAllys().size());
        enemyTurnQueue = new TurnEventQueue(model.getEnemies().size());
    }

    @Override
    public void initialize(AppStateManager manager, Application app) {
        this.manager = manager;
        this.app = (Main) app;
        scene = new BattleScene(model, SceneType.DEFAULT);
        AudioNode backgroundMusic = scene.getBackgroundMusic();
        backgroundMusic.setPositional(false);
        AudioManager.getInstance().playBackgroundMusic(backgroundMusic);
        this.app.getRootNode().attachChild(scene);
    }

    @Override
    public void update(float tpf) {
        for (CombatantNode node : scene.getCombatantMap().values()) {
            if (node.getAtbGauge().isFull()) {
                queueTurn(node);
            } else {
                node.incrementATBGauge();
            }
        }

        if (currentTurn == null) {
            currentTurn = turnQueue.pop();
        }
        
        if (currentTurn != null && !currentTurn.isProcessing()) {
            if (!currentTurn.isAwaitingUserInput()) {
                currentTurn = turnQueue.pop();
                currentTurn.fireEvent();
                currentTurn.isAwaitingUserInput();
            }
        }

        eventBus.processAllEvents();
    }

    private void queueTurn(CombatantNode node) {
        String id = node.getCombatant().getId();
        switch (node.getCombatant().getType()) {
            case Ally:
                if (turnQueue.contains(id)) {
                    break;
                }
                turnQueue.push(new TurnEvent(this, node));
                break;
            case Enemy:
                if (enemyTurnQueue.contains(id)) {
                    break;
                }
                enemyTurnQueue.push(new TurnEvent(this, node));
                break;
        }

    }
}
