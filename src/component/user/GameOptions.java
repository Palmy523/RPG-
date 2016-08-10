/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component.user;

import java.io.Serializable;

/**
 *
 * @author Dave
 */
public class GameOptions implements Serializable{
    
    public static enum BattleMode {ACTIVE, WAIT}
    
    private BattleMode battleMode = BattleMode.ACTIVE;
    
    private boolean pauseATBOnAction = false;
    
    private boolean cursorMemory = true;
    
    public GameOptions() {
        
    }

    public BattleMode getBattleMode() {
        return battleMode;
    }

    public void setBattleMode(BattleMode battleMode) {
        this.battleMode = battleMode;
    }

    public boolean isPauseATBOnAction() {
        return pauseATBOnAction;
    }

    public void setPauseATBOnAction(boolean pauseATBOnAction) {
        this.pauseATBOnAction = pauseATBOnAction;
    }

    public boolean isCursorMemory() {
        return cursorMemory;
    }

    public void setCursorMemory(boolean cursorMemory) {
        this.cursorMemory = cursorMemory;
    }
    
    
}
