/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.battle;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import component.battle.combatant.Combatant;
import component.state.BattleStateModel;
import game.Main;
import graphics.Scene.SceneType;
import graphics.combatant.CombatantNode;

/**
 *
 * @author Dave
 */
public class BattleScene extends Node {
    
    private AudioNode backgroundMusic;

    public BattleScene(BattleStateModel model, SceneType type) {
        AssetManager assetManager = Main.app.getAssetManager();
        Box backdropMesh = new Box(30, 30, .01f);
        Box battlegroundMesh = new Box(20, .01f, 20);
        Geometry backdropGeom = new Geometry("Background", backdropMesh);
        Geometry battlegroundGeom = new Geometry("Floor", battlegroundMesh);
        backdropGeom.setLocalTranslation(0, 0, -30);

        switch (type) {
            case DEFAULT:
                
                backgroundMusic = new AudioNode(Main.app.getAssetManager(), "Sounds/Battle 2 - FFIV.ogg");
                
                Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat.setColor("Color", new ColorRGBA(0, 0, 255, .5f));
                backdropGeom.setMaterial(mat);

                Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat3.setColor("Color", ColorRGBA.Brown);
                battlegroundGeom.setMaterial(mat3);
                break;
        }
        
        Node battleground = new Node("BattleGround");

        float allyStartX = 4;
        float allyStartY = .75f;
        float allyStartZ = 0;
        
        for (Combatant combatant : model.getAllys()) {
            CombatantNode combatantNode = new CombatantNode(combatant);
            combatantNode.setLocalTranslation(allyStartX, allyStartY, allyStartZ);
            battleground.attachChild(combatantNode);
            allyStartZ += 4;
        }
        
        allyStartZ = 0;
        for (Combatant combatant : model.getEnemies()) {
            CombatantNode combatantNode = new CombatantNode(combatant);
            combatantNode.setLocalTranslation(-allyStartX, allyStartY, allyStartZ);
            battleground.attachChild(combatantNode);
            allyStartZ += 4;
        }

        battleground.setLocalTranslation(0, -3, -5);
        battleground.rotate(FastMath.DEG_TO_RAD * 10, 0, 0);
        battleground.attachChild(battlegroundGeom);

        this.attachChild(backdropGeom);
        this.attachChild(battleground);
    }

    public AudioNode getBackgroundMusic() {
        return backgroundMusic;
    }
}
