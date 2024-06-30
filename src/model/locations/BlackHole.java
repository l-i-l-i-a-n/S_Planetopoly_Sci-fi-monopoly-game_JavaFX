package model.locations;

import model.utils.Windows;

public class BlackHole extends Location {
    /**
     * BlackHole constructor
     * Also sets this.dialogWindow to the right value
     * @param name The name of this black hole
     */
    public BlackHole(String name) {
        super(name);
        this.dialogWindow = Windows.BLACK_HOLE_DIALOG;
    }
}
