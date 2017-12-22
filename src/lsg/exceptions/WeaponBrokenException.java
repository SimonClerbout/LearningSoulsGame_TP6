package lsg.exceptions;

import lsg.weapons.Weapon;

/**
 * Created by sclerbou on 01/12/17.
 */
public class WeaponBrokenException extends Exception {

    private Weapon weapon;

    public WeaponBrokenException(Weapon weapon){

        super(weapon.getName() + "is broken !");
        this.weapon = weapon;

    }


    public Weapon getWeaponBroken(){

        return weapon;

    }

}
