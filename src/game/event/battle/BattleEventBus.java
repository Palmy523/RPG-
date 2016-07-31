/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.event.battle;

import game.event.AbstractGameEventBus;
import game.event.GameEvent;

/**
 *
 * @author Dave
 */
public class BattleEventBus extends AbstractGameEventBus {

    @Override
    public void processEvent(GameEvent event) {
        event.fireEvent();
    }
    
    
}
