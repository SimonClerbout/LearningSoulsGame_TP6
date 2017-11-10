package lsg.consumables.repair;

import lsg.consumables.Consumable;
import lsg.weapons.Weapon;

public class RepairKit extends Consumable{
    public RepairKit(){
        super("Repair Kit",10, Weapon.DURABILITY_STAT_STRING);
    }

    public int use(){
        int val = 1;
        if(super.getCapacity()>0){
            super.setCapacity(super.getCapacity()-1);
        }
        else{
            val = 0;
        }
        return val;
    }
}
