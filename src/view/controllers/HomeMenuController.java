package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import model.managers.GameManager;
import view.utils.AlertBox;

import java.io.File;
import java.io.IOException;

public class HomeMenuController implements Manageable {

    /**
     * FXML components
     */
    @FXML
    private Button newGamebutton;
    @FXML
    private Button resumeSavedGameButton;

    /**
     * The gameManager instance, used to access game data
     */
    private GameManager gameManager;

    /**
     * GameManager setter
     *
     * @param gameManager The new GameManager instance
     */
    @Override
    public void setGameManager(GameManager gameManager) throws IOException {
        this.gameManager = gameManager;
        if (gameManager.getPersistence().getGameFile() == null)
            resumeSavedGameButton.setDisable(true);
    }

    /**
     * Handles the new game button click
     *
     * @param event The button click event
     * @throws IOException Thrown by initNewGame method
     */
    public void newGameHandler(ActionEvent event) throws IOException {
        gameManager.initNewGame();
    }

    /**
     * Handles the "resume saved game" button click
     *
     * @param event The button click event
     * @throws Exception Thrown by initNewGame method or initSavedGame method
     */
    public void resumeSavedGameHandler(ActionEvent event) throws Exception {
        File gameFile = gameManager.getPersistence().getGameFile();
        if (!gameFile.canRead()) {
            AlertBox.show(Alert.AlertType.ERROR, "Failed to load game data: unable to find or open save file.");
            gameManager.getPersistence().setGameFile(null);
            gameManager.initNewGame();
        } else {
            gameManager.initSavedGame(gameFile);
        }
    }
}
