/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle;

import com.jme3.audio.AudioNode;
import game.Game;
import game.battle.action.AttackAction;
import game.battle.action.BattleAction;
import game.battle.graphics.CombatantNode;
import java.util.ArrayList;
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
    
    public void processNPCAttack(CombatantNode attacker, BattleState state) {
        AttackAction action = new AttackAction(attacker);
        List<CombatantNode> targets = state.getScene().getAllys();
        int max = targets.size() - 1;
        Random random = new Random();
        int intTarget = random.nextInt(max + 1);
        CombatantNode target = targets.get(intTarget);
        List<CombatantNode> targetsList = new ArrayList<>();
        targetsList.add(target);
        action.setTargets(targetsList);
        state.processCombatantAction(attacker, action);
    }
    
    public void attack(CombatantNode actor, CombatantNode target) {
        target.getHealthBar().decrement(actor.getCombatant().getATK());
        AudioNode node = new AudioNode(Game.app.getAssetManager(), "Sounds/Effects/punch.wav");
        node.setPositional(false);
        node.play();
        if (target.getHealthBar().getValue() == 0) {
            state.queueCombatantForRemoval(target);
        }
        state.resetState(actor);
    }
    
    public void processAction(BattleAction action, boolean pauseATB) {
        state.pauseATBUpdate(pauseATB);
        
    }
    
    public int computeAttackDamage(CombatantNode actor, CombatantNode recipient) {
        return (actor.getCombatant().getATK());
    }
    
    public static void setBattleState(BattleState state) {
        BattleManager.state = state;
    }
}
