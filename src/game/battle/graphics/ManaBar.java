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
public class ManaBar extends ProgressBar {

    public ManaBar(Combatant combatant) {
        super(combatant.getCurrentMana(), 0, combatant.getMaxMana(), 
                new ColorRGBA(190f / 255f, 190f / 255f, 190f / 255f, 1), 
                ColorRGBA.Red, FillType.Horizontal);
        createMesh();
    }
}
