package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.locations.Location;
import model.locations.OwnableLocation;

public class OwnableLocationDialogController extends LocationDialog {

    /**
     * FXML components
     */
    @FXML
    private Label title;
    @FXML
    private Label price;

    /**
     * Location setter
     *
     * @param location The current location
     */
    @Override
    public void setLocation(Location location) {
        this.location = location;
        title.setText(location.getName());
        title.setStyle("-fx-text-fill: " + location.getColor());
        price.setText(((OwnableLocation) this.location).getPrice().toString().concat("$"));
    }

    /**
     * Handles the "buy" button click
     *
     * @param event The button click event
     */
    public void buyPlanetHandler(ActionEvent event) {
        boolean ownsOfColor = ownsLocationOfColor(this.location.getColor());

        ((OwnableLocation) this.location).setOwned(true);
        ((OwnableLocation) this.location).setOwner(player);
        ((OwnableLocation) this.location).setOwnerName(player.getName());
        Integer oldMoney = player.getMoneyBalance();
        Integer newMoney = oldMoney - ((OwnableLocation) this.location).getPrice();
        player.setMoneyBalance(newMoney);
        player.getOwnedLocations().add((OwnableLocation) this.location);

        if (ownsOfColor)
            incrementValueOfLocationsOfColor(this.location.getColor());

        gameManager.closeStage(stage);
    }

    /**
     * Closes this dialog
     *
     * @param event The next button click event
     */
    public void nextHandler(ActionEvent event) {
        gameManager.closeStage(stage);
    }

    public boolean ownsLocationOfColor(String color) {
        for (OwnableLocation l : player.getOwnedLocations()) {
            if (l.getColor().equals(color))
                return true;
        }
        return false;
    }

    /**
     * Updates the price and tax of all locations of the given color, for locations that belong to the current player
     *
     * @param color The color of locations
     */
    public void incrementValueOfLocationsOfColor(String color) {
        for (OwnableLocation l : player.getOwnedLocations()) {
            if (l.getColor().equals(color)) {
                l.setTax(l.getTax() + 5000);
                l.setPrice(l.getPrice() + 5000);
            }
        }
    }
}
