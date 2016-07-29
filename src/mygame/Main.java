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
import graphics.progressbar.ProgressCircle;
import graphics.progressbar.stat.ATBGauge;
import graphics.progressbar.stat.EnduranceBar;
import graphics.progressbar.stat.HealthBar;
import graphics.progressbar.stat.ManaBar;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mygame.Main.app;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static Main app;
    public static String unshadedMat = "Common/MatDefs/Misc/Unshaded.j3md";
    public CombatantNode combatant;
    public ProgressCircle atbGauge;
    
    public static void main(String[] args) {
        app = new Main();
        //app.showStartupMenu();
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        app.setSettings(settings);
        app.settings.setWidth(1280);
        app.settings.setHeight(800);
        app.settings.setSamples(32);
        app.start();
        System.out.println((int) 5.75);
        

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
        
        combatant = new CombatantNode(Combatant.createDefault("Dude Man"));
        combatant.getHealthBar().setValue(100);
        combatant.getHealthBar().update();
        combatant.getManaBar().setValue(100);
        combatant.getManaBar().update();
        combatant.getSkillBar().setValue(100);
        combatant.getSkillBar().update();
        combatant.setLocalTranslation(4, .75f, -2);
        battleground.attachChild(combatant);
        
        CombatantNode combatant2 = new CombatantNode(Combatant.createDefault("Dude Man"));
        combatant2.getHealthBar().setValue(40);
        combatant2.getHealthBar().update();
        combatant2.setLocalTranslation(4, .75f, 2);
        battleground.attachChild(combatant2);

        CombatantNode combatant3 = new CombatantNode(Combatant.createDefault("Dude Man"));
        combatant3.getSkillBar().setValue(100);
        combatant3.getSkillBar().update();
        combatant3.setLocalTranslation(-4, .75f, -2);
        battleground.attachChild(combatant3);
        
        CombatantNode combatant4 = new CombatantNode(Combatant.createDefault("Dude Man"));
        combatant4.getManaBar().setValue(100);
        combatant4.getManaBar().update(); 
        combatant4.getHealthBar().setValue(50);
        combatant4.getHealthBar().update();
        combatant4.setLocalTranslation(-4, .75f, 2);
        battleground.attachChild(combatant4);
        
        battleground.setLocalTranslation(0, -3, -5);
        battleground.rotate(FastMath.DEG_TO_RAD * 10, 0, 0);
        battleground.attachChild(battlegroundGeom);
        
        Material mat4 = this.getUnshadedMat();
        mat4.setColor("Color", ColorRGBA.Red);
        
        
        rootNode.attachChild(backdropGeom);
        rootNode.attachChild(battleground);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        HealthBar bar = combatant.getHealthBar();
        ManaBar manabar = combatant.getManaBar();
        EnduranceBar skillBar = combatant.getSkillBar();
        atbGauge = combatant.getATBGauge();
        bar.decrement();
        manabar.decrement();
        skillBar.decrement();
        atbGauge.increment();
        if (atbGauge.getValue() == atbGauge.getMax()) {
            atbGauge.clearFill();
        }
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
        
 
    }
    
    @Override 
    public AssetManager getAssetManager(){
        return super.getAssetManager();
    }
    
    public Material getUnshadedMat() {
        return new Material(getAssetManager(), Main.unshadedMat);
    }
}
