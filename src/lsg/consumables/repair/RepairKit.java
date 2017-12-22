package lsg.consumables.repair;

import lsg.consumables.Consumable;

import static lsg.weapons.Weapon.DURABILITY_STAT_STRING;

/**
 * Created by sclerbou on 09/11/17.
 */
public class RepairKit extends Consumable{


    public RepairKit() {
        super("Repair Kit", 10, DURABILITY_STAT_STRING);
    }

    public int use(){

        int stock = 0;
        if(this.getCapacity() > 0) {

            this.setCapacity(this.getCapacity() - 1);
            stock = 1;

        }

        return stock;

    }
}
