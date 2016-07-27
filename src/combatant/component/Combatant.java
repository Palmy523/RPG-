/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package combatant.component;

/**
 *
 * @author Dave
 */
public class Combatant {
    
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
    
    public Combatant(String name, int health, int speed, int attack, int defense) {
        this.name = name;
        this.maxHealth = health;
        this.currentHealth = maxHealth;
        this.SPD = speed;
        this.ATK = attack;
        this.DEF = defense;
    }
    
    public static Combatant createDefault(String name) {
        return new Combatant(name, 100, 20, 10, 5);
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
    

}
