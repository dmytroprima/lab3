package com.Droid;

import java.util.Random;

public class Wizard extends Droid{
    public Wizard(String name, int health, int damage) {
        super(name, health, damage);
    }
    public void dealDamage(Droid droid){
        Random rnd = new Random();
        super.dealDamage(droid);
        this.health+=this.damage*(rnd.nextDouble(0.5));
    }
}
