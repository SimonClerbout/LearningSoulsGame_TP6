package lsg.characters;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.RingedKnightArmor;
import lsg.buffs.rings.Ring;

import static java.lang.String.format;

public class Hero extends Character {
    private static int MAX_ARMOR_PIECES = 3;
    private static int MAX_RINGS = 2;
    private ArmorItem armor[];
    private Ring rings[];

    //constructeurs
    public Hero(){
        super(100,50,"Gregooninator");
        armor = new ArmorItem[MAX_ARMOR_PIECES];
        rings = new Ring[MAX_RINGS];
    }

    public Hero(String name){
        super(100,50,name);
        armor = new ArmorItem[MAX_ARMOR_PIECES];
        rings = new Ring[MAX_RINGS];
    }

    public void setArmorItem(ArmorItem armor, int slot) {
        slot --;
        if(0<=slot && slot<MAX_ARMOR_PIECES) {
            this.armor[slot] = armor;
        }
    }

    public float getTotalArmor(){
        float total = 0f;
        for(int i = 0; i<MAX_ARMOR_PIECES; i++){
            if (armor[i] != null) {
                total += armor[i].getArmorValue();
            }
        }
        return(total);
    }

    public int getTotalBuff(){
        int total = 0;
        for(int i = 0; i<MAX_RINGS; i++){
            if (rings[i] != null) {
                total += rings[i].getRingPower();
            }
        }
        return(total);
    }

    public void setRing(Ring ring, int slot) {
        slot --;
        if(0<=slot && slot<MAX_RINGS) {
            this.rings[slot] = ring;
            ring.setHero(this);
        }
    }

    public String armorToString(){
        String n = "ARMOR ";
        for(int i=0; i<MAX_ARMOR_PIECES; i++){
            if (armor[i] == null) {
                n += format(" %2d:%-30s",i+1,"empty");
            } else {
                n +=format(" %2d:%-30s",i+1, armor[i].toString());
            }
        }
        return (n +"TOTAL:"+getTotalArmor());
    }

    public String ringToString(){
        String n = "RINGS ";
        for(int i=0; i<MAX_RINGS; i++){
            if (rings[i] == null) {
                n += format(" %2d:%-30s",i+1,"empty");
            } else {
                n +=format(" %2d:%-30s",i+1, rings[i].toString());
            }
        }
        return (n +"TOTAL:"+getTotalBuff());
    }

    public void printRings(){
        String n = "RINGS ";
        for(int i=0; i<MAX_RINGS; i++){
            if (rings[i] == null) {
                n += format(" %2d:%-30s",i+1,"empty");
            } else {
                n +=format(" %2d:%-30s",i+1, rings[i].toString());
            }
        }
        System.out.println(n);
    }

    public ArmorItem[] getArmorItems(){
        int n = 0;
        for (int i=0; i<MAX_ARMOR_PIECES; i++){
            if(armor[i] != null){
                n++;
            }
        }
        ArmorItem temp[] = new ArmorItem[n];
        if(n>0){
            int m = 0;
            for (int i=0; i<MAX_ARMOR_PIECES; i++){
                if(armor[i] != null){
                    temp[m] = armor[i];
                    m++;
                }
            }
        }
        return (temp);
    }

    public Ring[] getRings(){
        int n = 0;
        for (int i=0; i<MAX_RINGS; i++){
            if(rings[i] != null){
                n++;
            }
        }
        Ring temp[] = new Ring[n];
        if(n>0){
            int m = 0;
            for (int i=0; i<MAX_RINGS; i++){
                if(rings[i] != null){
                    temp[m] = rings[i];
                    m++;
                }
            }
        }
        return (temp);
    }

    @Override
    protected float computeProtection() {
        return (this.getTotalArmor());
    }

    @Override
    protected float computeBuff() {
        return (this.getTotalBuff());
    }

    public void equip(ArmorItem item, int slot){
        if(pullOut(item) != null) {
            this.setArmorItem(item, slot);
            System.out.println(" and equips it !");
        }
    }
    public void equip(Ring ring, int slot){
        if(pullOut(ring) != null) {
            this.setRing(ring, slot);
            System.out.println(" and equips it !");
        }
    }

    public static void main(String[] args) {
        Hero hero = new Hero();
        hero.setArmorItem(new BlackWitchVeil(),1);
        hero.setArmorItem(new RingedKnightArmor(),3);
        System.out.println(hero.armorToString());
    }
}
