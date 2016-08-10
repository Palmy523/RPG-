/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.graphics;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import component.battle.combatant.Combatant;
import game.battle.component.BattleStateModel;
import game.Game;
import graphics.scene.Scene.SceneType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Dave
 */
public class BattleScene extends Node {
    
    private AudioNode backgroundMusic;
    private HashMap<String, CombatantNode> combatantMap;
    private List<CombatantNode> allys;
    private List<CombatantNode> enemies;
    private Node battleground;

    public BattleScene(BattleStateModel model, SceneType type) {
        allys = new ArrayList<>();
        enemies = new ArrayList<>();
        combatantMap = new HashMap<>();
        AssetManager assetManager = Game.app.getAssetManager();
        Box backdropMesh = new Box(30, 30, .01f);
        Box battlegroundMesh = new Box(20, .01f, 20);
        Geometry backdropGeom = new Geometry("Background", backdropMesh);
        Geometry battlegroundGeom = new Geometry("Floor", battlegroundMesh);
        backdropGeom.setLocalTranslation(0, 0, -30);

        switch (type) {
            case DEFAULT:
                
                backgroundMusic = new AudioNode(Game.app.getAssetManager(), "Sounds/Music/Battle Music/Lair_Gorgon_CLOS2.ogg");
                
                Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat.setColor("Color", new ColorRGBA(0, 0, 255, .5f));
                backdropGeom.setMaterial(mat);

                Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat3.setColor("Color", ColorRGBA.Brown);
                battlegroundGeom.setMaterial(mat3);
                break;
        }
        
        battleground = new Node("BattleGround");

        float allyStartX = 5.5f;
        float allyStartY = 0.75f;
        float allyStartZ = 4;
        
        for (Combatant combatant : model.getAllys()) {
            CombatantNode combatantNode = new CombatantNode(combatant, null);
            combatantNode.setLocalTranslation(allyStartX, allyStartY, allyStartZ);
            combatantNode.setStartingPosition(new Vector3f(allyStartX, allyStartY, allyStartZ));
            battleground.attachChild(combatantNode);
            allyStartZ -= 3.5f;
            allys.add(combatantNode);
            combatantMap.put(combatant.getId(), combatantNode);
        }
        
        allyStartZ = 4;
        for (Combatant combatant : model.getEnemies()) {
            CombatantNode combatantNode = new CombatantNode(combatant, null);
            combatantNode.setLocalTranslation(-allyStartX, allyStartY, allyStartZ);
            combatantNode.setStartingPosition(new Vector3f(-allyStartX, allyStartY, allyStartZ));
            battleground.attachChild(combatantNode);
            allyStartZ -= 3.5f;
            enemies.add(combatantNode);
            combatantMap.put(combatant.getId(), combatantNode);
        }
        
        battleground.setLocalTranslation(0, -3, -5);
        battleground.rotate(FastMath.DEG_TO_RAD * 10, 0, 0);
        battleground.attachChild(battlegroundGeom);

        this.attachChild(backdropGeom);
        this.attachChild(battleground);
    }
    
    public void detachCombatant(CombatantNode combatantNode) {
        battleground.detachChild(combatantNode);
        this.enemies.remove(combatantNode);
        this.allys.remove(combatantNode);
        this.combatantMap.remove(combatantNode.getCombatant().getId());
    }

    public HashMap<String, CombatantNode> getCombatantMap() {
        return combatantMap;
    }

    public void setCombatantMap(HashMap<String, CombatantNode> combatantMap) {
        this.combatantMap = combatantMap;
    }

    public AudioNode getBackgroundMusic() {
        return backgroundMusic;
    }

    public List<CombatantNode> getAllys() {
        return allys;
    }

    public void setAllys(List<CombatantNode> allys) {
        this.allys = allys;
    }

    public List<CombatantNode> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<CombatantNode> enemies) {
        this.enemies = enemies;
    }
    
    
}
