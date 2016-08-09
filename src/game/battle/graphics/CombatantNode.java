/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.graphics;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import component.battle.combatant.Combatant;
import component.battle.combatant.Combatant.CombatantType;
import game.Game;
import game.battle.component.Attacks;
import game.battle.component.Moves;
import game.battle.graphics.animation.attack.BasicSlashAnimation;
import game.graphics.animation.AbstractAnimation;
import graphics.shapes.Sword;

/**
 *
 * @author Dave
 */
public class CombatantNode extends Node implements Moves, Attacks {

    
    private Combatant combatant;
    private EnduranceBar skillBar;
    private HealthBar healthBar;
    private ManaBar manaBar;
    private ATBGauge atbGauge;
    private Geometry combatantGeom;
    private Geometry selectionCursor;
    private ColorRGBA original;
    private Vector3f startingPosition;
    private Sword sword;

    public CombatantNode(Combatant combatant, String id) {
        this.combatant = combatant;
        
        Box selectionBox = new Box(0.75f, .05f, 0.75f);
        selectionCursor = new Geometry("Cursor", selectionBox);
        Material cursorMat = Game.app.getUnshadedMat();
        cursorMat.setColor("Color", ColorRGBA.White);
        selectionCursor.setMaterial(cursorMat);
        selectionCursor.setLocalTranslation(0, -0.70f, 0);
        
        Box box = new Box(0.5f, 0.75f, 0.5f);
        combatantGeom = new Geometry("Combatant", box);
        Material mat = new Material(Game.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        switch(combatant.getType()) {
            case Ally : original = ColorRGBA.Green.mult(0.75f); break;
            case Enemy : original = ColorRGBA.Red.mult(0.75f); break;
            case Neutral : original = ColorRGBA.Yellow; break;
        }
        mat.setColor("Color", original);
        combatantGeom.setMaterial(mat);

        healthBar = new HealthBar(combatant);
        healthBar.setLocalTranslation(-(healthBar.getWidth() / 2), 1.8f, 0);
        
        manaBar = new ManaBar(combatant);
        manaBar.setLocalTranslation(-(manaBar.getWidth() / 2), 1.5f, 0);
        
        skillBar = new EnduranceBar(combatant);
        skillBar.setLocalTranslation(-(skillBar.getWidth() / 2), 1.20f, 0);
        
        atbGauge = new ATBGauge(combatant);
        atbGauge.setLocalTranslation(-1.5f, 1.5f, 0);
        
        sword = new Sword();
        sword.scale(.2f);
        sword.rotate(0, 0, FastMath.DEG_TO_RAD * 45);
        sword.setLocalTranslation(0, 0, 1);
        if (combatant.getType() == CombatantType.Enemy) {
            sword.rotate(0, FastMath.DEG_TO_RAD * 180, 0);
        }
        
        this.attachChild(combatantGeom);
        this.attachChild(healthBar);
        this.attachChild(manaBar);
        this.attachChild(skillBar);
        this.attachChild(atbGauge);
        this.attachChild(sword);
        
    }
    
    public void select(boolean select) {
        if (select) {
            this.attachChild(selectionCursor);
            return;
        }
        this.detachChild(selectionCursor);
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

    public void turnHighlight(boolean highlight) {
        if (highlight) {
            this.combatantGeom.getMaterial().setColor("Color", ColorRGBA.White);
        } else {
            this.combatantGeom.getMaterial().setColor("Color", original);
        }
        
    }
    
    public void receiveAttack(CombatantNode actor) {
    }
    
    public void setHealth(int health) {
        this.combatant.setCurrentHealth(health);
        this.getHealthBar().setValue(health);
    }

    public Vector3f getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Vector3f startingPosition) {
        this.startingPosition = startingPosition;
    }

    @Override
    public AbstractAnimation getMoveAnimation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractAnimation getAttackAnimation() {
        return new BasicSlashAnimation(sword);
    }
}
