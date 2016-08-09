/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.shapes;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import game.Game;

/**
 *
 * @author Dave
 */
public class Sword extends Node {
    
    
    public Sword() {
        Box handle = new Box(0.5f, 1, 0.5f);
        Box hilt = new Box(2f, 0.5f, 0.5f);
        Box blade = new Box(1, 3.0f, 0.25f);
        
        Geometry handleGeom = new Geometry("Handle", handle);
        Geometry hiltGeom = new Geometry("Hilt", hilt);
        Geometry bladeGeom = new Geometry("Blad", blade);
        
        Material handleMat = Game.app.getUnshadedMat();
        handleMat.setColor("Color", ColorRGBA.Brown);
        handleGeom.setMaterial(handleMat);
        
        Material hiltMat = Game.app.getUnshadedMat();
        hiltMat.setColor("Color", ColorRGBA.DarkGray);
        hiltGeom.setMaterial(hiltMat);
        
        Material bladeMat = Game.app.getUnshadedMat();
        bladeMat.setColor("Color", ColorRGBA.Gray);
        bladeGeom.setMaterial(bladeMat);
        
        hiltGeom.setLocalTranslation(0, 1.5f, 0);
        bladeGeom.setLocalTranslation(0, 5f, 0);
        
        this.attachChild(handleGeom);
        this.attachChild(hiltGeom);
        this.attachChild(bladeGeom);
    }
}
