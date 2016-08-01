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
        super(currentHealth, 0, maxHealth, new ColorRGBA(0f / 255f, 0f / 255f, 150f / 255f, 1), ColorRGBA.Red, FillType.Horizontal);
        createMesh();
    }
}
