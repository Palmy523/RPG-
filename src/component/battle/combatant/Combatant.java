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

    /**
     * Enumerator to distinguish between enemy types.
     */
    public static enum CombatantType {Ally, Enemy, Neutral}

    /**
     * Max health setting.
     */
    private static int ABSOLUTE_MAX_HEALTH = 99999;
    
    /**
     * Max Mana setting.
     */
    private static int ABSOLUTE_MAX_MANA = 99999;
    
    /**
     * Max Endurance setting.
     */
    private static int ABSOLUTE_MAX_END = 99999;
    
    /**
     * Max Attack setting.
     */
    private static int ABSOLUTE_MAX_ATK = 999;
    
    /**
     * Max Defense setting.
     */
    private static int ABSOLUTE_MAX_DEF = 999;
    
    /**
     * Max Speed setting.
     */
    private static int ABSOLUTE_MAX_SPD = 999;
    
    /**
     * The Players unique ID.
     */
    private String id;
    
    /**
     * The name of the character.
     */
    private String name;
    
    /**
     * The characters maxHealth.
     */
    private int maxHealth;
    
    /**
     * The characters current health.
     */
    private int currentHealth;
    
    /**
     * The players maxMana.
     */
    private int maxMana = 100;
    
    /**
     * The players currentMana.
     */
    private int currentMana = 50;
    
    /**
     * The players max Endurance.
     */
    private int maxEND = 100;
    
    /**
     * The players current Endurance.
     */
    private int currentEND = 50;
    
    /**
     * The players Speed setting.
     */
    private int SPD;
    
    /**
     * The players Attack setting.
     */
    private int ATK;
    
    /**
     * The players Defense setting.
     */
    private int DEF;
    
    /**
     * The players CombatantType.
     */
    private CombatantType type = CombatantType.Ally;
    
    /**
     * Default Constructor, too many arguments to make a constructor. Create and 
     * set values using setters.
     */
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
        if (SPD < 1) {
            SPD = 1;
        }
        this.SPD = (SPD > Combatant.ABSOLUTE_MAX_SPD) 
                ? Combatant.ABSOLUTE_MAX_SPD : SPD;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        if (ATK < 0) {
            ATK = 0;
        }
        this.ATK = (SPD > Combatant.ABSOLUTE_MAX_ATK) 
                ? Combatant.ABSOLUTE_MAX_ATK : ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        if (DEF < 0) {
            DEF = 0;
        }
        this.DEF = (DEF > Combatant.ABSOLUTE_MAX_DEF) 
                ? Combatant.ABSOLUTE_MAX_DEF : DEF;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        maxHealth = (maxHealth < 0) ? 0 : maxHealth;
        this.maxHealth = (maxHealth > Combatant.ABSOLUTE_MAX_HEALTH) 
                ? Combatant.ABSOLUTE_MAX_HEALTH : maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        currentHealth = (currentHealth < 0) ? 0 : currentHealth;
        this.currentHealth = (currentHealth > maxHealth) 
                ? maxHealth : currentHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        if (maxMana < 0) {
            maxMana = 0;
        }
        this.maxMana = (maxMana > Combatant.ABSOLUTE_MAX_MANA)
                ? Combatant.ABSOLUTE_MAX_MANA : maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        if (currentMana < 0) {
            currentMana = 0;
        }
        this.currentMana = (currentMana > maxMana)
                ? maxMana : currentMana;
    }

    public int getMaxEND() {
        return maxEND;
    }

    public void setMaxEND(int maxEND) {
        if (maxEND < 0) {
            maxEND = 0;
        }
        this.maxEND = (maxEND > Combatant.ABSOLUTE_MAX_END) 
                ? Combatant.ABSOLUTE_MAX_END : maxEND;
    }

    public int getCurrentEND() {
        return currentEND;
    }

    public void setCurrentEND(int currentEND) {
        currentEND = (currentEND < 0) ? 0 : currentEND;
        this.currentEND = (currentEND > maxEND) ? maxEND : currentEND ;
    }

    public CombatantType getType() {
        return type;
    }

    public void setType(CombatantType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    

}
