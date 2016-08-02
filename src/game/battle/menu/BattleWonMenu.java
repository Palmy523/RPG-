/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.battle.menu;

import controller.menu.AbstractMenuController;

/**
 *
 * @author Dave
 */
public class BattleWonMenu extends AbstractMenuController {

    @Override
    public String getXMLResource() {
        return "Interface/Battle/BattleWonMenu.xml";
    }

    @Override
    public String getMenuId() {
        return "battle_won";
    }

    @Override
    public void onStartScreen() {
    }
    
    
}
