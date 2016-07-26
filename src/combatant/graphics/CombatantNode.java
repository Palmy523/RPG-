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
import graphics.ProgressBar;
import mygame.Main;

/**
 *
 * @author Dave
 */
public class CombatantNode extends Node {
    
    private Combatant combatant;
    
    public CombatantNode(Combatant combatant) {
        this.combatant = combatant;
        
        Box box = new Box(0.5f, 0.75f, 0.5f);
        Geometry player = new Geometry("Combatant", box);
        Material mat = new Material(Main.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Yellow);
        player.setMaterial(mat);
        
        ProgressBar bar = new ProgressBar(combatant.getCurrentHealth(), 0, combatant.getMaxHealth(), ColorRGBA.Green, ColorRGBA.Red);
        bar.setLocalTranslation(-(bar.getWidth() / 2), 1.25f, 0);
        
        this.attachChild(player);
        this.attachChild(bar);
    }
    
}
