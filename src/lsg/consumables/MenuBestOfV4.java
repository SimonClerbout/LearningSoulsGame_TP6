package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;
import lsg.consumables.repair.RepairKit;

import java.util.LinkedHashSet;

/**
 * Created by sclerbou on 09/11/17.
 */
public class MenuBestOfV4 extends LinkedHashSet<Consumable>{

    RepairKit kit;

    public MenuBestOfV4(){

        kit = new RepairKit();
        this.add(new Hamburger());
        this.add(new Wine());
        this.add(new Americain());
        this.add(new Coffee());
        this.add(new Whisky());

    }


    @Override
    public String toString() {

        String str = getClass().getSimpleName() + " :\n";
        int i = 1;

        for(Consumable consumable : this){

            str += i + " : " + consumable + "\n";
            i++;
        }

        return str;

    }
    // hashSet ne garantie pas la conservation de l'ordre.
    public static void main(String[] args) {
        MenuBestOfV4 menu = new MenuBestOfV4() ;
        System.out.println(menu.toString());
    }


}
