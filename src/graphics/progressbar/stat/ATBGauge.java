/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.progressbar.stat;

import com.jme3.math.ColorRGBA;
import graphics.progressbar.ProgressCircle;

/**
 *
 * @author Dave
 */
public class ATBGauge extends ProgressCircle {
    
    public ATBGauge() {
        super(30, 0, 0, 500, new ColorRGBA(0, 0, 0, 0), ColorRGBA.Cyan);
        createMesh();
    }
}
