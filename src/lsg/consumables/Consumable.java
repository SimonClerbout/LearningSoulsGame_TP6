package lsg.consumables;

import lsg.bags.Collectible;
import lsg.exceptions.ConsumeEmptyException;

/**
 * Created by sclerbou on 07/11/17.
 */
public class Consumable implements Collectible{

    private String name, stat;
    private int capacity;


    public Consumable(String name, int capacity, String stat ){

        this.name = name;
        this.stat = stat;
        this.capacity = capacity;

    }

    public int getCapacity(){

        return this.capacity;

    }

    public String getName(){

       return this.name;

    }

    public String getStat(){

        return this.stat;

    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int use() throws ConsumeEmptyException{

        int stock = this.getCapacity();

        if(stock == 0){

            throw new ConsumeEmptyException(this);

        }else {
            this.setCapacity(0);
        }
        return stock;


    }

    public int getWeight(){

        return 1;

    }

    public String toString() {

        return (this.getName() + " [" + this.getCapacity() + " " + this.getStat() + " point(s)]");

    }
}
