package model.utils;

import model.locations.*;

public abstract class AllLocations {
    /**
     * All the locations used in the game
     */
    public static final Location[] LOCATIONS = {
            new Earth("Earth", 10000),

            new Planet("Mars", 30000, 15000, "darkred"),
            new Asteroid("Asteroid", 1000),
            new Station("Station n°1", 20000, 10000, "darkred"),
            new Planet("Jupiter", 20000, 10000, "darkred"),

            new Jail("Jail"),

            new Planet("Saturn", 24000, 12000, "darkblue"),
            new Asteroid("Asteroid", 1000),
            new Station("Station n°2", 20000, 10000, "darkblue"),
            new Planet("Uranus", 10000, 5000, "darkblue"),

            new BlackHole("Black Hole"),

            new Planet("Neptune", 15000, 7500, "darkgreen"),
            new Asteroid("Asteroid", 1000),
            new Station("Station n°3", 20000, 10000, "darkgreen"),
            new Planet("Mercury", 20000, 10000, "darkgreen"),

            new Jail("Jail"),

            new Planet("Venus", 20000, 10000, "darkmagenta"),
            new Asteroid("Asteroid", 1000),
            new Station("Station n°4", 20000, 10000, "darkmagenta"),
            new Planet("Pluto", 6000, 3000, "darkmagenta"),
    };
}
