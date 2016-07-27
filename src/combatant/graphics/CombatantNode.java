/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package combatant.graphics;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import combatant.component.Combatant;
import graphics.progressbar.stat.HealthBar;
import graphics.progressbar.stat.ManaBar;
import graphics.progressbar.stat.EnduranceBar;
import mygame.Main;

/**
 *
 * @author Dave
 */
public class CombatantNode extends Node {

    private Combatant combatant;
    private EnduranceBar skillBar;
    private HealthBar healthBar;
    private ManaBar manaBar;

    public CombatantNode(Combatant combatant) {
        this.combatant = combatant;

        Box box = new Box(0.5f, 0.75f, 0.5f);
        Geometry player = new Geometry("Combatant", box);
        Material mat = new Material(Main.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Yellow);
        player.setMaterial(mat);

        healthBar = new HealthBar(combatant.getCurrentHealth(), combatant.getMaxHealth());
        healthBar.setLocalTranslation(-(healthBar.getWidth() / 2), 1.8f, 0);
        
        manaBar = new ManaBar(combatant.getCurrentMana(), combatant.getMaxMana());
        manaBar.setLocalTranslation(-(manaBar.getWidth() / 2), 1.5f, 0);
        
        skillBar = new EnduranceBar(combatant.getCurrentSkill(), combatant.getMaxSkill());
        skillBar.setLocalTranslation(-(skillBar.getWidth() / 2), 1.20f, 0);
        skillBar.setValue(25);
        skillBar.update();
        //bar.update();
        //manaBar.update();
        //skillBar.update();
        
        this.attachChild(player);
        this.attachChild(healthBar);
        this.attachChild(manaBar);
        this.attachChild(skillBar);
    }

    public Combatant getCombatant() {
        return combatant;
    }

    public void setCombatant(Combatant combatant) {
        this.combatant = combatant;
    }

    public EnduranceBar getSkillBar() {
        return skillBar;
    }

    public void setSkillBar(EnduranceBar skillBar) {
        this.skillBar = skillBar;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public ManaBar getManaBar() {
        return manaBar;
    }

    public void setManaBar(ManaBar manaBar) {
        this.manaBar = manaBar;
    }
    
    
}
