package view.controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.locations.Planet;
import model.utils.PlayerPositions;

public class BlackHoleDialogController extends LocationDialog {
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

    /**
     * Triggers a second turn for the player
     */
    public void continueTurn() {
        this.stage.setOnHidden(windowEvent -> {
            int movement = (int) Math.ceil(Math.random() * (PlayerPositions.BOARD_SIZE * 4) - 1);
            try {
                gameManager.getMainGameController().playTurn(this.player, movement, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
