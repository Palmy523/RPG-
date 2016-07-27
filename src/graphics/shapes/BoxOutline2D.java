/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.shapes;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Line;

/**
 *
 * @author Dave
 */
public class BoxOutline2D extends Node {
    
    public BoxOutline2D(float width, float height, float lineWidth, Material mat) {
        Vector3f bottomLeft = new Vector3f(0, 0, 0);
        Vector3f topLeft = new Vector3f(0, height, 0);
        Vector3f topRight = new Vector3f(width, height, 0);
        Vector3f bottomRight = new Vector3f(width, 0, 0);
        Line line = new Line(bottomLeft, topLeft);
        line.setLineWidth(lineWidth);
        Line line2 = new Line(topLeft, topRight);
        line2.setLineWidth(lineWidth);
        Line line3 = new Line(topRight, bottomRight);
        line3.setLineWidth(lineWidth);
        Line line4 = new Line(bottomRight, bottomLeft);
        line4.setLineWidth(lineWidth);
        
        Geometry geom = new Geometry("Line", line);
        geom.setMaterial(mat);
        Geometry geom2 = geom.clone();
        geom2.setMesh(line2);
        Geometry geom3 = geom.clone();
        geom3.setMesh(line3);
        Geometry geom4 = geom.clone();
        geom4.setMesh(line4);
        
        this.attachChild(geom);
        this.attachChild(geom2);
        this.attachChild(geom3);
        this.attachChild(geom4);
        
    }
    
    
}
