package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;

/**
 * Created by sclerbou on 07/11/17.
 */
public class MenuBestOfV1 {

    private Consumable[] menu;


    public MenuBestOfV1(){

        menu = new Consumable[5];
        menu[0] = new Hamburger();
        menu[1] = new Wine();
        menu[2] = new Americain();
        menu[3] = new Coffee();
        menu[4] = new Whisky();

    }


    @Override
    public String toString() {

        String str = getClass().getSimpleName() + " :\n";

        for(int i = 0; i < 5; i++){

            str +=  i+1 + " : " + menu[i].toString() + "\n";

        }

        return str;

    }

    public static void main(String[] args) {
        MenuBestOfV1 menu = new MenuBestOfV1() ;
        System.out.println(menu.toString());
    }
}
