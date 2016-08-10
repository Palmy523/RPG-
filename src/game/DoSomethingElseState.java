/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import component.battle.combatant.Combatant;
import game.battle.graphics.CombatantNode;
import game.battle.graphics.animation.attack.SlashFullRotation;
import game.state.AbstractGameState;

/**
 *
 * @author Dave
 */
public class DoSomethingElseState extends AbstractGameState {

    private float interp = 0;
    private float speed = 2.0f;
    private CombatantNode node;
    
    public DoSomethingElseState() {
    }

    @Override
    public void initialize(AppStateManager manager, Application app) {
        super.initialize(manager, app);
        Combatant ally1 = new Combatant();
        ally1.setId("0");
        ally1.setMaxHealth(100);
        ally1.setCurrentHealth(100);
        ally1.setName("Dudeman");
        ally1.setMaxMana(999);
        ally1.setCurrentMana(999);
        ally1.setMaxEND(999);
        ally1.setCurrentEND(999);
        ally1.setSPD(1);
        ally1.setATK(10);
        node = new CombatantNode(ally1, "Whatev...");
        
        this.getApp().getRootNode().attachChild(node);
        this.getStateManager().attach(new SlashFullRotation(node.getSword()));
    }

    @Override
    public void update(float tpf) {
    }
}
