package lsg.armor;

import lsg.bags.Collectible;

/**
 * Created by sclerbou on 19/10/17.
 */
public class DragonSlayerLeggings extends ArmorItem implements Collectible{

    public DragonSlayerLeggings(){

        super("Dragon Slayer Leggings", 10.2f);

    }

    public int getWeight(){

        return 3;

    }

}
