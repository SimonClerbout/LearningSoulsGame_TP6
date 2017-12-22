package lsg.characters;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.RingedKnightArmor;
import lsg.buffs.rings.Ring;
import lsg.consumables.Consumable;

import static java.lang.String.format;

public class Hero extends Character{


    private ArmorItem[] armor;
    private Ring[] rings;
    private static int MAX_ARMOR_PIECES = 3;
    private static int MAX_RINGS = 2;

    public Hero(){

        this("Gregooninator");
        this.armor = new ArmorItem[MAX_ARMOR_PIECES];
        this.rings = new Ring[MAX_RINGS];

    }

    public Hero(String name){

        super(name);
        this.setLife(100);
        this.setMaxLife(100);
        this.setStamina(50);
        this.setMaxStamina(50);
        this.armor = new ArmorItem[MAX_ARMOR_PIECES];
        this.rings = new Ring[MAX_RINGS];

    }

    public void setArmorItem(ArmorItem armor, int i){

        if(i > 0 && i <= MAX_ARMOR_PIECES){

            this.armor[i-1] = armor;

        }

    }


    public float getTotalArmor(){

        float sum = 0;

        for(int i = 0; i < MAX_ARMOR_PIECES; i++){

            if(this.armor[i] != null) {

                sum = sum + this.armor[i].getArmorValue();

            }

        }

        return(sum);

    }

    public Ring[] getRings(){

        Ring[] tmp;
        int cmp = 0;

        for(int i=0; i < MAX_RINGS; i++){

            if(this.rings[i] != null){

                cmp = cmp +1;

            }

        }

        tmp = new Ring[cmp];
        cmp = 0;

        for(int i=0; i < MAX_RINGS; i++){

            if(this.rings[i] != null){

                tmp[cmp] = this.rings[i];
                cmp++;

            }

        }

        return tmp;


    }

    public float getTotalBuff(){

        float sum = 0;

        for(int i = 0; i < MAX_RINGS; i++){

            if(this.rings[i] != null) {

                sum = sum + this.rings[i].computeBuffValue();

            }

        }

        return(sum);

    }

    public void setRing(Ring ring, int i){

        if(i > 0 && i <= MAX_RINGS){

            this.rings[i-1] = ring;
            ring.setHero(this);

        }

    }

    public String ringToString(){

        String rString = ("BUFF ");

        for(int i=0; i < MAX_RINGS; i++){

            if(this.rings[i] == null){

                rString += format(" %2d:%-30s", i+1, "empty");

            }else {

                rString += format(" %2d:%-30s", i+1, this.rings[i].toString());

            }

        }

        rString += "TOTAL:" + this.getTotalBuff();
        return rString;


    }

    public String armorToString(){

        String aString = ("ARMOR ");

        for(int i=0; i < MAX_ARMOR_PIECES; i++){

            if(this.armor[i] == null){

                aString += format(" %2d:%-30s", i+1, "empty");

            }else {

                aString += format(" %2d:%-30s", i+1, this.armor[i].toString());

            }

        }

        aString += "TOTAL:" + this.getTotalArmor();
        return aString;

    }

    public ArmorItem[] getArmorItems(){

        ArmorItem[] tmp;
        int cmp = 0;

        for(int i=0; i < MAX_ARMOR_PIECES; i++){

            if(this.armor[i] != null){

                cmp = cmp +1;

            }

        }

        tmp = new ArmorItem[cmp];
        cmp = 0;

        for(int i=0; i < MAX_ARMOR_PIECES; i++){

            if(this.armor[i] != null){

                tmp[cmp] = this.armor[i];
                cmp++;

            }

        }

        return tmp;

    }

    protected float computeProtection(){

        return this.getTotalArmor();

    }

    protected float computeBuff(){

        return this.getTotalBuff();

    }


    public void equip(ArmorItem item, int slot){
            if(pullOut(item) != null){

                this.setArmorItem(item, slot);
                System.out.println(" and equips it !");

            }


    }

    public void equip(Ring ring, int slot){
        if(pullOut(ring) != null){

            this.setRing(ring, slot);
            System.out.println(" and equips it !");

        }
    }


    public void printRings(){

        String rString = ("RINGS ");

        for(int i=0; i < MAX_RINGS; i++){

            if(this.rings[i] == null){

                rString += format(" %2d:%-30s", i+1, "empty");

            }else {

                rString += format(" %2d:%-30s", i+1, this.rings[i].toString());

            }

        }

        System.out.println(rString);

    }

    public static void main(String[] args) {

        Hero hero = new Hero();
        ArmorItem armor = new BlackWitchVeil();
        ArmorItem armor2 = new RingedKnightArmor();

        hero.setArmorItem(armor,1);
        hero.setArmorItem(armor2,3);

        System.out.print(hero.armorToString());

    }


}
