/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state.menu;

import component.user.GameOptions;
import controller.menu.AbstractMenuController;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.RadioButtonGroupStateChangedEvent;
import game.Game;
import game.StateManager;

/**
 *
 * @author Dave
 */
public class OptionsMenuState extends AbstractMenuController {

    @Override
    public String getXMLResource() {
        return "Interface/GameOptionsMenu.xml";
    }

    @Override
    public String getMenuId() {
        return "options";
    }

    @Override
    public void onStartScreen() {
    }
    
    @NiftyEventSubscriber(id="checkbox_pauseATB")
    public void changePauseATBOption(String id, CheckBoxStateChangedEvent event) {
        Game.getGameState().getOptions().setPauseATBOnAction(event.isChecked());
    }

    @NiftyEventSubscriber(id="checkbox_cursorMemory")
    public void changeCursorMemory(String id, CheckBoxStateChangedEvent event) {
        Game.getGameState().getOptions().setCursorMemory(event.isChecked());
    }
    
    @NiftyEventSubscriber(id="go_back")
    public void goBack(String id, ButtonClickedEvent event) {
        StateManager.getStateManager().loadStartMenu();
        this.getNifty().exit();
    }

    @NiftyEventSubscriber(id = "Battle_Progress_Group")
    public void onRadioGroup1Changed(final String id, final RadioButtonGroupStateChangedEvent event) {
        if (event.getSelectedId().equals("wait")) {
            Game.getGameState().getOptions().setBattleMode(GameOptions.BattleMode.WAIT);
        }
        if (event.getSelectedId().equals("active")) {
            Game.getGameState().getOptions().setBattleMode(GameOptions.BattleMode.ACTIVE);
        }
    }
    
}
