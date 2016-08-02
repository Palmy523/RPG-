/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.graphics;

import com.jme3.math.ColorRGBA;
import component.battle.combatant.Combatant;
import graphics.progressbar.ProgressBar;

/**
 *
 * @author Dave
 */
public class HealthBar extends ProgressBar{
    
    public HealthBar(Combatant combatant) {
        super(combatant.getCurrentHealth(), 0, combatant.getMaxHealth(), 
                new ColorRGBA(0f / 255f, 0f / 255f, 150f / 255f, 1), 
                ColorRGBA.Red, FillType.Horizontal);
        createMesh();
    }
}
