package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import component.combatant.Combatant;
import graphics.combatant.CombatantNode;
import graphics.progressbar.ProgressCircle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mygame.Main.app;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static Main app;
    public static String unshadedMat = "Common/MatDefs/Misc/Unshaded.j3md";
    public CombatantNode combatant;
    public ProgressCircle atbGauge;
    public Node node;
    public Node menuNode;
    public float xPos;
    public static float menuFactor = 0.00049765625f;
    public static int screenWidth = 1280;
    public static int screenHeight = 800;
    public boolean turnTaken = false;
    public Geometry highlightTurnGeom;

    public static void main(String[] args) {
        app = new Main();
        //app.showStartupMenu();
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        app.setSettings(settings);
        app.settings.setWidth(screenWidth);
        app.settings.setHeight(screenHeight);
        app.settings.setSamples(32);
        app.start();
        System.out.println((int) 5.75);


    }

    @Override
    public void simpleInitApp() {
        AudioNode backgroundMusic = new AudioNode(assetManager, "Sounds/Battle.ogg", true);
        backgroundMusic.setPositional(false);
        backgroundMusic.play();
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
        atbGauge = combatant.getATBGauge();
        battleground.attachChild(combatant);

        CombatantNode combatant2 = new CombatantNode(Combatant.createDefault("Dude Man"));
        combatant2.getHealthBar().setValue(40);
        combatant2.getHealthBar().update();
        combatant2.setLocalTranslation(4, .75f, 2);
        battleground.attachChild(combatant2);

                
        Combatant combatant = Combatant.createDefault("Whatever Man");
        combatant.setType(Combatant.CombatantType.Enemy);
        CombatantNode combatant3 = new CombatantNode(combatant);
        combatant3.getSkillBar().setValue(100);
        combatant3.getSkillBar().update();
        combatant3.setLocalTranslation(-4, .75f, -2);
        battleground.attachChild(combatant3);

        combatant = Combatant.createDefault("Bling");
        combatant.setType(Combatant.CombatantType.Enemy);
        CombatantNode combatant4 = new CombatantNode(combatant);
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
        mat4.setColor("Color", ColorRGBA.DarkGray);

        node = new Node();
        Box menu = new Box(.1f, .4f, 0.0001f);
        Geometry menuGeom = new Geometry("Menu", menu);
        menuGeom.setMaterial(mat4);
        menuGeom.setLocalTranslation(cam.getLocation());
        menuGeom.move((float) screenWidth * menuFactor, 0, -8.89f);
        
        Box highlightBox = new Box(1.5f, .01f, 0);
        highlightTurnGeom = new Geometry("highlightBox", highlightBox);
        Material highlightMat = getUnshadedMat();
        highlightMat.setColor("Color", ColorRGBA.White);
        
        //CameraNode camNode = new CameraNode("Camera Node", cam);
        //camNode.setControlDir(CameraControl.ControlDirection.CameraToSpatial);

        menuNode = new Node();
        menuNode.attachChild(menuGeom);

        rootNode.attachChild(backdropGeom);
        rootNode.attachChild(battleground);
        rootNode.attachChild(menuNode);

        xPos = 0;

    }

    @Override
    public void simpleUpdate(float tpf) {
        atbGauge = combatant.getATBGauge();
        atbGauge.increment();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (atbGauge.getValue() == atbGauge.getMax()) {
            initiateTurn(combatant);
            AudioNode node = new AudioNode(assetManager, "Sounds/shotgun.wav", false);
            node.setPositional(false);
            node.play();
            
            if (turnTaken) {
                atbGauge.clearFill();
                endTurn(combatant);
            }
        }
        //cam.setLocation(new Vector3f(xPos, 0, 5));
        menuNode.setLocalRotation(cam.getRotation());
        menuNode.setLocalTranslation(cam.getLocation());
        //Vector3f location = menuNode.getLocalTranslation();

        //menuNode.setLocalTranslation(cam.getLocation());
        //menuNode.move(location);

        //xPos += .01f;




    }

    public void initiateTurn(CombatantNode combatant) {
        Geometry geom = (Geometry) combatant.getChild("Combatant");
        
        //geom.getMaterial().setColor("Color", ColorRGBA.White);
        turnTaken = true;
//        combatant.getHealthBar().decrement(20);
//        turnTaken = true;
    }

    public void endTurn(CombatantNode combatant) {
        Geometry geom = (Geometry) combatant.getChild("Combatant");
        geom.getMaterial().setColor("Color", ColorRGBA.Yellow);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    @Override
    public AssetManager getAssetManager() {
        return super.getAssetManager();
    }

    public Material getUnshadedMat() {
        return new Material(getAssetManager(), Main.unshadedMat);
    }
}
