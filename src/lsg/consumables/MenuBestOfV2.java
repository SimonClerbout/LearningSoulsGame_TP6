package lsg.consumables;

import lsg.consumables.Consumable;
import lsg.consumables.MenuBestOfV1;
import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;

/**
 * Created by sclerbou on 07/11/17.
 */
public class MenuBestOfV2 {

    private java.util.HashSet<Consumable> menu;

    public MenuBestOfV2(){

        menu = new java.util.HashSet<Consumable>();
        menu.add(new Hamburger());
        menu.add(new Wine());
        menu.add(new Americain());
        menu.add(new Coffee());
        menu.add(new Whisky());

    }


    @Override
    public String toString() {

        String str = getClass().getSimpleName() + " :\n";
        int i = 1;

        for(Consumable consumable : menu){

            str += i + " : " + consumable + "\n";
            i++;
        }

        return str;

    }

    public static void main(String[] args) {
        MenuBestOfV2 menu = new MenuBestOfV2() ;
        System.out.println(menu.toString());
    }

}
