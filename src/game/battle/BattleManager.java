/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle;

import com.jme3.audio.AudioNode;
import game.Main;
import game.battle.graphics.CombatantNode;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Dave
 */
public class BattleManager {
    
    public static BattleManager instance;
    public static BattleState state;
    
    public static BattleManager getInstance() {
        if (instance == null) {
            instance = new BattleManager();
        }
        return instance;
    }
    
    public CombatantNode processNPCAttack(CombatantNode attacker, BattleState state) {
        List<CombatantNode> targets = state.getScene().getAllys();
        int max = targets.size() - 1;
        Random random = new Random();
        int intTarget = random.nextInt(max + 1);
        CombatantNode target = targets.get(intTarget);
        target.getHealthBar().decrement(attacker.getCombatant().getATK());
        AudioNode node = new AudioNode(Main.app.getAssetManager(), "Sounds/Effects/punch.wav");
        node.setPositional(false);
        node.play();
        return target;
    }
    
    public CombatantNode attack(CombatantNode actor, CombatantNode target) {
        target.getHealthBar().decrement(actor.getCombatant().getATK());
        AudioNode node = new AudioNode(Main.app.getAssetManager(), "Sounds/Effects/punch.wav");
        node.setPositional(false);
        node.play();
        if (target.getHealthBar().getValue() == 0) {
            state.queueCombatantForRemoval(target);
        }
        return target;
    }
    
    public int computeAttackDamage(CombatantNode actor, CombatantNode recipient) {
        return (actor.getCombatant().getATK());
    }
    
    public static void setBattleState(BattleState state) {
        BattleManager.state = state;
    }
}
