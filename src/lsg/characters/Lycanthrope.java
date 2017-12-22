package lsg.characters;

import lsg.weapons.Claw;

/**
 * Created by sclerbou on 19/10/17.
 */
public class Lycanthrope extends Monster{

    public Lycanthrope(){

        super("Lycanthrope");
        this.setSkinThickness(30f);
        this.setWeapon(new Claw());

    }

}
