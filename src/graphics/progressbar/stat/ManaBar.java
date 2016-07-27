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
public class ManaBar extends ProgressBar {

    public ManaBar(int currentMana, int maxMana) {
        super(currentMana, 0, maxMana, ColorRGBA.Blue, ColorRGBA.Red, FillType.Horizontal);
    }
}
