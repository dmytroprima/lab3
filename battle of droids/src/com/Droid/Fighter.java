package com.Droid;

import java.util.Random;

public class Fighter extends Droid{
    public Fighter(String name, int health, int damage) {
        super(name, health, damage);
    }

    public void dealDamage(Droid droid){
        Random rnd = new Random();
        super.dealDamage(droid);
        int chance = rnd.nextInt(4)+1;
        if (chance==4){
            super.dealDamage(droid);
        }
    }
}
