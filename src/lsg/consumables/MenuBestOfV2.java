package lsg.consumables;

import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.drinks.Wine;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;

import java.util.HashSet;

public class MenuBestOfV2 {
    private HashSet<Consumable> menu = new HashSet<>();

    public MenuBestOfV2(){
        menu.add(new Hamburger());
        menu.add(new Wine());
        menu.add(new Americain());
        menu.add(new Coffee());
        menu.add(new Whisky());
    }

    @Override
    public String toString() {
        String str = getClass().getSimpleName()+" :\n";
        int i = 0;
        for(Consumable aff :menu){
            str += i+1 + " : " + aff.toString() +"\n";
            i++;
        }
        return str;
    }

    public static void main(String[] args) {
        MenuBestOfV2 m1 = new MenuBestOfV2();
        System.out.println(m1.toString());
    }
}
