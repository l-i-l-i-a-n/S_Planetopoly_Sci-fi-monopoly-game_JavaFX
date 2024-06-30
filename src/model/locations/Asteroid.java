package model.locations;

import model.utils.Windows;

public class Asteroid extends Location {
    /**
     * The minimum amount of money players must pay when they are in an "Asteroid" square
     */
    private int minDamage;

    /**
     * Returns the amount of money a player must pay
     * Random value between 1000 and 15000 (step: 1000)
     *
     * @return The computed random value
     */
    public int getDamage() {
        int randomMultiplier = (int) Math.ceil(Math.random() * 15);
        return minDamage * randomMultiplier;
    }

    /**
     * Asteroid constructor
     *
     * @param name      The name of this asteroid
     * @param minDamage The minimum damage value
     */
    public Asteroid(String name, int minDamage) {
        super(name);
        this.minDamage = minDamage;
        this.dialogWindow = Windows.ASTEROID_DIALOG;
    }
}
