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
        super(currentSkillPoints, 0, maxSkillPoints, ColorRGBA.Green, ColorRGBA.Red, FillType.Horizontal);
    }
    
    
}
