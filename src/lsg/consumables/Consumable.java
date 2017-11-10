package lsg.consumables;

import lsg.bags.Collectible;

public class Consumable implements Collectible{
    private String name, stat;
    private int capacity;

    public Consumable(String name, int capacity, String stat){
        this.name = name;
        this.capacity = capacity;
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStat() {
        return stat;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return (name + " ["+capacity+" "+stat+" point(s)]");
    }

    public int use(){
        int val = capacity;
        capacity = 0;
        return val;
    }

    @Override
    public int getWeight() {
        return 1;
    }
}
