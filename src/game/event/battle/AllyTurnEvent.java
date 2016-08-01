/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.event.battle;

import com.jme3.audio.AudioNode;
import com.jme3.math.ColorRGBA;
import game.Main;
import game.state.battle.BattleState;
import game.state.menu.battle.BattleActionBar;
import graphics.combatant.CombatantNode;

/**
 *
 * @author Dave
 */
public class AllyTurnEvent extends TurnEvent {
    
    private boolean awaitingUserInput = false;
    private boolean eventFired = false;
    private AudioNode audioNode ;
    
    public AllyTurnEvent(BattleState state, CombatantNode node) {
        super(state, node);
        audioNode = new AudioNode(Main.app.getAssetManager(), "Sounds/Effects/shotgun.wav");
        audioNode.setPositional(false);
    }
    
    @Override
    public void fireEvent() {
        this.getCombatantNode().getCombatantGeom().getMaterial().setColor("Color", ColorRGBA.White);
        Main.app.getStateManager().attach(new BattleActionBar());
        audioNode.play();
        eventFired = true;
    }

    @Override
    public boolean isAwaitingUserInput() {
        return awaitingUserInput;
    }

    @Override
    public void setAwaitingUserInput(boolean awaitingUserInput) {
        this.awaitingUserInput = awaitingUserInput;
    }
    
    @Override
    public boolean isEventFired() {
        return eventFired;
    }
    
    
    
    
}
