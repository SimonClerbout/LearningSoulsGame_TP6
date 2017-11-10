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
        System.out.println(BULLET_POINT +" "+ hero.getWeapon());
        System.out.println(BULLET_POINT +" "+ hero.getConsumable());
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
                    hitValue = attaquant.attack();
                    damage = defenseur.getHitWith(hitValue);
                    System.out.println(attaquant.getName() + " attacks " + defenseur.getName() + " with " + attaquant.getWeapon().getName() + "(ATTACK:" + hitValue + "| DMG:" + damage + ")");
                }
                else{
                    attaquant.consume();
                }
            }
            else {
                hitValue = attaquant.attack();
                damage = defenseur.getHitWith(hitValue);
                System.out.println(attaquant.getName() + " attacks " + defenseur.getName() + " with " + attaquant.getWeapon().getName() + "(ATTACK:" + hitValue + "| DMG:" + damage + ")");
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
        hero.attack();
        hero.printStats();
    }

    private void aTable(){
        MenuBestOfV4 manger = new MenuBestOfV4();
        for(Consumable aff: manger){
            hero.use(aff);
            hero.printStats();
            System.out.println("Apres utilisation : "+aff.toString());
        }
        System.out.println(hero.getWeapon());
    }

    public void title(){
        System.out.println("***********************************************");
        System.out.println("*------------ Learning Souls Game ------------*");
        System.out.println("***********************************************");
    }

    public static void main(String[] args){
        LearningSoulsGame game = new LearningSoulsGame();
        game.title();
        game.createExhaustedHero();
        game.aTable();

    }
}