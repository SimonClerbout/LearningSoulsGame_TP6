package lsg.characters;

import lsg.bags.Bag;
import lsg.bags.Collectible;
import lsg.bags.SmallBag;
import lsg.consumables.Consumable;
import lsg.consumables.drinks.Drink;
import lsg.consumables.food.Food;
import lsg.consumables.repair.RepairKit;
import lsg.helpers.Dice;
import lsg.weapons.Weapon;

import java.util.Locale;

import static java.lang.String.format;

public abstract class Character {
    private String name;
    private int life, maxLife, stamina, maxStamina;
    private Dice dice;
    private Weapon weapon;
    private Consumable consumable;
    private Bag bag;
    public static final String LIFE_STAT_STRING = "life";
    public static final String STAM_STAT_STRING = "stamina";
    public static final String BUFF_STAT_STRING = "buff";
    public static final String PROTECTION_STAT_STRING = "protection";

    //accesseurs

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getStamina() {
        return stamina;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Consumable getConsumable() {
        return consumable;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setLife(int life) {
        this.life = life;
    }

    protected void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    protected void setStamina(int stamina) {
        this.stamina = stamina;
    }

    protected void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }

    public Character(){}

    public Character(int val1, int val2, String name){
        this.name = name;
        life = val1;
        maxLife= val1;
        stamina = val2;
        maxStamina = val2;
        dice = new Dice(101);
        bag = new SmallBag();
    }

    //toString
    @Override
    public String toString() {
        String str1= format("%5d",life);
        String str2= format("%5d",stamina);
        String str3= format(Locale.US,"%6.2f",computeProtection());
        String str4= format(Locale.US,"%6.2f",computeBuff());
        return (format(Locale.US,"%-20s %-20s "+LIFE_STAT_STRING.toUpperCase()+":%-10s "+STAM_STAT_STRING.toUpperCase()+":%-10s "+PROTECTION_STAT_STRING.toUpperCase()+":%-10s "+BUFF_STAT_STRING.toUpperCase()+":%-10s", "[ "+getClass().getSimpleName()+" ]", name, str1, str2, str3, str4)+ ((this.isAlive())? "(ALIVE)" :"(DEAD)"));
    }

    //methodes
    public void printStats(){
        System.out.println(this.toString());
    }

    public boolean isAlive(){
        return(this.getLife()>0);
    }

    private int attackWith(Weapon weapon){
        if (weapon.isBroken())
            return 0;
        else{
            int pourcent = dice.roll();
            weapon.use();
            int difference = weapon.getMaxDamage()-weapon.getMinDamage();
            int damage = (int)(weapon.getMinDamage() + ((difference*pourcent)/100.0));
            if(stamina<weapon.getStamCost()){
                if(stamina==0)
                    return 0;
                else {
                    double difStamina = stamina/weapon.getStamCost();
                    damage = (int)(damage*difStamina);
                    stamina = 0;
                    return damage;
                }
            }
            else{
                stamina = stamina - weapon.getStamCost();
                return damage;
            }

        }
    }

    public int attack(){
        return (attackWith(weapon));
    }

    public int getHitWith(int val){
        int pvRetire = val;
        if(computeProtection()>=100){
            pvRetire = 0;
        }
        else {
            if(computeProtection()!=0){
                pvRetire =Math.round(val - (val * computeProtection()/100));
            }
        }
        pvRetire = ((pvRetire > life)? life :pvRetire);
        life -= pvRetire;
        return (pvRetire);
    }

    private void drink(Drink drink){
        System.out.println(name + " drinks " + drink.toString());
        stamina += drink.use();
        if (stamina > maxStamina) {
            stamina = maxStamina;
        }
    }

    private void eat(Food food){
        System.out.println(name + " eats " + food.toString());
        stamina += food.use();
        if (stamina > maxStamina) {
            stamina = maxStamina;
        }
    }

    public void use(Consumable consumable){
        if(consumable instanceof Food){
            this.eat((Food) consumable);
        }
        else{
            if(consumable instanceof Drink) {
                this.drink((Drink) consumable);
            }
            else {
                this.repairWeaponWith((RepairKit) consumable);
            }
        }
    }

    private void repairWeaponWith(RepairKit kit){
        System.out.println(name + " repairs " + weapon.toString() + " with " + kit.toString());
        weapon.repairWith(kit);
    }

    public void pickUp(Collectible item){
        bag.push(item);
        System.out.print(name + " picks up "+ item.toString());
    }

    public Collectible pullOut(Collectible item){
        if(bag.contains(item)) {
            bag.pop(item);
            System.out.print(name + " pulls out " + item.toString());
            return item;
        }
        return null;
    }

    public void printBag(){
        System.out.println("BAG : "+bag.toString());
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
        System.out.println(name +" changes "+this.bag.getClass().getSimpleName()+ " for "+bag.getClass().getSimpleName());
        Bag.transfer(this.bag,bag);
        Bag temp = this.bag;
        this.bag = bag;
        return temp;
    }

    public void equip(Weapon weapon){
        if(bag.contains(weapon)){
            this.weapon = weapon;
            bag.pop(weapon);
        }
    }

    public void equip(Consumable consumable){
        if(bag.contains(consumable)){
            this.consumable = consumable;
            bag.pop(consumable);
        }
    }

    public void consume(){
        this.use(consumable);
    }

    protected abstract float computeProtection();

    protected abstract float computeBuff();
}
