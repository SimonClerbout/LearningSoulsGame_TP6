package lsg.bags;

import lsg.LearningSoulsGame;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.consumables.Consumable;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.food.Hamburger;
import lsg.weapons.Sword;

import java.util.HashSet;

/**
 * Created by sclerbou on 10/11/17.
 */
public class Bag {

    private int capacity;
    private int weight;
    private HashSet<Collectible> items = new HashSet<>();

    public Bag(int capacity){

        this.capacity = capacity;

    }

    public int getCapacity(){

        return capacity;

    }

    public void setCapacity(int capacity){

        this.capacity = capacity;

    }

    public int getWeight(){

        return weight;

    }

    public void setWeight(int weight){

        this.weight = weight;

    }

    public void push(Collectible item){

        if(item.getWeight() <= capacity - weight){

            this.weight += item.getWeight();
            this.items.add(item);

        }

    }

    public Collectible pop(Collectible item){

        Collectible poubelle = null;

        if(this.items.contains(item)){


            poubelle = item;
            items.remove(item);
            this.weight -= item.getWeight();

        }

        return poubelle;

    }

    public boolean contains(Collectible item){

        return items.contains(item);

    }

    public Collectible[] getItems(){

        Collectible[] tab = new Collectible[this.items.size()];
        int i = 0;

        for(Collectible collectible : items){

            tab[i] = collectible;
            i++;

        }

        return tab;

    }

    public static void transfer(Bag from, Bag into){

        if(from == null || into == null){

         return;

        }
        else {
            if (from == into) {

                return;

            }
            for (Collectible tr : from.getItems()) {
                into.push(tr);
                if (into.contains(tr)) {

                    from.pop(tr);

                }

            }
        }

    }

    public String toString(){

        String str = getClass().getSimpleName() + " [ " + this.getItems().length + " items | " + this.getWeight() + "/" + this.getCapacity() + " kg ]\n";
        if(items.isEmpty()){

            str += LearningSoulsGame.BULLET_POINT + " " + "(empty)";

        }

        else {

            for (Collectible collectible : items) {

                str += LearningSoulsGame.BULLET_POINT + " " + collectible.toString() + "[" +collectible.getWeight() + " kg]\n";

            }



        }

        return str;

    }

    public static void main(String[] args) {

        SmallBag b = new SmallBag();
        SmallBag b2 = new SmallBag();
        DragonSlayerLeggings ds = new DragonSlayerLeggings();

        b.push(new Hamburger());
        b.push(new Sword());
        b.push(new BlackWitchVeil());
        b.push(ds);
        System.out.println(b);
        b.pop(ds);
        System.out.println("Pop sur " + ds.toString() + "\n");

        System.out.println(b);

        /*System.out.println("Sac 1 :\n" + b);
        System.out.println("Sac 2 :\n" + b2);

        transfer(b,b2);

        System.out.println("Sac 2 après transfert :\n" + b2);
        System.out.println("Sac 1 après transfert :\n" + b);*/





    }


}
