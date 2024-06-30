package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class OwnedStationDialogController extends LocationDialog {

    /**
     * Stage setter
     *
     * @param stage The new stage
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setOnHiding(windowEvent -> {
            continueTurn();
        });
    }

    /**
     * Closes this dialog
     *
     * @param event The next button click event
     */
    public void nextHandler(ActionEvent event) {
        this.gameManager.closeStage(stage);
    }

    public void continueTurn() {
        this.stage.setOnHidden(null);
    }
}
