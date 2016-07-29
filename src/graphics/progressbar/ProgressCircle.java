/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.progressbar;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import graphics.shapes.FillCircle;
import mygame.Main;

/**
 *
 * @author Dave
 */
public class ProgressCircle extends Progress {

    private FillCircle circle;
    private int samples;
    
    public ProgressCircle(int samples, int value, int min, int max, ColorRGBA color1, ColorRGBA color2, FillType type) {
        super(value, min, max, color1, color2, type);
        this.samples = samples;
    }
    
    public ProgressCircle(int samples, int value, int min, int max, ColorRGBA color1, ColorRGBA color2) {
        super(value, min, max, color1, color2, FillType.Vertical);
        this.samples = samples;
    }
    
    @Override
    public void updateFill() {
        circle.updateFill(getFilledValue());
    }

    @Override
    public void updateFilledColor() {
         
    }

    @Override
    public void updateUnfilledColor() {
    }
    
    public void clearFill() {
        circle.clearFill();
        this.setValue(0);
    }

    @Override
    public void createMesh() {
        Material mat = Main.app.getUnshadedMat();
        mat.setColor("Color", getUnfilledColor());
        circle = new FillCircle(.25f, samples, mat, getFilledValue());
        
        this.attachChild(circle);
    }
    
}
