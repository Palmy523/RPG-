/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.eventhandler;

import game.event.GameEvent;

/**
 *
 * @author Dave
 */
public interface GameEventHandler {
    
    public void onEvent(GameEvent event);
}
