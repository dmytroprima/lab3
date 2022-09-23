package com.Droid;

import java.util.Random;

public class Droid {
    protected String name;
    protected int health;
    protected int damage;

    public Droid(String name, int health, int damage){
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public void dealDamage(Droid droid){
        Random rnd = new Random();
        int dmg = (int) (this.damage* (rnd.nextDouble(0.6)+ 0.7));
        droid.health = droid.health - dmg;
    }

    public boolean isAlive(){
        return this.health>0;
    }

    public String getName(){
        return this.name;
    }

    public int getHealth(){
        if (this.health<0){
            this.health = 0;
        }
        return this.health;
    }
}