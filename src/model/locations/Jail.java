package model.locations;

import model.utils.Windows;

public class Jail extends Location {
    /**
     * Jail constructor
     * Also sets this.dialogWindow to the right value
     *
     * @param name The name of this jail
     */
    public Jail(String name) {
        super(name);
        this.dialogWindow = Windows.JAIL_DIALOG;
    }
}
