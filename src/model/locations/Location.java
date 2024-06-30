package model.locations;

import model.Player;
import model.utils.Windows;

import java.io.Serializable;

public abstract class Location implements Serializable {

    protected Windows dialogWindow;

    private String name;

    /**
     * Dialog window getter
     *
     * @param player The current player, who is on the square corresponding to this location
     * @return The dialog window corresponding to this location
     */
    public Windows getDialogWindow(Player player) {
        return dialogWindow;
    }

    /**
     * Name getter
     *
     * @return The name of this location
     */
    public String getName() {
        return name;
    }

    /**
     * Location color getter
     *
     * @return The color of this location, default is #1e1e1e (for not ownable locations)
     */
    public String getColor() {
        return "#1e1e1e";
    }

    /**
     * Location constructor
     *
     * @param name The name of the location
     */
    public Location(String name) {
        this.name = name;
    }

}
