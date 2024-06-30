package model.locations;

import model.Player;
import model.utils.Windows;

public class Planet extends OwnableLocation {

    /**
     * The number of moons bought for this planet
     */
    private Integer numberOfMoon = 0;

    /**
     * NumberOfMoons getter
     *
     * @return The value of "numberOfMoon"
     */
    public Integer getNumberOfMoon() {
        return numberOfMoon;
    }

    /**
     * Returns the price of a moon
     * Depends on the number of moons already bought for this planet
     *
     * @return The correct price of a new moon
     */
    public Integer getPriceOfMoon() {
        return (numberOfMoon * 1000) + 1000;
    }

    /**
     * NumberOfMoons setter
     *
     * @param numberOfMoon The new number of moons
     */
    public void setNumberOfMoon(Integer numberOfMoon) {
        this.numberOfMoon = numberOfMoon;
    }

    /**
     * Planet constructor
     *
     * @param name  The name of this planet
     * @param price the price of this planet
     * @param tax   The tax of this planet
     * @param color The color of this planet
     */
    public Planet(String name, int price, int tax, String color) {
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
                return Windows.OWNED_PLANET_DIALOG;
            else
                return Windows.PAY_TAX_DIALOG;
        }
    }

}
