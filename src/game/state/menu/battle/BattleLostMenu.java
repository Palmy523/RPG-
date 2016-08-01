/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state.menu.battle;

import controller.menu.AbstractMenuController;

/**
 *
 * @author Dave
 */
public class BattleLostMenu extends AbstractMenuController{

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
