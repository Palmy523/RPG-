/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.event;

import com.jme3.audio.AudioNode;
import game.Game;
import game.battle.BattleState;
import game.battle.graphics.CombatantNode;

/**
 *
 * @author Dave
 */
public class AllyTurnEvent extends OnTurnEvent {
    
    private boolean awaitingUserInput = false;
    private boolean eventFired = false;
    private AudioNode audioNode ;
    
    public AllyTurnEvent(BattleState state, CombatantNode node) {
        audioNode = new AudioNode(Game.app.getAssetManager(), "Sounds/Effects/shotgun.wav");
        audioNode.setPositional(false);
    }
    
    public void fireEvent() {
//        this.getCombatantNode().turnHighlight(true);
//        Game.app.getStateManager().attach(new BattleActionBar(this.getBattleState(), this.getCombatantNode()));
//        audioNode.play();
//        eventFired = true;
    }

    
    
    
    
}
