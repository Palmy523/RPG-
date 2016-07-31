package game;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import component.battle.combatant.Combatant;
import component.battle.combatant.Combatant.CombatantType;
import component.state.BattleStateModel;
import component.state.GameStateModel;
import component.user.User;
import de.lessvoid.nifty.Nifty;
import graphics.combatant.CombatantNode;
import static game.Main.app;
import game.state.GameState;
import game.state.StateManager;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static Main app;
    private Nifty nifty;
    public GameState state;
    public StateManager manager;
    public static String unshadedMat = "Common/MatDefs/Misc/Unshaded.j3md";
    public static int screenWidth = 1280;
    public static int screenHeight = 800;
//    public ProgressCircle atbGauge;
//    public Node node;
//    public Node menuNode;
//    public float xPos;
//    public static float menuFactor = 0.00049765625f;
//    public boolean turnTaken = false;
//    public Geometry highlightTurnGeom;
//    public CombatantNode combatant;

    public static void main(String[] args) {
        app = new Main();

        //app.showStartupMenu();
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        app.setSettings(settings);
        app.settings.setWidth(screenWidth);
        app.settings.setHeight(screenHeight);
        app.settings.setSamples(0);
        app.start();
        System.out.println((int) 5.75);


    }

    /**
     * Initialize the app.
     */
    @Override
    public void simpleInitApp() {
        initUserState("0");
        initNifty();
        manager = new StateManager(this);
        manager.loadStartMenu();




//        AudioNode backgroundMusic = new AudioNode(assetManager, "Sounds/Battle.ogg", true);
//        backgroundMusic.setPositional(false);
//        backgroundMusic.play();
//        Box backdropMesh = new Box(30, 30, .01f);
//        Box battlegroundMesh = new Box(20, .01f, 20);
//
//        Geometry backdropGeom = new Geometry("Background", backdropMesh);
//        Geometry battlegroundGeom = new Geometry("Floor", battlegroundMesh);
//
//        backdropGeom.setLocalTranslation(0, 0, -30);
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", new ColorRGBA(0, 0, 255, .5f));
//        backdropGeom.setMaterial(mat);
//
//        Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat3.setColor("Color", ColorRGBA.Brown);
//        battlegroundGeom.setMaterial(mat3);
//
//        Node battleground = new Node("BattleGround");
//
//        combatant = new CombatantNode(Combatant.createDefault("Dude Man"));
//        combatant.getHealthBar().setValue(100);
//        combatant.getHealthBar().update();
//        combatant.getManaBar().setValue(100);
//        combatant.getManaBar().update();
//        combatant.getSkillBar().setValue(100);
//        combatant.getSkillBar().update();
//        combatant.setLocalTranslation(4, .75f, -2);
//        atbGauge = combatant.getATBGauge();
//        battleground.attachChild(combatant);
//
//        CombatantNode combatant2 = new CombatantNode(Combatant.createDefault("Dude Man"));
//        combatant2.getHealthBar().setValue(40);
//        combatant2.getHealthBar().update();
//        combatant2.setLocalTranslation(4, .75f, 2);
//        battleground.attachChild(combatant2);
//
//
//        Combatant combatant = Combatant.createDefault("Whatever Man");
//        combatant.setType(Combatant.CombatantType.Enemy);
//        CombatantNode combatant3 = new CombatantNode(combatant);
//        combatant3.getSkillBar().setValue(100);
//        combatant3.getSkillBar().update();
//        combatant3.setLocalTranslation(-4, .75f, -2);
//        battleground.attachChild(combatant3);
//
//        combatant = Combatant.createDefault("Bling");
//        combatant.setType(Combatant.CombatantType.Enemy);
//        CombatantNode combatant4 = new CombatantNode(combatant);
//        combatant4.getManaBar().setValue(100);
//        combatant4.getManaBar().update();
//        combatant4.getHealthBar().setValue(50);
//        combatant4.getHealthBar().update();
//        combatant4.setLocalTranslation(-4, .75f, 2);
//        battleground.attachChild(combatant4);
//
//        battleground.setLocalTranslation(0, -3, -5);
//        battleground.rotate(FastMath.DEG_TO_RAD * 10, 0, 0);
//        battleground.attachChild(battlegroundGeom);
//
//        Material mat4 = this.getUnshadedMat();
//        mat4.setColor("Color", ColorRGBA.DarkGray);
//
//        node = new Node();
//        Box menu = new Box(.1f, .4f, 0.0001f);
//        Geometry menuGeom = new Geometry("Menu", menu);
//        menuGeom.setMaterial(mat4);
//        menuGeom.setLocalTranslation(cam.getLocation());
//        menuGeom.move((float) screenWidth * menuFactor, 0, -8.89f);
//
//        Box highlightBox = new Box(1.5f, .01f, 0);
//        highlightTurnGeom = new Geometry("highlightBox", highlightBox);
//        Material highlightMat = getUnshadedMat();
//        highlightMat.setColor("Color", ColorRGBA.White);
//
//        //CameraNode camNode = new CameraNode("Camera Node", cam);
//        //camNode.setControlDir(CameraControl.ControlDirection.CameraToSpatial);
//
//        menuNode = new Node();
//        menuNode.attachChild(menuGeom);
//
//        rootNode.attachChild(backdropGeom);
//        rootNode.attachChild(battleground);
//        //rootNode.attachChild(menuNode);
//
//        xPos = 0;

    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    public void initiateTurn(CombatantNode combatant) {
//        Geometry geom = (Geometry) combatant.getChild("Combatant");
        //geom.getMaterial().setColor("Color", ColorRGBA.White);
//        turnTaken = true;
//        combatant.getHealthBar().decrement(20);
//        turnTaken = true;
    }

    public void endTurn(CombatantNode combatant) {
//        Geometry geom = (Geometry) combatant.getChild("Combatant");
//        geom.getMaterial().setColor("Color", ColorRGBA.Yellow);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    @Override
    public AssetManager getAssetManager() {
        return this.assetManager;
    }

    /**
     * @return a default testing material.
     */
    public Material getUnshadedMat() {
        return new Material(getAssetManager(), Main.unshadedMat);
    }

    /**
     * Initializes the GameState based on a userId.
     *
     * @param userId the userId of the person to initialize the GameState for.
     */
    private void initUserState(String userId) {
        state = new GameState();
        GameStateModel gameStateModel = new GameStateModel();
        BattleStateModel battleStateModel = new BattleStateModel();

        User user = new User();
        gameStateModel.setUser(user);

        Combatant combatant = new Combatant();
        combatant.setCurrentHealth(100);
        combatant.setMaxHealth(100);
        combatant.setName("Dudeman");
        combatant.setMaxMana(25);
        combatant.setCurrentMana(25);
        combatant.setEND(100);
        combatant.setCurrentEND(100);
        battleStateModel.getAllys().add(combatant);

        Combatant combatant2 = new Combatant();
        combatant2.setCurrentHealth(100);
        combatant2.setMaxHealth(100);
        combatant2.setName("Slappy");
        combatant2.setMaxMana(25);
        combatant2.setCurrentMana(25);
        combatant2.setEND(100);
        combatant2.setCurrentEND(100);
        battleStateModel.getAllys().add(combatant2);

        Combatant combatant3 = new Combatant();
        combatant3.setCurrentHealth(100);
        combatant3.setMaxHealth(100);
        combatant3.setName("Banana Lover");
        combatant3.setMaxMana(25);
        combatant3.setCurrentMana(25);
        combatant3.setEND(100);
        combatant3.setCurrentEND(100);
        combatant3.setType(CombatantType.Enemy);
        battleStateModel.getEnemies().add(combatant3);


        Combatant combatant4 = new Combatant();
        combatant4.setCurrentHealth(100);
        combatant4.setMaxHealth(100);
        combatant4.setName("Dudeman");
        combatant4.setMaxMana(25);
        combatant4.setCurrentMana(25);
        combatant4.setEND(100);
        combatant4.setCurrentEND(100);
        combatant4.setType(CombatantType.Enemy);
        battleStateModel.getEnemies().add(combatant4);

        state.setBattleStateModel(battleStateModel);
        state.setModel(gameStateModel);

    }

    /**
     * Initializes nifty for display of menus in the game and sets to Main nifty
     * instance.
     */
    private void initNifty() {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        nifty = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);
        //nifty.setDebugOptionPanelColors(true); //un-comment this line to use DebugPanelColors and make sure Nifty is running correctly.
        flyCam.setDragToRotate(true); //detaches camera from mouse unless you click/drag.
    }

    public Nifty getNifty() {
        return nifty;
    }
}
