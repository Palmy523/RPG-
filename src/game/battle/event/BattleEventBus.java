/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.event;

import game.battle.action.BattleAction;
import game.battle.graphics.CombatantNode;
import game.error.GameException;
import game.event.GameEvent;
import game.eventhandler.GameEventHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Dave
 */
public class BattleEventBus {

    public static enum BattleEventType {ON_TURN_EVENT, SELECTION_EVENT, ACTION_EVENT} 
    private HashMap<Class<?>, List<GameEventHandler>> eventMap;
    private HashMap<Class<?>, GameEvent> events;
    
    public BattleEventBus() {
        eventMap = new HashMap<>();
        events = new HashMap<>();
        
        OnTurnEvent turnEvent = new OnTurnEvent();
        eventMap.put(turnEvent.getClass(), new ArrayList<GameEventHandler>());
        events.put(turnEvent.getClass(), turnEvent);
        
        ActionEvent actionEvent = new ActionEvent();
        eventMap.put(actionEvent.getClass(), new ArrayList<GameEventHandler>());
        events.put(actionEvent.getClass(), actionEvent);
        
        CombatantDeathEvent deathEvent = new CombatantDeathEvent();
        eventMap.put(deathEvent.getClass(), new ArrayList<GameEventHandler>());
        events.put(deathEvent.getClass(), deathEvent);
        
        TargetSelectionEvent selectionEvent = new TargetSelectionEvent();
        eventMap.put(selectionEvent.getClass(), new ArrayList<GameEventHandler>());
        events.put(selectionEvent.getClass(), selectionEvent);
        
        ReceivedActionEvent receivedActionEvent = new ReceivedActionEvent();
        eventMap.put(receivedActionEvent.getClass(), new ArrayList<GameEventHandler>());
        events.put(receivedActionEvent.getClass(), receivedActionEvent);
    }
    
    public void fireCombatantRemovalEvent(CombatantNode combatantNode) throws GameException {
        Class<?> clazz = CombatantDeathEvent.class;
        if (!eventMap.containsKey(clazz)) {
            throw new GameException("Event Map does not contain the specified event: "
                    + clazz);
        }

        for (GameEventHandler handler : eventMap.get(clazz)) {
            CombatantDeathEvent event = (CombatantDeathEvent) events.get(clazz);
            event.setCombatantNode(combatantNode);
            handler.onEvent(event);
        }
    }
    
    /**
     * Fires the on turn event for the combatantNode.
     * 
     * @param combatantNode the combatantNode to fire the onTurnEvent for.
     * @throws GameException
     */
    public void fireOnTurnEvent(CombatantNode combatantNode) throws GameException {
        Class<?> clazz = OnTurnEvent.class;
        if (!eventMap.containsKey(clazz)) {
            throw new GameException("Event Map does not contain the specified event: " 
                    + clazz);
        }
        
        for (GameEventHandler handler : eventMap.get(clazz)) {
            OnTurnEvent event = (OnTurnEvent) events.get(clazz);
            event.setCombatantNode(combatantNode);
            handler.onEvent(event);
        }
    }
    
    public void fireOnActionEvent(CombatantNode actor, BattleAction action, List<CombatantNode> targets) throws GameException {
        Class<?> clazz = ActionEvent.class;
        if (!eventMap.containsKey(clazz)) {
            throw new GameException("Event Map does not contain the specified event: "
                    + clazz);
        }
        
        for (GameEventHandler handler : eventMap.get(clazz)) {
            ActionEvent event = (ActionEvent) events.get(clazz);
            event.setActor(actor);
            event.setAction(action);
            event.setTargets(targets);
            handler.onEvent(event);
        }
    }

    public void fireTargetSelectionEvent(BattleAction action) throws GameException {
        Class<?> clazz = TargetSelectionEvent.class;
        if (!eventMap.containsKey(clazz)) {
            throw new GameException("Event Map does not contain the specified event: "
                    + clazz);
        }
        
        for (GameEventHandler handler : eventMap.get(clazz)) {
            TargetSelectionEvent event = (TargetSelectionEvent) events.get(clazz);
            event.setAction(action);
            event.setTargetSelectionType(action.getTargetSelectionType());
            handler.onEvent(event);
        }
    }
    
    public void fireReceivedActionEvent(BattleAction action, CombatantNode target) throws GameException {
        Class<?> clazz = ReceivedActionEvent.class;
        if (!eventMap.containsKey(clazz)) {
            throw new GameException("Event Map does not contain the specified event: "
                    + clazz);
        }

        for (GameEventHandler handler : eventMap.get(clazz)) {
            ReceivedActionEvent event = (ReceivedActionEvent) events.get(clazz);
            event.setAction(action);
            event.setCombatantNode(target);
            handler.onEvent(event);
        }
    }
    
    
    public void registerHandler(Class<?> clazz, GameEventHandler handler) {
        if (!eventMap.containsKey(clazz)) {
            eventMap.put(clazz, new ArrayList<GameEventHandler>());
        }
        eventMap.get(clazz).add(handler);
    }
    
    public void registerEvent(GameEvent event) {
        eventMap.putIfAbsent(event.getClass(), new ArrayList<GameEventHandler>());
        events.putIfAbsent(event.getClass(), event);
    }
    
    
}
