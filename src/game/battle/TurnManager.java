/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle;

import game.battle.graphics.CombatantNode;

/**
 *
 * @author Dave
 */
public interface TurnManager {
    
    public CombatantNode getCurrentTurn();
    public CombatantNode next();
    public boolean hasTurn();
    
    
}
