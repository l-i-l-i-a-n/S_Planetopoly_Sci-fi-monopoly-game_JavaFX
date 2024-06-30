package view.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Player;
import model.managers.GameManager;
import model.utils.PlayerColors;
import model.utils.Windows;
import view.utils.AlertBox;
import view.utils.PlayerCellFactory;

import javax.swing.plaf.FileChooserUI;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitPlayersController implements Manageable {
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
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        playersListView.itemsProperty().bind(gameManager.playersListProperty());
    }

    /**
     * Stores all the player names already in use to avoid having two players with the same name
     */
    private List<String> usedNames = new ArrayList<>();

    /**
     * Stores all the player colors already in use to avoid having two players with the same color
     */
    private List<String> usedColors = new ArrayList<>();

    /**
     * FXML components
     */
    @FXML
    private ListView<Player> playersListView;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField playerNameTextField;
    @FXML
    private ComboBox<String> colorComboBox;
    @FXML
    private GridPane mainPane;

    /**
     * Initializes controls
     */
    @FXML
    private void initialize() {
        ObservableList<String> availableColors = PlayerColors.getColorsList();
        playersListView.setCellFactory(new PlayerCellFactory());
        colorComboBox.setItems(availableColors);
    }

    /**
     * Handles the add player button press
     * Checks if palyer details are correct before adding the player to players list
     *
     * @param event The event from the button
     */
    public void addPlayer(ActionEvent event) {
        String givenName = nameTextField.getText();
        String givenColor = colorComboBox.getValue();
        if (gameManager.getNbPlayers() >= 4) {
            AlertBox.show(Alert.AlertType.ERROR, "Too many players ! (max. 4)");
        } else if (nameTextField.getText().trim().isEmpty() || colorComboBox.getValue() == null) {
            AlertBox.show(Alert.AlertType.ERROR, "Please fill in all the fields !");
        } else if (usedNames.contains(givenName) || usedColors.contains(givenColor)) {
            AlertBox.show(Alert.AlertType.ERROR, "Player details already in use !");
        } else {
            gameManager.getPlayersList().add(new Player(givenName, Paint.valueOf(givenColor)));
            gameManager.setNbPlayers(gameManager.getNbPlayers() + 1);
            this.usedNames.add(givenName);
            this.usedColors.add(givenColor);
        }
    }

    /**
     * Starts the game if players data is correct (enough players)
     *
     * @param event The event from the start game button
     * @throws IOException Thrown by changeScene method
     */
    public void startGame(ActionEvent event) throws IOException {
        if (gameManager.playersListProperty().getSize() <= 1) {
            AlertBox.show(Alert.AlertType.ERROR, "Not enough players !");
        } else {
            gameManager.changeScene(Windows.MAIN_GAME);
        }
    }
}
