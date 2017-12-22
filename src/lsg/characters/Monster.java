package lsg.characters;

import lsg.buffs.talismans.Talisman;

public class Monster extends Character {


    private static int INSTANCES_COUNT = 0;
    private float skinThickness = 20;
    private Talisman talisman;
    /*private static int MAX_TALISMANS = 1;*/


    public Monster() {

        this("Monster_" + INSTANCES_COUNT);
        INSTANCES_COUNT++;

    }

    public Monster(String name) {

        super(name);
        INSTANCES_COUNT++;
        this.setLife(10);
        this.setMaxLife(10);
        this.setStamina(10);
        this.setMaxStamina(10);

    }

    public float getSkinThickness(){

        return skinThickness;

    }

    protected void setSkinThickness(float skinThickness){

        this.skinThickness = skinThickness;

    }

    protected float computeProtection(){

        return this.getSkinThickness();

    }

    public Talisman getTalisman(){

        return this.talisman;

    }

    public void setTalisman(Talisman talisman){

        this.talisman = talisman;

    }

    protected float computeBuff(){

        if(talisman != null) {

            return this.talisman.computeBuffValue();

        }else{

            return 0;

        }

    }

}
