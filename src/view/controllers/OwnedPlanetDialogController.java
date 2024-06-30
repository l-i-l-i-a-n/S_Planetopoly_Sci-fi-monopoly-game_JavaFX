package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.locations.Location;
import model.locations.Planet;
import view.utils.AlertBox;

public class OwnedPlanetDialogController extends LocationDialog {

    /**
     * FXML components
     */
    @FXML
    private Button buyMoonButton;
    @FXML
    private Label title;
    @FXML
    private Label price;
    @FXML
    private Label nbMoon;

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
        price.setText(((Planet) location).getPriceOfMoon().toString().concat("$"));
        nbMoon.setText("You own ".concat(((Planet) location).getNumberOfMoon().toString()).concat(" moons of this planet"));
    }

    /**
     * Handles the "buy moon" button click
     * Updates the number of moons for the planet
     *
     * @param actionEvent The button click event
     */
    public void buyMoonHandler(ActionEvent actionEvent) {
        if (((Planet) location).getNumberOfMoon() <= 4) {
            ((Planet) location).setTax(((Planet) location).getTax() + ((Planet) location).getPriceOfMoon());
            ((Planet) location).setPrice(((Planet) location).getPrice() + ((Planet) location).getPriceOfMoon());
            player.setMoneyBalance(player.getMoneyBalance() - ((Planet) location).getPriceOfMoon());
            ((Planet) location).setNumberOfMoon((((Planet) location).getNumberOfMoon()) + 1);
        } else {
            AlertBox.show(Alert.AlertType.ERROR, "You already own 4 moons here !");
        }
        gameManager.closeStage(stage);
    }

    /**
     * Closes this dialog
     *
     * @param actionEvent The next button click event
     */
    public void nextHandler(ActionEvent actionEvent) {
        gameManager.closeStage(stage);
    }
}
