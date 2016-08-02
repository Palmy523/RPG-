/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.graphics;

import com.jme3.math.ColorRGBA;
import component.battle.combatant.Combatant;
import graphics.progressbar.ProgressCircle;

/**
 *
 * @author Dave
 */
public class ATBGauge extends ProgressCircle {
    
    private static int ABSOLUTE_MAX = 10000;
    private Combatant combatant;
    
    public ATBGauge(Combatant combatant) {
        super(100, 0, 0, 1000, new ColorRGBA(0, 0, 0, 0), ColorRGBA.Cyan);
        createMesh();
        int SPD = combatant.getSPD();
        double ln = Math.log(SPD);
        ln = 5 * ln;
        double sqrt = Math.sqrt(2.25f * SPD);
        this.setMax((int) (ABSOLUTE_MAX - ((ABSOLUTE_MAX / 100) * (ln + sqrt - 1.5))));
    }
    
    public boolean isFull() {
        return this.getValue() >= this.getMax();
    }
}
