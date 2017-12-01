package lsg;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.buffs.rings.DragonSlayerRing;
import lsg.buffs.rings.RingOfDeath;
import lsg.buffs.talismans.MoonStone;
import lsg.characters.*;
import lsg.characters.Character;
import lsg.consumables.Consumable;
import lsg.consumables.MenuBestOfV4;
import lsg.consumables.food.Hamburger;
import lsg.exceptions.WeaponBrokenException;
import lsg.exceptions.WeaponNullException;
import lsg.weapons.Claw;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

import java.util.Scanner;

public class LearningSoulsGame {

    private Scanner scanner = new Scanner(System.in);
    private Hero hero;
    private  Monster monster;
    public static final String BULLET_POINT = "\u2219";

    private void refresh(){
        hero.printStats();
        System.out.println(hero.armorToString());
        hero.printRings();
        System.out.println("CONSUMABLE : "+ hero.getConsumable());
        System.out.println("WEAPON : "+ hero.getWeapon());
        hero.printBag();
        System.out.println();
        monster.printStats();
    }

    private void fight1v1(){
        Character attaquant = hero;
        Character defenseur = monster;
        Character temp;

        refresh();
        while(hero.isAlive() && monster.isAlive()){
            System.out.println("Hit enter key for next move >");
            int hitValue, damage, scan;

            if(attaquant == hero){
                scan = scanner.nextInt();
                while (scan != 1 && scan != 2){
                    scan = scanner.nextInt();
                }
                if(scan == 1) {
                    try {
                        hitValue = attaquant.attack();
                        damage = defenseur.getHitWith(hitValue);
                        System.out.println(attaquant.getName() + " attacks " + defenseur.getName() + " with " + attaquant.getWeapon().getName() + "(ATTACK:" + hitValue + "| DMG:" + damage + ")");
                    }catch (WeaponNullException e){
                        hitValue = 0;
                        System.out.println(e.getMessage());
                        damage = defenseur.getHitWith(hitValue);
                        System.out.println(attaquant.getName() + " attacks " + defenseur.getName() + " with " + attaquant.getWeapon() + "(ATTACK:" + hitValue + "| DMG:" + damage + ")");
                    }catch (WeaponBrokenException e){
                        hitValue = 0;
                        System.out.println(e.getMessage());
                        damage = defenseur.getHitWith(hitValue);
                        System.out.println(attaquant.getName() + " attacks " + defenseur.getName() + " with " + attaquant.getWeapon() + "(ATTACK:" + hitValue + "| DMG:" + damage + ")");
                    }
                }
                else{
                    attaquant.consume();
                }
            }
            else {
                try {
                    hitValue = attaquant.attack();
                    damage = defenseur.getHitWith(hitValue);
                    System.out.println(attaquant.getName() + " attacks " + defenseur.getName() + " with " + attaquant.getWeapon().getName() + "(ATTACK:" + hitValue + "| DMG:" + damage + ")");
                }catch (WeaponNullException e){
                    hitValue = 0;
                    System.out.println(e.getMessage());
                    damage = defenseur.getHitWith(hitValue);
                    System.out.println(attaquant.getName() + " attacks " + defenseur.getName() + " with " + attaquant.getWeapon() + "(ATTACK:" + hitValue + "| DMG:" + damage + ")");
                }catch (WeaponBrokenException e){
                    hitValue = 0;
                    System.out.println(e.getMessage());
                    damage = defenseur.getHitWith(hitValue);
                    System.out.println(attaquant.getName() + " attacks " + defenseur.getName() + " with " + attaquant.getWeapon() + "(ATTACK:" + hitValue + "| DMG:" + damage + ")");
                }
            }

            temp = attaquant;
            attaquant = defenseur;
            defenseur = temp;
            refresh();
        }

        System.out.println("!!! "+(hero.isAlive() ? hero.getName() : monster.getName()) + " WINS !!!");
    }

    private void init(){
        hero = new Hero();
        hero.setWeapon(new Sword());
        hero.setConsumable(new Hamburger());

        monster = new Monster();
        monster.setWeapon(new Claw());
    }

    private void play_v1(){
        init();
        fight1v1();
    }

    private void play_v2(){
        init();
        hero.setArmorItem(new BlackWitchVeil(),1);
        hero.setArmorItem(new DragonSlayerLeggings(),2);
        hero.setArmorItem(new RingedKnightArmor(),3);
        fight1v1();
    }

    private void play_v3(){
        init();
        monster = new Lycanthrope();
        hero.setArmorItem(new DragonSlayerLeggings(),2);
        hero.setRing(new RingOfDeath(),1);
        hero.setRing(new DragonSlayerRing(),2);
        fight1v1();
    }

    private void createExhaustedHero(){
        hero = new Hero();
        hero.getHitWith(99);
        hero.setWeapon(new Weapon("Grosse Arme",0,0,1000,100));
        try {
            hero.attack();
        }catch (WeaponNullException e){
            e.printStackTrace();
        }catch (WeaponBrokenException e){
            System.out.println(e.getMessage());
        }
        hero.printStats();
    }

 /*   private void aTable(){
        MenuBestOfV4 manger = new MenuBestOfV4();
        for(Consumable aff: manger){
            hero.use(aff);
            hero.printStats();
            System.out.println("Apres utilisation : "+aff.toString());
        }
        System.out.println(hero.getWeapon());
    }*/

    public void title(){
        System.out.println("***********************************************");
        System.out.println("*------------ Learning Souls Game ------------*");
        System.out.println("***********************************************");
    }

    public void testExceptions(){
        hero.setWeapon(null);
        this.fight1v1();
    }

    public static void main(String[] args){
        LearningSoulsGame game = new LearningSoulsGame();
        game.init();
        game.testExceptions();
    }
}