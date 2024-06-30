package model.locations;

import model.Player;
import model.utils.Windows;

public class Station extends OwnableLocation {

    /**
     * Station constructor
     *
     * @param name  The name of this station
     * @param price The price of this station
     * @param tax   The tax for this station
     * @param color The color if this station
     */
    public Station(String name, int price, int tax, String color) {
        super(name, price, tax, color);
    }

    /**
     * Dialog window getter
     * Returns a different value depending on the value of isOwned and the current player
     *
     * @param player The current player, who is on the square corresponding to this location
     * @return The dialog window corresponding to this location
     */
    @Override
    public Windows getDialogWindow(Player player) {
        if (!this.isOwned())
            return Windows.OWNABLE_LOCATION_DIALOG;
        else {
            if (player == this.getOwner())
                return Windows.OWNED_STATION_DIALOG;
            else
                return Windows.PAY_TAX_DIALOG;
        }
    }

}
