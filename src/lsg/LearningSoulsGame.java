package lsg;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.buffs.rings.DragonSlayerRing;
import lsg.buffs.rings.RingOfDeath;
import lsg.buffs.rings.RingOfSwords;
import lsg.characters.Hero;
import lsg.characters.Lycanthrope;
import lsg.characters.Monster;
import lsg.consumables.Consumable;
import lsg.consumables.MenuBestOfV4;
import lsg.consumables.drinks.Coffee;
import lsg.consumables.drinks.Whisky;
import lsg.consumables.food.Americain;
import lsg.consumables.food.Hamburger;
import lsg.exceptions.*;
import lsg.weapons.Claw;
import lsg.weapons.ShotGun;
import lsg.weapons.Sword;
import lsg.weapons.Weapon;

import java.util.Scanner;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class LearningSoulsGame {

    private Hero hero = new Hero();
    private Monster monster = new Monster();
    private Scanner scanner = new Scanner(System.in);
    public static final String BULLET_POINT = "\u2219";

    private void refresh(){

        hero.printStats();
        System.out.println(hero.armorToString());
        hero.printRings();
        hero.printConsumable();
        System.out.println("WEAPON : "+ hero.getWeapon());
        hero.printBag();
        monster.printStats();
        System.out.println("\n");

    }

    private void fight1v1(){

        this.refresh();

        boolean hturn = true;
        int attack = 0, damage = 0;

        do{

            if(hturn) {

                System.out.println("\nHit enter key for next move : (1) attack | (2) consume > ");
                int action = scanner.nextInt();
                if(action==1) {

                    try {
                        attack = hero.attack();
                        damage = monster.getHitWith(attack);
                        System.out.println(hero.getName() + " attacks " + monster.getName() + " with " + hero.getWeapon().getName() + " (ATTACK:" + attack + " | " + "DMG : " + damage + ")");
                    }catch(WeaponNullException e){

                        System.out.println(e.getMessage());
                        System.out.println(hero.getName() + " attacks " + monster.getName() + " with " + hero.getWeapon() + " (ATTACK:" + attack + " | " + "DMG : " + damage + ")");

                    }catch(WeaponBrokenException f){

                        attack = 0;
                        System.out.println("WARNING : " + f.getMessage());

                    }catch(StaminaEmptyException g){

                        System.out.println("ACTION HAS NO EFFECT : " + g.getMessage());

                    }
                    this.refresh();
                }else if(action == 2){
                    try {
                        hero.consume();
                    }catch(ConsumeNullException h){

                        System.out.println("IMPOSSIBLE ACTION : " + h.getMessage());

                    }catch(ConsumeEmptyException e){

                        System.out.println("ACTION HAS NO EFFECT : " + hero.getConsumable() + "is empty !");

                    }catch(ConsumeRepairNullWeaponException e) {

                        System.out.println("IMPOSSIBLE ACTION : " + e.getMessage());

                    }
                }

                hturn = false;
            }else {

                try {
                    attack = monster.attack();
                    damage = hero.getHitWith(attack);
                    System.out.println(monster.getName() + " attacks " + hero.getName() + " with " + monster.getWeapon().getName() + " (ATTACK:" + attack + " | " + "DMG : " + damage + ")");
                } catch (WeaponNullException e) {

                    System.out.println(e.getMessage());
                    System.out.println(monster.getName() + " attacks " + hero.getName() + " with " + monster.getWeapon() + " (ATTACK:" + attack + " | " + "DMG : " + damage + ")");
                }catch(WeaponBrokenException f){

                    attack = 0;
                    System.out.println("WARNING : " + f.getMessage());

                }catch(StaminaEmptyException g){

                    System.out.println("ACTION HAS NO EFFECT : " + g.getMessage());

                }
                this.refresh();
                hturn = true;

            }

        }while(hero.isAlive() && monster.isAlive());
        System.out.println("");
        if(hero.isAlive()){

            System.out.println("--- " + hero.getName() + " WINS !!! ---");

        }else{

            System.out.println("--- " + monster.getName() + " WINS !!! ---");

        }


    }

   /* private void createExhaustedHero(){

        hero = new Hero();
        hero.getHitWith(99);
        Weapon w = new Weapon("Grosse Arme", 0, 0, 1000, 100);
        hero.setWeapon(w);
        try {
            hero.attack();
        }catch(WeaponNullException e){

         e.printStackTrace();
            System.out.println(e.getMessage());

        }
        hero.printStats();

    }*/

    /*private void aTable(){

        MenuBestOfV4 menu = new MenuBestOfV4();

        for(Consumable consumable : menu){

            hero.use(consumable);
            hero.printStats();
            System.out.println("Après utilisation : " + consumable);

        }

        System.out.println(hero.getWeapon());


    }*/

    private void init(){

        hero = new Hero();
        Hamburger h = new Hamburger();
        hero.setConsumable(h);
        hero.setWeapon(new Sword());
        monster = new Monster();
        monster.setWeapon(new Claw());


    }

    private void play_v1(){

        this.init();
        this.fight1v1();

    }

    private void play_v2(){

        this.init();
        hero.setArmorItem(new BlackWitchVeil(),1);
        hero.setArmorItem(new DragonSlayerLeggings(),2);
        hero.setArmorItem(new RingedKnightArmor(),3);
        this.fight1v1();

    }

    private void play_v3(){

        this.init();
       /*hero.setArmorItem(new BlackWitchVeil(),1);*/
        hero.setArmorItem(new DragonSlayerLeggings(),2);
        hero.setRing(new RingOfDeath(), 2);
        hero.setRing(new DragonSlayerRing(), 1);
        monster = new Lycanthrope();
        this.fight1v1();

    }

    public String title(){


        String titre = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        titre += "#  THE LEARNING SOULS GAME   #\n";
        titre += "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(titre);
        return titre;

    }

    public void testExceptions(){

        hero.setWeapon(null);
        this.fight1v1();

    }


    public static void main(String[] args) {

        LearningSoulsGame game = new LearningSoulsGame();

        /*for(int i = 0; i < 5; i++){

            h1.printStats();
            System.out.println("attacks with " + sg.toString() + " > " + h1.attack());
            System.out.println("attacks with " + s2.toString() + " > " + m1.attack());

        }*/

        game.init();
        game.testExceptions();

        //game.createExhaustedHero();
        //game.aTable();
        //game.title();


    }

}
