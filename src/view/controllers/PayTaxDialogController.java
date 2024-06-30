package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Player;
import model.locations.Location;
import model.locations.OwnableLocation;

public class PayTaxDialogController extends LocationDialog {
    /**
     * FXML components
     */
    @FXML
    private Label titleLabel;
    @FXML
    private Label taxLabel;

    /**
     * Stage setter
     *
     * @param stage The new stage
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setOnHiding(windowEvent -> {
            payTax();
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
        titleLabel.setText(location.getName());
        titleLabel.setStyle("-fx-text-fill: " + location.getColor());
        taxLabel.setText(((OwnableLocation) this.location).getTax().toString().concat("$"));
    }

    /**
     * Handles the "pay tax" button click
     *
     * @param event The button click event
     */
    public void payTaxButtonHandler(ActionEvent event) {
        gameManager.closeStage(stage);
    }

    /**
     * Updates the current player money
     */
    public void payTax() {
        Integer oldMoney = player.getMoneyBalance();
        Integer newMoney = oldMoney - ((OwnableLocation) this.location).getTax();
        player.setMoneyBalance(newMoney);
        Player owner = ((OwnableLocation) this.location).getOwner();
        Integer oldOwnerMoney = owner.getMoneyBalance();
        Integer newOwnerMoney = oldOwnerMoney + ((OwnableLocation) this.location).getTax();
        ((OwnableLocation) this.location).getOwner().setMoneyBalance(newOwnerMoney);
    }
}
