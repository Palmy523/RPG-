/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state.battle;

import com.jme3.audio.AudioNode;
import game.Main;
import game.state.StateManager;
import graphics.combatant.CombatantNode;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Dave
 */
public class BattleManager {
    
    public static BattleManager instance;
    
    public static BattleManager getInstance() {
        if (instance == null) {
            instance = new BattleManager();
        }
        return instance;
    }
    
    public void processNPCAttack(CombatantNode attacker, BattleState state) {
        List<CombatantNode> targets = state.getScene().getAllys();
        int max = targets.size() - 1;
        Random random = new Random();
        int intTarget = random.nextInt(max + 1);
        CombatantNode target = targets.get(intTarget);
        target.getHealthBar().decrement(attacker.getCombatant().getATK());
        AudioNode node = new AudioNode(Main.app.getAssetManager(), "Sounds/Effects/punch.wav");
        node.setPositional(false);
        node.play();
        if (target.getHealthBar().getValue() == 0) {
            state.getScene().detachCombatant(target);
        }
        
        if (targets.isEmpty()) {
            StateManager.getStateManager().loadBattleLostState(state);
        }
    }
    
    public void attack(CombatantNode attacker, CombatantNode recipient) {
        
    }
    
}
