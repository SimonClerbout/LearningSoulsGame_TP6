package lsg.weapons;

import lsg.bags.Collectible;
import lsg.consumables.repair.RepairKit;
import lsg.exceptions.ConsumeNullException;

/**
 * Created by sclerbou on 12/10/17.
 */
public class Weapon implements Collectible{

    private String name;
    private int minDamage;
    private int maxDamage;
    private int stamCost;
    private int durability;
    public static final String DURABILITY_STAT_STRING = "durability";

    public Weapon(String name, int minDamage, int maxDamage, int stamCost, int durability){

        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.stamCost = stamCost;
        this.durability = durability;

    }

    public String getName(){

        return name;

    }

    public int getMinDamage(){

        return minDamage;

    }

    public int getMaxDamage(){

        return maxDamage;

    }

    public int getStamCost(){

        return stamCost;

    }

    public int getDurability(){

        return durability;

    }

    private void setDurability(int durability){

        this.durability = durability;

    }

    public void use(){

        this.setDurability(this.getDurability()-1);

    }

    public boolean isBroken(){

        return durability<=0;

    }

    public void repairWith(RepairKit kit) throws ConsumeNullException {

        if(kit == null){

            throw new ConsumeNullException(kit);

        }
        else {

            this.setDurability(this.getDurability() + kit.use());

        }
    }

    public int getWeight(){

        return 2;

    }

    @Override
    public String toString() {
        return (getName()+" (min:" + getMinDamage() + " max:"+ getMaxDamage() + " stam:" + getStamCost() + " " + DURABILITY_STAT_STRING.substring(0,3)+ ":" + getDurability() + ")");
    }
}
