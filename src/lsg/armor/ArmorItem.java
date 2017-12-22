package lsg.armor;

import lsg.bags.Collectible;

/**
 * Created by sclerbou on 19/10/17.
 */
public class ArmorItem implements Collectible{

    private String name;
    private float armorValue;


    public String getName(){

        return name;

    }

    public float getArmorValue(){

        return armorValue;

    }


    public ArmorItem(String name, float armorValue){

        this.name = name;
        this.armorValue = armorValue;

    }

    public int getWeight(){

        return 4;

    }

    @Override
    public String toString() {

        return(name + "(" + armorValue + ")");

    }
}
