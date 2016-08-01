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
public class BattleActionBar extends AbstractMenuController {

    @Override
    public String getXMLResource() {
        return "Interface/Battle/BattleActionBar.xml";
    }

    @Override
    public String getMenuId() {
        return "battle_actionbar";
    }

    @Override
    public void onStartScreen() {
    }
    
}
