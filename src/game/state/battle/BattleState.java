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
import game.event.battle.EnemyTurnEvent;
import game.event.battle.AllyTurnEvent;
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
    private TurnEvent currentTurnEvent;
    private TurnEvent enemyTurnEvent;
    private long startTime = System.currentTimeMillis();
    private double atbUpdateStat = 0;

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
        long currentTime = System.currentTimeMillis();
        atbUpdateStat += currentTime - startTime;
        startTime = currentTime;
        updateATBGauge();
        processPlayerTurn();
        processEnemyTurn();
        eventBus.processAllEvents();
        atbUpdateStat = 0;
    }

    private void updateATBGauge() {
        for (CombatantNode node : scene.getCombatantMap().values()) {
           node.getManaBar().decrement();
           node.getSkillBar().decrement();
            if (node.getAtbGauge().isFull()) {
                //node.getAtbGauge().clearFill();
                queueTurn(node);
            } else {
                node.incrementATBGauge((int) atbUpdateStat);
            }
        }
    }

    private void queueTurn(CombatantNode node) {
        String id = node.getCombatant().getId();
        switch (node.getCombatant().getType()) {
            case Ally:
                if (turnQueue.contains(id)) {
                    break;
                }
                turnQueue.push(new AllyTurnEvent(this, node));
                break;
            case Enemy:
                if (enemyTurnQueue.contains(id)) {
                    break;
                }
                enemyTurnQueue.push(new EnemyTurnEvent(this, node));
                break;
        }

    }

    private void processPlayerTurn() {
        if (currentTurnEvent == null) {
            currentTurnEvent = turnQueue.pop();
        }

        if (currentTurnEvent != null && !currentTurnEvent.isProcessing()) {
            if (!currentTurnEvent.isAwaitingUserInput()) {
                if (!currentTurnEvent.isEventFired()) {
                    //eventBus.registerEvent(currentTurnEvent);
                    currentTurnEvent.fireEvent();
                    currentTurnEvent.isAwaitingUserInput();
                }
            }
        }
    }

    private void processEnemyTurn() {
        if (enemyTurnEvent == null) {
            enemyTurnEvent = enemyTurnQueue.pop();
        }

        if (enemyTurnEvent != null && !enemyTurnEvent.isProcessing()) {
            if (!enemyTurnEvent.isAwaitingUserInput()) {
                if (!enemyTurnEvent.isEventFired()) {
                    enemyTurnEvent.fireEvent();
                    enemyTurnEvent = null;
                }
            }
        }
    }

    public BattleScene getScene() {
        return scene;
    }

    public void setScene(BattleScene scene) {
        this.scene = scene;
    }
    
    
    
    
}
