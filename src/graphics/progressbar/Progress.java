/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.progressbar;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.scene.Node;

/**
 *
 * @author Dave
 */
public abstract class Progress extends Node {

    protected static enum FillType {

        Vertical, Horizontal, Diagonal
    };
    private int value;
    private int min;
    private int max;
    private ColorRGBA filledColor;
    private ColorRGBA unfilledColor;
    private float width = 2;
    private float height = 0.25f;
    private FillType fillType;
    private int incrementAmount = 1;
    private int decrementAmount = 1;
    private boolean isTransitional = true;
    private ColorRGBA transitionToColor = ColorRGBA.Red;

    public Progress(int value, int min, int max, ColorRGBA color1, ColorRGBA color2) {
        this(value, min, max, color1, color2, Progress.FillType.Horizontal);
    }

    public Progress(int value, int min, int max, ColorRGBA filledColor, ColorRGBA unfilledColor, FillType type) {
        this.min = min;
        this.max = max;
        this.value = value;
        if (value > max) {
            this.value = max;
        }
        if (value < min) {
            this.value = min;
        }
        this.filledColor = filledColor;
        this.unfilledColor = unfilledColor;
        this.fillType = type;

        switch (fillType) {
            case Horizontal:
                break;
            case Vertical:
                this.rotate(0, 0, FastMath.DEG_TO_RAD * 90);
                break;
            case Diagonal:
                this.rotate(0, 0, FastMath.DEG_TO_RAD * 45);
                break;
        }
    }

    public float getFilledValue() {
        return (value != 0) ? (float) value / max : 0;
    }

    public float getUnfilledValue() {
        return 1 - getFilledValue();
    }

    public void increment() {
        this.increment(incrementAmount);
    }

    public void increment(int amount) {
        value += amount;
        if (value > max) {
            value = max;
        }
        update();
    }

    public void decrement() {
        this.decrement(decrementAmount);
    }

    public void decrement(int amount) {
        value -= amount;
        if (value < min) {
            value = min;
        }
        update();
    }

    public abstract void updateFill();

    public abstract void updateFilledColor();

    public abstract void updateUnfilledColor();

    public abstract void createMesh();

    public void update() {
        updateFill();
        updateFilledColor();
        updateUnfilledColor();
    }

    public int getValue() {
        return value;
    }

    public void reset() {
        this.value = 0;
        update();
    }
    
    public void setValue(int value) {
        if (value > max) {
            value = max;
        }
        this.value = value;
        update();
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
        this.update();
    }

    public ColorRGBA getFilledColor() {
        if (isTransitional()) {
            ColorRGBA fill = filledColor;
            float red = fill.getRed();
            float green = fill.getGreen();
            float blue = fill.getBlue();

            float transitionRed = transitionToColor.getRed();
            float transitionGreen = transitionToColor.getGreen();
            float transitionBlue = transitionToColor.getBlue();

            float newRed = Math.abs(red - transitionRed);
            if (red > transitionRed) {
                newRed = red - (newRed * getUnfilledValue());
            } else {
                newRed = red + (newRed * getUnfilledValue());
            }

            float newGreen = Math.abs(green - transitionGreen);
            if (green > transitionGreen) {
                newGreen = green - (newGreen * getUnfilledValue());
            } else {
                newGreen = green + (newGreen * getUnfilledValue());
            }

            float newBlue = Math.abs(blue - transitionBlue);
            if (blue > transitionBlue) {
                newBlue = blue - (newBlue * getUnfilledValue());
            } else {
                newBlue = blue + (newBlue * getUnfilledValue());
            }
            return new ColorRGBA(newRed, newGreen, newBlue, 1);
        }
        return filledColor;
    }

    public void setFilledColor(ColorRGBA color1) {

        this.filledColor = color1;
    }

    public ColorRGBA getUnfilledColor() {

        return unfilledColor;
    }

    public void setUnfilledColor(ColorRGBA color2) {
        this.unfilledColor = color2;
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

    public FillType getFillType() {
        return fillType;
    }

    public void setFillType(FillType fillType) {
        this.fillType = fillType;
    }

    public boolean isTransitional() {
        return isTransitional;
    }

    public boolean isIsTransitional() {
        return isTransitional;
    }

    public void setIsTransitional(boolean isTransitional) {
        this.isTransitional = isTransitional;
    }
}
