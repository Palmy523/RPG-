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
public class EnduranceBar extends ProgressBar {
    
    public EnduranceBar(int currentSkillPoints, int maxSkillPoints) {
        super(currentSkillPoints, 0, maxSkillPoints, new ColorRGBA( 0f / 255f, 150f / 255f, 0f / 255f, 1), ColorRGBA.Red, FillType.Horizontal);
        createMesh();
    }
    
    
}
