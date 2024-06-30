package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.locations.Asteroid;
import model.locations.Earth;
import model.locations.Location;

public class EarthDialogController extends LocationDialog {

    /**
     * FXML components
     */
    @FXML
    private Label paycheckLabel;

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
        paycheckLabel.setText(((Earth) this.location).getPaycheck().toString().concat("$"));
    }

    /**
     * Closes this dialog
     *
     * @param actionEvent The next button click event
     */
    public void nextHandler(ActionEvent actionEvent) {
        gameManager.closeStage(stage);
    }

    /**
     * Updates the current player money balance
     */
    public void updateMoneyBalance() {
        player.setMoneyBalance(player.getMoneyBalance() + ((Earth) this.location).getPaycheck());
    }
}