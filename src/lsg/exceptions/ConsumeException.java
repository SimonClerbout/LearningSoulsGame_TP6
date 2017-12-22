package lsg.exceptions;

import lsg.consumables.Consumable;

/**
 * Created by sclerbou on 01/12/17.
 */
public abstract class ConsumeException extends Exception {

    private Consumable consumable;
    private String message;

    public ConsumeException(String message, Consumable consumable){}

    public Consumable getConsumable(){

        return this.consumable;

    }

}
