/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component.battle.combatant;

/**
 *
 * @author Dave
 */
public class Combatant {
    
    public static enum CombatantType {Ally, Enemy, Neutral}

    private static int ABSOLUTE_MAX_HEALTH = 99999;
    private static int ABSOLUTE_MIN_HEALTH = 99999;
    private static int ABSOLUTE_MAX_MANA = 99999;
    private static int ABSOLUTE_MAX_ATK = 999;
    private static int ABSOLUTE_MAX_DEF = 999;
    private static int ABSOLUTE_MAX_SPD = 999;
    
    private String name;
    private int maxHealth;
    private int currentHealth;
    private int maxMana = 100;
    private int currentMana = 50;
    private int END = 100;
    private int currentEND = 50;
    private int SPD;
    private int ATK;
    private int DEF;
    private CombatantType type = CombatantType.Ally;
    
    public Combatant() {
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSPD() {
        return SPD;
    }

    public void setSPD(int SPD) {
        this.SPD = SPD;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxSkill() {
        return END;
    }

    public void setMaxSkill(int maxSkill) {
        this.END = maxSkill;
    }

    public int getCurrentSkill() {
        return currentEND;
    }

    public void setCurrentSkill(int currentSkill) {
        this.currentEND = currentSkill;
    }

    public int getEND() {
        return END;
    }

    public void setEND(int END) {
        this.END = END;
    }

    public int getCurrentEND() {
        return currentEND;
    }

    public void setCurrentEND(int currentEND) {
        this.currentEND = currentEND;
    }

    public CombatantType getType() {
        return type;
    }

    public void setType(CombatantType type) {
        this.type = type;
    }
    

}
