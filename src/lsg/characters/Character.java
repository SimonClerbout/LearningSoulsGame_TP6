package lsg.characters;

import lsg.bags.Bag;
import lsg.bags.Collectible;
import lsg.bags.SmallBag;
import lsg.consumables.Consumable;
import lsg.consumables.drinks.Drink;
import lsg.consumables.food.Food;
import lsg.consumables.repair.RepairKit;
import lsg.exceptions.*;
import lsg.helpers.Dice;
import lsg.weapons.Weapon;

import java.util.Locale;

import static lsg.bags.Bag.transfer;

/**
 * Created by sclerbou on 12/10/17.
 */
public abstract class Character {

    private String name;
    private int life, maxLife, stamina, maxStamina;
    private Dice dice;
    private Weapon weapon;
    public static final String LIFE_STAT_STRING = "life";
    public static final String STAM_STAT_STRING = "stamina";
    public static final String BUFF_STAT_STRING = "buff";
    public static final String PROTECTION_STAT_STRING = "protection";
    private Consumable consumable;
    private Bag bag;

    public Consumable getConsumable(){

        return consumable;

    }

    public void setConsumable(Consumable consumable){

        this.consumable = consumable;

    }

    public void consume() throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException {

        this.use(this.getConsumable());

    }

    public String getName() {

        return name;

    }

    protected void setName(String name) {

        this.name = name;

    }

    public int getLife() {

        return life;

    }

    protected void setLife(int life) {

        this.life = life;

    }

    public int getMaxLife() {
        return maxLife;
    }

    protected void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getStamina() {
        return stamina;
    }

    protected void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    protected void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public Weapon getWeapon(){

        return weapon;

    }

    public void setWeapon(Weapon w){

        this.weapon = w;

    }



    public Character(){

        name = "";
        life = 0;
        maxLife = 0;
        stamina = 0;
        maxStamina = 0;
        dice = new Dice(101);
        bag = new SmallBag();

    }

    public Character(String name){
        this.setName(name);
        dice = new Dice(101);
        bag = new SmallBag();

    }

    public void printStats(){

        System.out.println(this.toString());

    }

    public boolean isAlive(){

        return this.getLife()>0;


    }

    private int attackWith(Weapon weapon) throws WeaponNullException, WeaponBrokenException, StaminaEmptyException {
        if(weapon == null)
        {

            throw new WeaponNullException();

        }
        int damage;

        if(weapon.isBroken()){

            throw new WeaponBrokenException(weapon);

        }
        else{

            int accurate = dice.roll();
            weapon.use();
            /* différence entre l'attaque max et l'attaque min */
            int diff = weapon.getMaxDamage() - weapon.getMinDamage();
            damage = (int)(weapon.getMinDamage()+(accurate*diff/100.0));
            /* différence entre le stamina du personnage et le coup engendré par l'attaque*/

            /* conséquence s'appliquant si le personnage n'a pas assez de stamina pour lancer l'attaque. */
            if(this.getStamina() < weapon.getStamCost()){

                damage = damage*this.getStamina()/weapon.getStamCost();
                this.setStamina(0);/* sa stamina tombe donc à 0*/
                throw new StaminaEmptyException();

            }else{

                this.setStamina(this.getStamina() - weapon.getStamCost());

            }

        }

        return damage;

    }

    public int attack() throws WeaponNullException, WeaponBrokenException, StaminaEmptyException {
            return this.attackWith(weapon);


    }

    public int getHitWith(int value){

        int damage = value;

        if(this.computeProtection() >= 100){

            damage = 0;

        }else{

            if(this.computeProtection() != 0) {

                damage = Math.round(damage - damage * this.computeProtection() / 100);

            }
        }

        damage = (this.life > damage) ? damage : life;
        life = life - damage;
        return damage;

    }

    private void drink(Drink drink) throws ConsumeNullException, ConsumeEmptyException{

        if(drink == null){

            throw new ConsumeNullException(drink);

        }
        else {
            System.out.println(name + " drinks " + drink.toString());
            stamina = stamina + drink.use();
            if (stamina > maxStamina) {

                stamina = maxStamina;

            }
        }
    }


    private void eat(Food food) throws ConsumeNullException, ConsumeEmptyException{

        if(food == null){

            throw new ConsumeNullException(food);

        }
        else {
            System.out.println(name + " eats " + food.toString());
            life = life + food.use();
            if (life > maxLife) {

                life = maxLife;

            }
        }
    }

    public void use(Consumable consumable) throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException{

        if(consumable == null){

            throw new ConsumeNullException(consumable);

        }
        else {
            if (consumable instanceof Drink) {

                this.drink((Drink) consumable);

            } else if (consumable instanceof Food) {

                this.eat((Food) consumable);

            } else if (consumable instanceof RepairKit) {
                try {
                    this.repairWeaponWith((RepairKit) consumable);
                } catch (WeaponNullException e) {

                    throw new ConsumeRepairNullWeaponException(consumable);

                }
            }
        }
    }

    private void repairWeaponWith(RepairKit kit) throws WeaponNullException, ConsumeNullException  {

        if(weapon == null){

            throw  new WeaponNullException();

        }
        System.out.println(name + " repairs " + weapon.toString() + " with " + kit.toString());
        weapon.repairWith(kit);

    }

    public void pickUp(Collectible item){

        if(item.getWeight() < bag.getCapacity()){

            this.bag.push(item);
            System.out.print(name + " picks up " + item.toString());

        }

    }

    public Collectible pullOut(Collectible item){

        Collectible rItem = null;

        if(this.bag.contains(item)){

            rItem = item;
            this.bag.pop(item);
            System.out.print(name + " pulls out " + item.toString());

        }

        return rItem;
    }

    public void printBag(){

        String bagstat = bag.toString();

        System.out.println("BAG : " + bagstat);

    }

    public int getBagCapacity(){

        return bag.getCapacity();

    }

    public int getBagWeight(){

        return bag.getWeight();

    }

    public Collectible[] getBagItems(){

        return bag.getItems();

    }

    public Bag setBag(Bag bag){

        if(bag == null){

            return null;

        }
        else {
            System.out.println(getName() + " changes " + this.bag.getClass().getSimpleName() + " for " + bag.getClass().getSimpleName());
            transfer(this.bag, bag);
            Bag temp = this.bag;
            this.bag = bag;
            return temp;
        }

    }

    public void equip(Weapon weapon){

        if(bag.contains(weapon)){

            this.weapon = weapon;
            this.pullOut(weapon);
            System.out.println(" and equips it !");


        }

    }

    public void equip(Consumable consumable){

        if(bag.contains(consumable)){

            this.consumable = consumable;
            this.pullOut(consumable);
            System.out.println(" and equips it !");


        }

    }

    private Consumable fastUseFirst(Class<? extends Consumable> type) throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException {

        for(Collectible c : bag.getItems()){

            if(type.isInstance(c)){

                this.use((Consumable) c);
                if(((Consumable) c).getCapacity() == 0){

                    pullOut(c);

                }

                return (Consumable)c;

            }

        }

        return null;

    }

    public Drink fastDrink() throws ConsumeNullException, ConsumeEmptyException{
        Drink toRet = null;
        System.out.println(getName() + " drinks FAST :");
        try {
            toRet = (Drink) fastUseFirst(Drink.class);

        }catch(ConsumeRepairNullWeaponException e){}

        return toRet;


    }

    public Food fastEat() throws ConsumeNullException, ConsumeEmptyException{
        Food toRet = null;

        System.out.println(getName() + " eats FAST :");
        try {
            toRet = (Food) fastUseFirst(Food.class);
        }catch(ConsumeRepairNullWeaponException e){}

        return toRet;
    }

    public RepairKit fastRepair() throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeaponException{

        System.out.println(getName() + " repairs FAST :");
        return (RepairKit)fastUseFirst(RepairKit.class);

    }

    public void printConsumable(){

        System.out.println("CONSUMABLE : " + consumable.toString());

    }

    protected abstract float computeProtection();

    protected abstract float computeBuff();

    @Override
    public String toString() {
        String alive = (this.isAlive())? "(ALIVE)" : "(DEAD)";
        return (String.format(Locale.US,"%-20s %-20s " + LIFE_STAT_STRING.toUpperCase() + ":%-10s " + STAM_STAT_STRING.toUpperCase() + ":%-10s " + PROTECTION_STAT_STRING.toUpperCase() + ":%-10s " + BUFF_STAT_STRING.toUpperCase() +":%-10s",("[ "+getClass().getSimpleName()+" ]"), getName(), String.format("%5d",life), String.format("%5d",stamina), String.format(Locale.US,"%6.2f", computeProtection()), String.format(Locale.US, "%6.2f", computeBuff())) + alive );
        /*return ("[ characters.Hero ]" + name + "LIFE: " + life + "STAMINA: " + stamina + ((this.isAlive())? "(ALIVE)" : "(DEAD)"));*/
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
