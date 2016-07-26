/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import mygame.Main;

/**
 *
 * @author Dave
 */
public class ProgressBar extends Node {
   
    private int value;
    private int min;
    private int max;
    private ColorRGBA color1;
    private ColorRGBA color2;
    private float width = 2;
    private float height = 0.25f;
    
    private Geometry filledBar;
    private Geometry unfilledBar;
    
    private int incrementAmount = 1;
    private int decrementAmount = 1;
    
    public ProgressBar(int value, int min, int max, ColorRGBA color1, ColorRGBA color2){
        this.value = value;
        this.min = min;
        this.max = max;
        if (value < min) value = min;
        if (value > max) value = max;
        float filledValue = (value != 0) ? (float) value/max : 0;
        float unfilledValue = 1 - filledValue;
        this.color1 = color1;
        this.color2 = color2;
        
        Quad quad = new Quad(width * filledValue, height);
        System.out.println("Filled Value: " + width * filledValue);
        System.out.println("Unfilled Value: " + unfilledValue * width);
        filledBar = new Geometry("Health Bar", quad);
        Material mat1 = new Material(Main.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", color1);
        filledBar.setMaterial(mat1);
        
        
        Quad quad2 = new Quad(width * unfilledValue, height);
        unfilledBar = new Geometry("Health Bar", quad2);
        Material mat2 = new Material(Main.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", color2);
        unfilledBar.setMaterial(mat2);
        unfilledBar.setLocalTranslation(filledValue * width, 0, 0);

        
        this.attachChild(filledBar);
        this.attachChild(unfilledBar);

    }

    
    public void increment() {
        this.increment(incrementAmount);
    }
    
    public void increment(int amount) {
       if (amount + value < max) {
           value += amount;
       } else {
           value = max;
       }
       
       updateBar();
    }
    
    public void decrement() {
        this.decrement(decrementAmount);
    }
    
    public void decrement(int amount) {
        if (value - amount > min) {
            value -= amount;
        } else {
            value = min;
        }
        
        updateBar();
    }
    
    public void updateBar() {
        float filledValue = (value != 0) ? value/max : 0;
        float unfilledValue = 1 - filledValue;
        
        Quad filled = (Quad) filledBar.getMesh();
        filled.updateGeometry(filledValue * width, height);
        
        Quad unfilled = (Quad) unfilledBar.getMesh();
        unfilled.updateGeometry(unfilledValue * width, height);
        
        unfilledBar.setLocalTranslation(filledValue * width, 0, 0);
    }
    
    public void updateColor() {
        filledBar.getMaterial().setColor("Color", color1);
        unfilledBar.getMaterial().setColor("Color", color2);
    }
    
    public void update() {
        updateBar();
        updateColor();
        updateColor();
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    


    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public ColorRGBA getColor1() {
        return color1;
    }

    public void setColor1(ColorRGBA color1) {
        this.color1 = color1;
    }

    public ColorRGBA getColor2() {
        return color2;
    }

    public void setColor2(ColorRGBA color2) {
        this.color2 = color2;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Geometry getFilledBar() {
        return filledBar;
    }

    public void setFilledBar(Geometry filledBar) {
        this.filledBar = filledBar;
    }

    public Geometry getUnfilledBar() {
        return unfilledBar;
    }

    public void setUnfilledBar(Geometry unfilledBar) {
        this.unfilledBar = unfilledBar;
    }

    public int getIncrementAmount() {
        return incrementAmount;
    }

    public void setIncrementAmount(int incrementAmount) {
        this.incrementAmount = incrementAmount;
    }

    public int getDecrementAmount() {
        return decrementAmount;
    }

    public void setDecrementAmount(int decrementAmount) {
        this.decrementAmount = decrementAmount;
    }
    
    
}
