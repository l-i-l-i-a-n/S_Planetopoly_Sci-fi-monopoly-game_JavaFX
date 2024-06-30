package view.controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class JailDialogController extends LocationDialog {
    /**
     * Stage setter
     *
     * @param stage The new stage
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setOnHiding(windowEvent -> {
            sendToJail();
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

    /**
     * Sets the current player's "inJail" to true
     */
    public void sendToJail() {
        this.player.setInJail(true);
    }
}
