/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.component;

import component.battle.combatant.Combatant;
import graphics.scene.Scene.SceneType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dave
 */
public class BattleStateModel {

    private SceneType type;
    private List<Combatant> allys;
    private List<Combatant> enemies;
    private List<Combatant> neutrals;

    public BattleStateModel() {
        allys = new ArrayList<>();
        enemies = new ArrayList<>();
        neutrals = new ArrayList<>();
    }

    public SceneType getType() {
        return type;
    }

    public void setType(SceneType type) {
        this.type = type;
    }

    public List<Combatant> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Combatant> enemies) {
        this.enemies = enemies;
    }

    public List<Combatant> getAllys() {
        return allys;
    }

    public void setAllys(List<Combatant> allys) {
        this.allys = allys;
    }

    public List<Combatant> getNeutrals() {
        return neutrals;
    }

    public void setNeutrals(List<Combatant> neutral) {
        this.neutrals = neutral;
    }
    
    
    
    
    
    
    
}
