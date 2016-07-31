/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.combatant;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import component.battle.combatant.Combatant;
import graphics.progressbar.stat.ATBGauge;
import graphics.progressbar.stat.HealthBar;
import graphics.progressbar.stat.ManaBar;
import graphics.progressbar.stat.EnduranceBar;
import game.Main;

/**
 *
 * @author Dave
 */
public class CombatantNode extends Node {

    
    private Combatant combatant;
    private EnduranceBar skillBar;
    private HealthBar healthBar;
    private ManaBar manaBar;
    private ATBGauge atbGauge;
    private Geometry combatantGeom;

    public CombatantNode(Combatant combatant) {
        this.combatant = combatant;
        
        Box box = new Box(0.5f, 0.75f, 0.5f);
        combatantGeom = new Geometry("Combatant", box);
        Material mat = new Material(Main.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        switch(combatant.getType()) {
            case Ally : mat.setColor("Color", ColorRGBA.Green.mult(0.75f)); break;
            case Enemy : mat.setColor("Color", ColorRGBA.Red.mult(0.75f)); break;
            case Neutral : mat.setColor("Color", ColorRGBA.Yellow); break;
        }
        combatantGeom.setMaterial(mat);

        healthBar = new HealthBar(combatant.getCurrentHealth(), combatant.getMaxHealth());
        healthBar.setLocalTranslation(-(healthBar.getWidth() / 2), 1.8f, 0);
        
        manaBar = new ManaBar(combatant.getCurrentMana(), combatant.getMaxMana());
        manaBar.setLocalTranslation(-(manaBar.getWidth() / 2), 1.5f, 0);
        
        skillBar = new EnduranceBar(combatant.getCurrentEND(), combatant.getMaxEND());
        skillBar.setLocalTranslation(-(skillBar.getWidth() / 2), 1.20f, 0);
        
        atbGauge = new ATBGauge();
        atbGauge.setLocalTranslation(-1.5f, 1.5f, 0);
        
        this.attachChild(combatantGeom);
        this.attachChild(healthBar);
        this.attachChild(manaBar);
        this.attachChild(skillBar);
        this.attachChild(atbGauge);
    }
    
    public void incrementATBGauge() {
        incrementATBGauge(1);
    }
    
    public void incrementATBGauge(int value) {
        this.atbGauge.increment(value);
    }

    public ATBGauge getAtbGauge() {
        return atbGauge;
    }

    public void setAtbGauge(ATBGauge atbGauge) {
        this.atbGauge = atbGauge;
    }

    public Geometry getCombatantGeom() {
        return combatantGeom;
    }

    public void setCombatantGeom(Geometry combatantGeom) {
        this.combatantGeom = combatantGeom;
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
