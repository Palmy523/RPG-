/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.shapes;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

/**
 *
 * @author Dave
 */
public class Triangle extends Mesh {
    
    public Triangle(Vector3f vector1, Vector3f vector2, Vector3f vector3) {
        super();
        this.setMode(Mode.TriangleFan);
        // Vertex positions in space
        Vector3f [] vertices = new Vector3f[3];
        vertices[0] = vector1;
        vertices[1] = vector2;
        vertices[2] = vector3;

        // Indexes. We define the order in which mesh should be constructed
        short[] indexes = {1, 2, 3};

        // Setting buffers
        this.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        //this.setBuffer(Type.Index, 1, BufferUtils.createShortBuffer(indexes));
        this.updateBound();
        
    }
    
}
