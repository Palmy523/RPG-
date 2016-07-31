/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.shapes;

import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dave
 */
public class FillCircle extends Node {

    private int samples;
    private List<Geometry> triangles;
    private float fill;
    private Geometry triangleGeom;
    private float angle;
    
    public FillCircle(float radius, int samples, Material mat, float fill) {
        
        this.fill = fill;
        this.samples = samples;
        triangles = new ArrayList<>();
        
        angle = (float) 360/samples;
        float angleB = (180 - angle) / 2;
        float rSq = (float) Math.pow(radius, 2);
        float edgeWidth = (float) (radius * Math.sin(FastMath.DEG_TO_RAD * angle) 
                / Math.sin(FastMath.DEG_TO_RAD * angleB)) / 2;
        float segmentHeight = (float) Math.sqrt(rSq - Math.pow(edgeWidth, 2));
        Vector3f center = new Vector3f(0, 0, 0);
        Vector3f right = new Vector3f((float)edgeWidth, segmentHeight, 0);
        Vector3f left = new Vector3f((float)-edgeWidth, segmentHeight, 0);
        Triangle triangle = new Triangle(right, left, center);
        triangleGeom = new Geometry("Triangle", triangle);
        triangleGeom.setMaterial(mat);
        
        int numFills = (int) (samples * fill);
        for (int i = 0; i < numFills; i++) {
            addTriangle();
        }
        
    }
    
    public void clearFill() {
        this.detachAllChildren();
        this.triangles.clear();
    }
    
    public void addTriangle() {
            this.attachChild(triangleGeom);
            this.triangles.add(triangleGeom);
            triangleGeom = triangleGeom.clone();
            triangleGeom.rotate(0, 0, FastMath.DEG_TO_RAD * angle);
    }
    
    public void removeTriangle() {
        this.detachChild(triangleGeom);
        this.triangles.remove(triangleGeom);
        triangleGeom = triangleGeom.clone();
        triangleGeom.rotate(0, 0, FastMath.DEG_TO_RAD * -angle);
    }

    public void updateFill(float fill) {
        this.fill = fill;
        int numFills = (int) (samples * fill);
        int amountFilled = triangles.size();
        
        while (amountFilled > numFills) {
            removeTriangle();
            amountFilled--;
        }
        
        while (amountFilled < numFills) {
            addTriangle();
            amountFilled++;
        }
    }
    
    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public List<Geometry> getTriangles() {
        return triangles;
    }

    public void setTriangles(List<Geometry> triangles) {
        this.triangles = triangles;
    }
    
    
}
