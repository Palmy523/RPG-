/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.event;

/**
 *
 * @author Dave
 */
public interface GameEventBus {
    
    public void registerEvent(GameEvent event);
    public void deregisterEvent(GameEvent event);
    public void processEvent(GameEvent event);
    
}
