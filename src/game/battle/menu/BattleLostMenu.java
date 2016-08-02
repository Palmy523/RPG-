/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.menu;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import controller.menu.AbstractMenuController;

/**
 *
 * @author Dave
 */
public class BattleLostMenu extends AbstractMenuController {


    
    private void registerControls() {
        
    }
    
    @Override
    public String getXMLResource() {
        return "Interface/Battle/BattleLostMenu.xml";
    }

    @Override
    public String getMenuId() {
        return "battle_lost";
    }

    @Override
    public void onStartScreen() {
    }
    
}
