package model.managers;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Player;
import model.locations.Location;
import model.persistance.IPersistence;
import model.utils.AllLocations;
import model.utils.Windows;
import view.controllers.*;

import java.io.*;

public class GameManager {
    /**
     * The main stage of this application
     */
    private final Stage stage;

    /**
     * Stage getter
     *
     * @return The instance of Stage
     */
    public Stage getStage() {
        return stage;
    }


    /**
     * The instance used to serialize/deserialize game data
     */
    private final IPersistence persistence;

    /**
     * Persistence getter
     *
     * @return The instance of IPersistence
     */
    public IPersistence getPersistence() {
        return persistence;
    }


    /**
     * The main game controller of this app
     */
    private MainGameController mainGameController;

    /**
     * MainGameController getter
     *
     * @return The instance of MainGameController
     */
    public MainGameController getMainGameController() {
        return mainGameController;
    }

    /******************************************************************************************
     * Game data
     **/

    /**
     * The current player instance
     */
    private Player currentPlayer;

    /**
     * CurrentPlayer getter
     *
     * @return The instance of Player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * CurrentPlayer setter
     *
     * @param currentPlayer The new Player instance
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * The name of the current player
     * Used to display the name in the UI
     */
    private StringProperty currentPlayerName = new SimpleStringProperty();

    public StringProperty currentPlayerNameProperty() {
        return currentPlayerName;
    }

    /**
     * CurrentPlayerName getter
     *
     * @return The current player name
     */
    public String getCurrentPlayerName() {
        return currentPlayerName.get();
    }

    /**
     * CurrentPlayerName setter
     *
     * @param name The new name
     */
    public void setCurrentPlayerName(String name) {
        this.currentPlayerName.set(name);
    }

    /**
     * Number of players in the game
     */
    private int nbPlayers = 0;

    /**
     * NbPlayers getter
     *
     * @return The value of nbPlayers
     */
    public int getNbPlayers() {
        return nbPlayers;
    }

    /**
     * NbPlayers setter
     *
     * @param nbPlayers The new  value of nbPlayers
     */
    public void setNbPlayers(int nbPlayers) {
        this.nbPlayers = nbPlayers;
    }

    /**
     * The list of players in the game
     */
    private ListProperty<Player> playersList = new SimpleListProperty<Player>(FXCollections.observableArrayList());

    public ListProperty<Player> playersListProperty() {
        return playersList;
    }

    /**
     * PlayersList getter
     *
     * @return The players list value
     */
    public ObservableList<Player> getPlayersList() {
        return playersList.get();
    }

    /**
     * PlayersList setter
     *
     * @param playersList The new players list
     */
    public void setPlayersList(ObservableList<Player> playersList) {
        this.playersList.set(playersList);
    }

    /**
     * All the locations used in this game
     */
    public static Location[] locations;

    /**
     * Specifies if the current game has been created from saved data or not
     */
    private boolean isSavedGame = false;

    /**
     * IsSavedGame getter
     *
     * @return The value of isSavedGame
     */
    public boolean isSavedGame() {
        return isSavedGame;
    }

    /**
     * GameManager constructor
     *
     * @param stage       The main stage of the app
     * @param persistence The IPersistance instance
     * @throws Exception Thrown during initialization
     */
    public GameManager(Stage stage, IPersistence persistence) throws Exception {
        this.stage = stage;
        this.persistence = persistence;
        this.initialDisplay();
    }

    /**
     * Sets basic stage properties and displays the first window: home window, to let the user start a new game or continue the previous game
     *
     * @throws Exception Thrown during initialization
     */
    private void initialDisplay() throws Exception {
        this.stage.setTitle("Planetopoly");
        this.stage.setMinWidth(640);
        this.stage.setMinHeight(360);
        changeScene(Windows.HOME_MENU);
    }

    /**
     * Launches a new game
     * Called from home window
     *
     * @throws IOException Thrown by changeScene method
     */
    public void initNewGame() throws IOException {
        locations = AllLocations.LOCATIONS;
        changeScene(Windows.INIT_PLAYERS);
    }

    /**
     * Launches a previously saved game, with all previously stored game data
     *
     * @param saveFile The file where to retrieve game data
     * @throws Exception Thrown by changeScene method
     */
    public void initSavedGame(File saveFile) throws Exception {
        persistence.readGameDataFromFile(saveFile, this);
        this.isSavedGame = true;
        changeScene(Windows.MAIN_GAME);
    }

    /**
     * Changes the current UI to another UI
     *
     * @param window The window we want to display
     * @throws IOException Thrown by load method
     */
    public void changeScene(Windows window) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(window.toString()));
        Parent root = loader.load();

        Manageable ctrl = loader.getController();
        ctrl.setGameManager(this);

        Scene scene = new Scene(root, Color.BLACK);
        scene.getStylesheets().add(getClass().getResource("/style/generalStyle.css").toExternalForm());

        this.stage.setScene(scene);
        this.stage.show();
        if (window == Windows.MAIN_GAME) this.stage.setMaximized(true);
        else this.stage.setMaximized(false);

        if (window == Windows.MAIN_GAME) {
            mainGameController = loader.getController();
        }
    }

    /**
     * Displays a dialog corresponding to the given location, on top of the main game window
     *
     * @param player   The current player
     * @param location The location where the current player is located
     * @throws IOException Thrown by load method
     */
    public void displayLocationDialog(Player player, Location location) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(location.getDialogWindow(player).toString()));
        Parent root = loader.load();

        Stage dialog = new Stage();
        LocationDialog ctrl = loader.getController();
        ctrl.setGameManager(this);
        ctrl.setPlayer(player);
        ctrl.setLocation(location);
        ctrl.setStage(dialog);

        Scene scene = new Scene(root, Color.BLACK);
        scene.getStylesheets().add(getClass().getResource("/style/generalStyle.css").toExternalForm());
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);
        dialog.setOnHidden(windowEvent -> mainGameController.switchTurn());
        dialog.show();
    }

    /**
     * Closes the given stage
     * Used to close dialog windows
     *
     * @param stage The stage to close
     */
    public void closeStage(Stage stage) {
        stage.close();
    }

    /**
     * Called when the game has finished and closes the app
     */
    public void endGame() {
        persistence.setGameFile(null);
        Platform.exit();
    }

    /**
     * Saves game data and closes the app, so that players can continue later
     */
    public void saveGameAndClose() {
        File gameDataFile = persistence.getGameFile();
        if (gameDataFile != null) {
            persistence.saveGameDataToFile(gameDataFile, this);
        } else {
            saveGameAs();
        }
        Platform.exit();
    }

    /**
     * Opens a dialog windows to let the user choose the file he wants to use to save game data
     */
    private void saveGameAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Any file extension", "*.*");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File gameDataFile = fileChooser.showSaveDialog(new Stage());

        if (gameDataFile != null) {
            if (!gameDataFile.getPath().endsWith(".bin")) {
                gameDataFile = new File(gameDataFile.getPath() + ".bin");
            }
            persistence.saveGameDataToFile(gameDataFile, this);
        }
    }

}