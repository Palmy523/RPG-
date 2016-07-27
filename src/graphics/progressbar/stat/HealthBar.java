/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.progressbar.stat;

import com.jme3.math.ColorRGBA;
import graphics.progressbar.ProgressBar;

/**
 *
 * @author Dave
 */
public class HealthBar extends ProgressBar{
    
    public HealthBar(int currentHealth, int maxHealth) {
        super(currentHealth, 0, maxHealth, new ColorRGBA(102f / 255f, 51f / 255f, 153f / 255f, 1), ColorRGBA.Red, FillType.Horizontal);
    }
}
