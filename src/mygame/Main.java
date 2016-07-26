package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import combatant.component.Combatant;
import combatant.graphics.CombatantNode;
import configuration.GameState;
import java.awt.Dialog;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private static GameState state = new GameState();
    public static Main app;
    
    public static void main(String[] args) {
        app = new Main();
        //app.showStartupMenu();
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        app.setSettings(settings);
        app.settings.setWidth(1280);
        app.settings.setHeight(800);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        Box backdropMesh = new Box(30, 30, .01f);
        Box battlegroundMesh = new Box(20, .01f, 20); 
        
        Geometry backdropGeom = new Geometry("Background", backdropMesh);
        Geometry battlegroundGeom = new Geometry("Floor", battlegroundMesh);
        
        backdropGeom.setLocalTranslation(0, 0, -30);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(0, 0, 255, .5f));
        backdropGeom.setMaterial(mat);

        Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color", ColorRGBA.Brown);
        battlegroundGeom.setMaterial(mat3);
        
        Node battleground = new Node("BattleGround");
        
        CombatantNode combatant = new CombatantNode(Combatant.createDefault("Dude Man"));
        combatant.setLocalTranslation(4, .75f, -2);
        battleground.attachChild(combatant);
        
        CombatantNode combatant2 = new CombatantNode(Combatant.createDefault("Dude Man"));
        combatant2.setLocalTranslation(4, .75f, 2);
        battleground.attachChild(combatant2);

        CombatantNode combatant3 = new CombatantNode(Combatant.createDefault("Dude Man"));
        combatant3.setLocalTranslation(-4, .75f, -2);
        battleground.attachChild(combatant3);
        
        CombatantNode combatant4 = new CombatantNode(Combatant.createDefault("Dude Man"));
        combatant4.setLocalTranslation(-4, .75f, 2);
        battleground.attachChild(combatant4);
        
        battleground.setLocalTranslation(0, -3, -5);
        battleground.rotate(FastMath.DEG_TO_RAD * 10, 0, 0);
        battleground.attachChild(battlegroundGeom);
        
        rootNode.attachChild(backdropGeom);
        rootNode.attachChild(battleground);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    private void showStartupMenu(){
        JFrame battleMenu = new JFrame();
        battleMenu.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        battleMenu.setSize(new Dimension(400, 600));
        battleMenu.setVisible(true);
        battleMenu.setAlwaysOnTop(true);
        battleMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
    }
    
    @Override 
    public AssetManager getAssetManager(){
        return super.getAssetManager();
    }
}
