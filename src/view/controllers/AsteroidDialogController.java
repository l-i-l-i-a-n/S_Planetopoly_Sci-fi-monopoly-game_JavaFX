package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.locations.Asteroid;
import model.locations.Earth;
import model.locations.Location;
import model.locations.OwnableLocation;

import java.io.IOException;

public class AsteroidDialogController extends LocationDialog {

    /**
     * FXML components
     */
    @FXML
    private Label damageLabel;

    /**
     * The damage value of this asteroid
     */
    Integer damage;

    /**
     * Stage setter
     *
     * @param stage The new stage
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setOnHiding(windowEvent -> {
            updateMoneyBalance();
        });
    }

    /**
     * Location setter
     *
     * @param location The current location
     */
    @Override
    public void setLocation(Location location) {
        this.location = location;
        damage = ((Asteroid) this.location).getDamage();
        damageLabel.setText(damage.toString().concat("$"));
    }

    /**
     * Closes this dialog
     *
     * @param event The next button click event
     */
    public void nextHandler(ActionEvent event) {
        gameManager.closeStage(stage);
    }

    /**
     * Updates the current player money balance
     */
    public void updateMoneyBalance() {
        player.setMoneyBalance(player.getMoneyBalance() - damage);
    }
}