package lsg.weapons;

import lsg.bags.Collectible;
import lsg.consumables.repair.RepairKit;

import static java.lang.String.format;

public class Weapon implements Collectible{
    private String name;
    private int minDamage, maxDamage, stamCost, durability;
    public static final String DURABILITY_STAT_STRING = "durability";

    public Weapon(String name, int minDamage, int maxDamage, int stamCost, int durability){
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.stamCost = stamCost;
        this.durability = durability;
    }

    public void use(){
        this.durability --;
    }

    public boolean isBroken(){
        return (this.durability<1);
    }

    public String toString() {
        return (name +" "+"(min:"+minDamage+" "+"max:"+maxDamage+" "+"stam:"+stamCost+" "+DURABILITY_STAT_STRING.substring(0,3)+":"+durability+")");
    }

    public String getName() {
        return name;
    }

    public int getDurability() {
        return durability;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getStamCost() {
        return stamCost;
    }

    protected void setName(String name) {
        this.name = name;
    }

    private void setDurability(int durability) {
        this.durability = durability;
    }

    protected void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    protected void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    protected void setStamCost(int stamCost) {
        this.stamCost = stamCost;
    }

    public void repairWith(RepairKit kit){
        durability += kit.use();
    }

    @Override
    public int getWeight() {
        return 2;
    }
}
