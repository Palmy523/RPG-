/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.event;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dave
 */
public abstract class AbstractGameEventBus implements GameEventBus {
    
    public List<GameEvent> events;
    
    public AbstractGameEventBus() {
        events = new ArrayList<>();
    }
    
    public void processAllEvents() {
        for (GameEvent event : events) {
            processEvent(event);
        }
    }
    
    @Override
    public void registerEvent(GameEvent event) {
        events.add(event);
    }
    
    @Override
    public void deregisterEvent(GameEvent event) {
        events.remove(event);
    }
    
}
