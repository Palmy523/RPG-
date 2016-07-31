/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state.battle;

import game.event.battle.TurnEvent;
import java.util.HashMap;

/**
 *
 * @author Dave
 */
public class TurnEventQueue {

    private HashMap<String, TurnEvent> eventMap;
    private int queueSize;
    private TurnEvent[] events;
    private int addPosition = 0;
    private int turnPosition = 0;

    public TurnEventQueue(int queueSize) {
        this.queueSize = queueSize;
        events = new TurnEvent[queueSize];
        eventMap = new HashMap<>();
    }

    public void push(TurnEvent event) {
        String id = event.getCombatantNode().getCombatant().getId();
        if (contains(id)) {
            return;
        }
        events[addPosition] = event;
        incrementAddPosition();
        eventMap.put(id, event);
    }

    public TurnEvent pop() {
        TurnEvent event = events[turnPosition];
        if (event == null) {
            return null;
        }
        events[turnPosition] = null;
        incrementTurnPosition();
        eventMap.remove(event.getCombatantNode().getCombatant().getId());
        return event;
    }

    /**
     * Increments the add position to allow the next TurnEvent to be added in
     * the appropriate spot in the queue.
     */
    private void incrementAddPosition() {
        if (addPosition >= queueSize - 1) {
            addPosition = 0;
        } else {
            addPosition++;
        }
    }

    /**
     * Increments the queue to set the queue position to get the TurnEvent of
     * the next player in the queue.
     */
    private void incrementTurnPosition() {
        if (turnPosition >= queueSize - 1) {
            turnPosition = 0;
        } else {
            turnPosition++;
        }
    }
    
    public boolean contains(String id) {
        return eventMap.containsKey(id);
    }
}
