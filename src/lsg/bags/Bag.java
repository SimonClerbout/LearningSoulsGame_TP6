package lsg.bags;

import lsg.LearningSoulsGame;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.consumables.food.Hamburger;
import lsg.weapons.Sword;

import java.util.HashSet;

public class Bag {
    private int capacity, weight;
    private HashSet<Collectible> items = new HashSet<>();

    public Bag(int capacity){
        this.capacity = capacity;
        weight = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getWeight() {
        return weight;
    }

    public void push(Collectible item){
        if(item.getWeight() <= capacity-weight){
            weight += item.getWeight();
            items.add(item);
        }
    }

    public Collectible pop(Collectible item){
        if(items.contains(item)){
            items.remove(item);
            weight -= item.getWeight();
            return item;
        }
        else {
            return null;
        }
    }

    public boolean contains(Collectible item){
        return items.contains(item);
    }

    public Collectible[] getItems(){
        Collectible[] tab = new Collectible[items.size()];
        int i = 0;
        for(Collectible item : items){
            tab[i] = item;
            i++;
        }
        return tab;
    }

    @Override
    public String toString() {
        String str = getClass().getSimpleName() + " [ " + items.size() + " items | " + weight + "/" + capacity + " kg ]\n";
        if(items.isEmpty()){
            str += LearningSoulsGame.BULLET_POINT +" (empty)";
        }
        else {
            for (Collectible item : items) {
                str += LearningSoulsGame.BULLET_POINT + " " + item.toString() + "["+item.getWeight()+" kg]\n";
            }
        }
        return str;
    }

    public static void transfer(Bag from, Bag into){
        if(from == into){
            return;
        }
        for(Collectible item: from.getItems()){
            into.push(item);
            if(into.contains(item)){
                from.pop(item);
            }
        }
    }

    public static void main(String[] args) {
        DragonSlayerLeggings test = new DragonSlayerLeggings();
        SmallBag bag = new SmallBag();
        SmallBag bag2 = new SmallBag();
        bag.push(new BlackWitchVeil());
        bag.push(test);
        bag.push(new Sword());
        bag.push(new Hamburger());
        System.out.println(bag.toString());
        bag.pop(test);
        System.out.println("Pop sur "+test.toString());
        System.out.println();
        System.out.println(bag.toString());

        transfer(bag,bag2);
    }
}
