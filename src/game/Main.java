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
 * Main app entry point.
 *
 * @author dpalmiter
 */
public class Main extends SimpleApplication {

    public static Main app;
    private Nifty nifty;
    public GameState state;
    public StateManager manager;
    public static String unshadedMat = "Common/MatDefs/Misc/Unshaded.j3md";
    public static int screenWidth = 1900;
    public static int screenHeight = 900;
    private static boolean displayStats = true;
    private static boolean displayFps = true;

    /**
     * If I need to explain this, you are already lost.
     */
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

    /**
     * Initialize the app.
     */
    @Override
    public void simpleInitApp() {
        //Get the data for the user and populate the game state.
        initUserState("0");

        //Init the Nifty class for menu creation.
        initNifty();

        //Initialize the StateManager and load the start menu.
        manager = new StateManager(this);
        manager.loadStartMenu();

        //disable the fly cam
        app.flyCam.setEnabled(false);

    }

    @Override
    public void simpleUpdate(float tpf) {
        setDisplayStatView(displayStats);
        this.setDisplayFps(displayFps);
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
     * Initializes the GameState based on a userId. At some point this will
     * contain a manager that will load a users data either from local save
     * files or network communication to remote database.
     *
     * @param userId the userId of the person to initialize the GameState for.
     */
    private void initUserState(String userId) {
        state = new GameState();
        GameStateModel gameStateModel = new GameStateModel();
        BattleStateModel battleStateModel = new BattleStateModel();

        User user = new User();
        gameStateModel.setUser(user);

        Combatant ally1 = new Combatant();
        ally1.setId("0");
        ally1.setMaxHealth(100);
        ally1.setCurrentHealth(100);
        ally1.setName("Dudeman");
        ally1.setMaxMana(999);
        ally1.setCurrentMana(999);
        ally1.setMaxEND(999);
        ally1.setCurrentEND(999);
        ally1.setSPD(1);
        battleStateModel.getAllys().add(ally1);

        Combatant ally2 = new Combatant();
        ally2.setId("1");
        ally2.setMaxHealth(100);
        ally2.setCurrentHealth(100);
        ally2.setName("Slappy");
        ally2.setMaxMana(999);
        ally2.setCurrentMana(999);
        ally2.setMaxEND(999);
        ally2.setCurrentEND(999);
        ally2.setSPD(5);
        battleStateModel.getAllys().add(ally2);

        Combatant ally3 = new Combatant();
                ally3.setId("2");
        ally3.setMaxHealth(100);
        ally3.setCurrentHealth(20);
        ally3.setName("Crappy");
        ally3.setMaxMana(999);
        ally3.setCurrentMana(999);
        ally3.setMaxEND(999);
        ally3.setCurrentEND(999);
        ally3.setSPD(10);
        battleStateModel.getAllys().add(ally3);

        Combatant ally4 = new Combatant();
        ally4.setId("3");
        ally4.setMaxHealth(100);
        ally4.setCurrentHealth(100);
        ally4.setName("Happy");
        ally4.setMaxMana(999);
        ally4.setCurrentMana(999);
        ally4.setMaxEND(999);
        ally4.setCurrentEND(999);
        ally4.setSPD(20);
        battleStateModel.getAllys().add(ally4);

        Combatant enemy1 = new Combatant();
        enemy1.setId("4");
        enemy1.setMaxHealth(100);
        enemy1.setCurrentHealth(100);
        enemy1.setName("Banana Lover");
        enemy1.setMaxMana(25);
        enemy1.setCurrentMana(25);
        enemy1.setMaxEND(100);
        enemy1.setCurrentEND(100);
        enemy1.setSPD(40);
        enemy1.setATK(2);
        enemy1.setType(CombatantType.Enemy);
        battleStateModel.getEnemies().add(enemy1);

        Combatant enemy2 = new Combatant();
        enemy2.setId("5");
        enemy2.setMaxHealth(100);
        enemy2.setCurrentHealth(100);
        enemy2.setName("Pookachoo");
        enemy2.setMaxMana(25);
        enemy2.setCurrentMana(25);
        enemy2.setMaxEND(100);
        enemy2.setCurrentEND(100);
        enemy2.setSPD(80);
        enemy2.setATK(30);
        enemy2.setType(CombatantType.Enemy);
        battleStateModel.getEnemies().add(enemy2);

        Combatant enemy3 = new Combatant();
        enemy3.setId("6");
        enemy3.setMaxHealth(100);
        enemy3.setCurrentHealth(100);
        enemy3.setName("BlipBob");
        enemy3.setMaxMana(25);
        enemy3.setCurrentMana(25);
        enemy3.setMaxEND(100);
        enemy3.setCurrentEND(100);
        enemy3.setSPD(160);
        enemy3.setATK(10);
        enemy3.setType(CombatantType.Enemy);
        battleStateModel.getEnemies().add(enemy3);

        Combatant enemy4 = new Combatant();
        enemy4.setId("7");
        enemy4.setMaxHealth(100);
        enemy4.setCurrentHealth(80);
        enemy4.setName("Polyman");
        enemy4.setMaxMana(25);
        enemy4.setCurrentMana(25);
        enemy4.setMaxEND(100);
        enemy4.setCurrentEND(100);
        enemy4.setSPD(999);
        enemy4.setATK(1);
        enemy4.setType(CombatantType.Enemy);
        battleStateModel.getEnemies().add(enemy4);

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
