/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.progressbar;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;
import graphics.shapes.BoxOutline2D;
import mygame.Main;

/**
 *
 * @author Dave
 */
public class ProgressBar extends Progress {
    
    private Geometry filledBar;
    private Geometry unfilledBar;
    private BoxOutline2D boxOutline;
    
    public ProgressBar(int value, int min, int max, ColorRGBA color1, ColorRGBA color2, FillType type) {
        super(value, min, max, color1, color2, type);
    }
    
    public ProgressBar(int value, int min, int max, ColorRGBA color1, ColorRGBA color2) {
        super(value, min, max, color1, color2);
    }

    @Override
    public void updateFill() {
        float width = getWidth();
        float height = getHeight();
        float filledValue = getFilledValue();
        float unfilledValue = getUnfilledValue();
        

        
        
        Quad quad = (Quad) filledBar.getMesh();
        quad.updateGeometry(width * filledValue, height);
        
//        quad = (Quad) unfilledBar.getMesh();
//        quad.updateGeometry(width * unfilledValue, height);
//        unfilledBar.setLocalTranslation(filledValue * width, 0, 0);
    }

    @Override
    public void updateFilledColor() {
        filledBar.getMaterial().setColor("Color", getFilledColor());
    }
    
    @Override
    public void updateUnfilledColor() {
//        unfilledBar.getMaterial().setColor("Color", getUnfilledColor());
        
    }

    @Override
    public void createMesh() {
        float width = getWidth();
        float height = getHeight();
        float filledValue = getFilledValue();
//        float unfilledValue = getUnfilledValue();
        ColorRGBA color1 = getFilledColor();
//        ColorRGBA color2 = getUnfilledColor();
        
        Quad quad = new Quad(width * filledValue, height);
        filledBar = new Geometry("Filled", quad);
        Material mat1 = new Material(Main.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", color1);
        filledBar.setMaterial(mat1);
        
//        Quad quad2 = new Quad(width * unfilledValue, height);
//        unfilledBar = new Geometry("Unfilled", quad2);
//        Material mat2 = new Material(Main.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
//        mat2.setColor("Color", color2);
//        unfilledBar.setMaterial(mat2);
//        unfilledBar.setLocalTranslation(filledValue * width, 0, 0);

        Material black = Main.app.getUnshadedMat();
        black.setColor("Color", ColorRGBA.Black);
        boxOutline = new BoxOutline2D(width, height, 4, black);

        this.attachChild(filledBar);
//        this.attachChild(unfilledBar);
        this.attachChild(boxOutline);
    }
    
    
    
}
