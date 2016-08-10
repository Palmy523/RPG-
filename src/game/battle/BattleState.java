/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle;

import game.battle.event.BattleEventBus;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import component.battle.combatant.Combatant;
import component.battle.combatant.Combatant.CombatantType;
import component.user.GameOptions.BattleMode;
import game.battle.component.BattleStateModel;
import game.Game;
import game.StateManager;
import game.audio.AudioManager;
import game.battle.SelectionState.SelectionSide;
import game.battle.action.BattleAction;
import game.battle.event.ActionEvent;
import game.battle.event.CombatantDeathEvent;
import game.battle.event.OnTurnEvent;
import game.battle.event.TargetSelectionEvent;
import game.battle.graphics.CombatantNode;
import graphics.scene.Scene.SceneType;
import game.battle.graphics.BattleScene;
import game.battle.menu.BattleActionBar;
import game.error.GameException;
import game.event.GameEvent;
import game.eventhandler.GameEventHandler;
import game.battle.graphics.animation.ActionAnimation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Dave
 */
public class BattleState extends AbstractAppState {

    private Game app;
    private AppStateManager manager;
    private BattleStateModel model;
    private BattleScene scene;
    private BattleEventBus eventBus;
    private ArrayBlockingQueue turnQueue;
    private ArrayBlockingQueue enemyTurnQueue;
    private long startTime = System.currentTimeMillis();
    private double atbUpdateStat = 0;
    private SelectionState selectionState;
    private BattleActionBar actionBarState;
    private static BattleState instance;
    private CombatantNode currentTurnNode;
    private CombatantNode enemyTurnNode;
    private List<CombatantNode> combatantRemovalList;
    private boolean pauseATBUpdate = false;
    private BattleMode battleMode;
    
    private boolean allyTurnProcessing;
    private boolean enemyTurnProcessing;

    public BattleState(BattleStateModel model) {
        this.model = model;
        eventBus = new BattleEventBus();
        combatantRemovalList = new ArrayList<>();
        this.turnQueue = new ArrayBlockingQueue(model.getAllys().size());
        this.enemyTurnQueue = new ArrayBlockingQueue(model.getEnemies().size());
        registerHandlers();
        BattleManager.getInstance().setBattleState(this);
        instance = this;
        battleMode = Game.getGameState().getOptions().getBattleMode();
    }

    private void registerHandlers() {
        GameEventHandler onTurnHandler = new GameEventHandler() {
            @Override
            public void onEvent(GameEvent e) {
                OnTurnEvent event = (OnTurnEvent) e;
                CombatantNode node = event.getCombatantNode();
                CombatantType type = node.getCombatant().getType();
                
                if (type == CombatantType.Ally) {
                    if (!turnQueue.contains(node) && node != currentTurnNode) {
                        turnQueue.add(node);
                    }

                    if (!allyTurnProcessing) {
                        if(battleMode == BattleMode.WAIT) {
                            pauseATBUpdate(true);
                        }
                        setAllyTurnProcessing(true);
                        currentTurnNode = (CombatantNode) turnQueue.poll();
                        initiateTurn(currentTurnNode);
                    }
                }
                
                if (type == CombatantType.Enemy) {
                    if (!enemyTurnQueue.contains(node) && node != enemyTurnNode) {
                        enemyTurnQueue.add(node);
                    }
                    
                    if (!enemyTurnProcessing) {
                        enemyTurnProcessing = true;
                        enemyTurnNode = (CombatantNode) enemyTurnQueue.poll();
                        processNPCAction(node);
                    }
                }
            }
        };

        eventBus.registerHandler(OnTurnEvent.class, onTurnHandler);

        GameEventHandler onActionHandler = new GameEventHandler() {
            @Override
            public void onEvent(GameEvent e) {
                ActionEvent event = (ActionEvent) e;
                CombatantNode node = event.getActor();
                event.getAction().setTargets(event.getTargets());
                processCombatantAction(node, event.getAction());
            }
        };

        eventBus.registerHandler(ActionEvent.class, onActionHandler);

        GameEventHandler onCombatantDeath = new GameEventHandler() {
            @Override
            public void onEvent(GameEvent e) {

            }
        };

        eventBus.registerHandler(CombatantDeathEvent.class, onCombatantDeath);

        GameEventHandler onTargetSelectionEvent = new GameEventHandler() {
            @Override
            public void onEvent(GameEvent e) {

                TargetSelectionEvent event = (TargetSelectionEvent) e;
                loadTargetSelectionState(event.getAction());
            }
        };

        eventBus.registerHandler(TargetSelectionEvent.class, onTargetSelectionEvent);
    }

    @Override
    public void initialize(AppStateManager manager, Application app) {
        this.manager = manager;
        this.app = (Game) app;
        scene = new BattleScene(model, SceneType.DEFAULT);
        AudioNode backgroundMusic = scene.getBackgroundMusic();
        backgroundMusic.setPositional(false);
        AudioManager.getInstance().playBackgroundMusic(backgroundMusic);
        if (selectionState == null) {
            selectionState = new SelectionState(this);
        }
        if (actionBarState == null) {
            actionBarState = new BattleActionBar();
        }

        this.app.getRootNode().attachChild(scene);
    }

    @Override
    public void update(float tpf) {
        removeCombatants();
        updateATBGauge();
        //processEnemyTurn();
    }

    /**
     * Update the ATB based on system time passed. if the ATB guage is full,
     * will prompt
     */
    private void updateATBGauge() {
        long currentTime = System.currentTimeMillis();
        atbUpdateStat += currentTime - startTime;
        startTime = currentTime;
        if (!pauseATBUpdate) {
            for (CombatantNode node : scene.getCombatantMap().values()) {
                if (node.getAtbGauge().isFull()) {
                    try {
                        eventBus.fireOnTurnEvent(node);
                    } catch (GameException ex) {
                        System.err.println(ex.getMessage());
                    }
                } else {
                    node.incrementATBGauge((int) atbUpdateStat);
                }
            }
        }
        atbUpdateStat = 0;
    }

    /**
     * Initiates the turn for a player by highlighting and displaying the action
     * bar.
     *
     * @param combatant the combatant to initiate the turn for.
     */
    private void initiateTurn(CombatantNode combatant) {
        actionBarState = null;
        actionBarState = new BattleActionBar();
        actionBarState.setEnabled(true);
        actionBarState.setState(this);
        actionBarState.setActor(combatant);
        combatant.turnHighlight(true);
        Game.app.getStateManager().attach(this.actionBarState);
    }

    private void processNPCAction(CombatantNode node) {
        BattleManager.getInstance().processNPCAttack(node, this);
    }

    public void processCombatantAction(CombatantNode node, BattleAction action) {
        if (Game.getGameState().getOptions().isPauseATBOnAction()) {
            this.pauseATBUpdate(true);
        }
        ActionAnimation animation = new ActionAnimation(action);
        this.manager.attach(animation);
    }

    /**
     * Resets a players state, should be used after a player has finished 
     * enacting a turn.
     * 
     * @param node the player node to reset.
     */
    public void resetState(CombatantNode node) {
        node.getAtbGauge().clearFill();
        if (node.getCombatant().getType() == Combatant.CombatantType.Enemy) {
            enemyTurnNode = null;
            enemyTurnProcessing = false;
        }
        if (node.getCombatant().getType() == Combatant.CombatantType.Ally) {
            node.turnHighlight(false);
            this.allyTurnProcessing = false;
            this.currentTurnNode = null;
            selectionState.setEnabled(false);
            actionBarState.setEnabled(false);
        }
        this.pauseATBUpdate(false);
    }

    public boolean isATBPaused() {
        return pauseATBUpdate;
    }
    
    public void pauseATBUpdate(boolean paused) {
        this.pauseATBUpdate = paused;
    }



    public void loadTargetSelectionState(BattleAction action) {
        actionBarState.setEnabled(false);
        this.manager.detach(actionBarState);
        switch (action.getTargetSelectionType()) {
            case ENEMY_SINGLE:
                selectionState.setSelectionSide(SelectionSide.ENEMY);
                selectionState.setNumEnemies(scene.getEnemies().size() - 1);
                selectionState.setNumAllys(scene.getAllys().size() - 1);
                selectionState.setActor(action.getActor());
                selectionState.setAction(action);
        }
        selectionState.setEnabled(true);
        app.getStateManager().attach(selectionState);
    }

    public void cancelAction() {
        app.getStateManager().detach(selectionState);
    }

    private void checkWinLose() {
        if (scene.getAllys().isEmpty()) {
            StateManager.getStateManager().loadBattleLostState(this);
        }

        if (scene.getEnemies().isEmpty()) {
            StateManager.getStateManager().loadBattleWonState(this);
        }
    }

    public void queueCombatantForRemoval(CombatantNode node) {
        if (node == this.currentTurnNode) {
            resetState(currentTurnNode);
        }
        turnQueue.remove(node);
        this.combatantRemovalList.add(node);
    }

    private void removeCombatants() {
        for (CombatantNode node : this.combatantRemovalList) {
            scene.detachCombatant(node);
            try {
                eventBus.fireCombatantRemovalEvent(node);
            } catch (GameException ex) {
                System.out.println(ex.getMessage());
            }
        }
        checkWinLose();
    }

    public boolean isAllyTurnProcessing() {
        return allyTurnProcessing;
    }

    private void setAllyTurnProcessing(boolean isProcessing) {
        this.allyTurnProcessing = isProcessing;
    }

    public void setScene(BattleScene scene) {
        this.scene = scene;
    }

    public BattleScene getScene() {
        return scene;
    }

    public void pause() {
        this.setEnabled(!this.isEnabled());
    }

    public static BattleState getInstance() {
        return instance;
    }

    public BattleEventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(BattleEventBus eventBus) {
        this.eventBus = eventBus;
    }
}
