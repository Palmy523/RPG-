/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.menu;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import game.Main;

/**
 *The AbstractMenuController is used to initialize menu data app, manager, nifty, 
 * and screen. ALL menu controllers should extend this class.
 * 
 * @author Dave
 */
public abstract class AbstractMenuController extends AbstractAppState implements ScreenController {

    /**
     * The main application.
     */
    private Main app;
    
    /**
     * The apps AppStateManager.
     */
    private AppStateManager manager;
    
    /**
     * The Nifty instance associated with the application.
     */
    private Nifty nifty;
    
    /**
     * The Screen associated with this menu.
     */
    private Screen screen;
    
    /**
     * Initializes the menu.
     * 
     * @param manager the manager to set as this menus manager.
     * @param app the application to set as this menus app.
     */
    @Override
    public void initialize(AppStateManager manager, Application app) {
        super.initialize(manager, app);
        this.app = (Main) app;
        this.manager = manager;
        nifty = this.app.getNifty();
        nifty.fromXml(this.getXMLResource(), this.getMenuId());
    }
    
    @Override
    public void onEndScreen() {
        nifty.removeScreen(getMenuId());
    }
    
    /**
     * Binds nifty and screen to this controller.
     * 
     * @param nifty the nifty instance of the app.
     * @param screen the screen associated with this menu.
     */
    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }
    
    /**
     * @return a string representation of the path to the XML resource associated
     * with this menu.
     */
    public abstract String getXMLResource();
    
    /**
     * @return a string representation of the menu id associated with the XML 
     * resource.
     */
    public abstract String getMenuId();

    public Main getApp() {
        return app;
    }

    public void setApp(Main app) {
        this.app = app;
    }

    public AppStateManager getManager() {
        return manager;
    }

    public void setManager(AppStateManager manager) {
        this.manager = manager;
    }

    public Nifty getNifty() {
        return nifty;
    }

    public void setNifty(Nifty nifty) {
        this.nifty = nifty;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    
    
    
}
