package view.controllers;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Player;
import model.locations.OwnableLocation;
import model.managers.GameManager;
import model.locations.Location;
import model.utils.PlayerPositions;
import view.customControls.*;
import view.utils.AlertBox;

import java.io.IOException;

public class MainGameController extends Parent implements Manageable {

    /**
     * The gameManager instance, used to access game data
     */
    private GameManager gameManager;

    /**
     * GameManager setter
     * Also initializes some UI elements
     *
     * @param gameManager The new GameManager instance
     */
    @Override
    public void setGameManager(GameManager gameManager) throws IOException {
        this.gameManager = gameManager;
        setInitialControllerData();
        allPlayersDetailsNode.setGameManager(gameManager);
    }

    /**
     * Initializes some UI elements
     *
     * @throws IOException Thrown by the Piece constructor
     */
    public void setInitialControllerData() throws IOException {
        if (!gameManager.isSavedGame()) {
            gameManager.setNbPlayers(gameManager.getPlayersList().size());
            gameManager.setCurrentPlayer(gameManager.getPlayersList().get(0));
            gameManager.setCurrentPlayerName(gameManager.getCurrentPlayer().getName());
        }
        for (Player p : gameManager.getPlayersList()) {
            Piece piece = new Piece();
            piece.setColor(p.getColor());
            piece.setId(p.getName());
            piece.setLayoutX((gameGridPane.getWidth() / 6 / 2) - (piece.getWidth() / 2) + Math.random() * 10 - Math.random() * 10);
            piece.setLayoutY((gameGridPane.getHeight() / 6 / 2) - (piece.getHeight() / 2) + Math.random() * 10 - Math.random() * 10);
            piecesPane.getChildren().add(piece);
            if (gameManager.isSavedGame()) piece.setPosition(p.getPosition());
        }
        currentPlayerLabel.textProperty().bind(gameManager.currentPlayerNameProperty());
    }

    /**
     * FXML components
     */
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private BorderPane mainGameBorderPane;
    @FXML
    private Dice diceNode;
    @FXML
    private AllPlayersDetails allPlayersDetailsNode;
    @FXML
    private Pane piecesPane;
    @FXML
    private Button launchDiceButton;
    @FXML
    private Board gameBoard;
    @FXML
    private GridPane gameGridPane;
    @FXML
    private Button saveGameButton;

    /**
     * Initializes controls
     */
    @FXML
    private void initialize() {
        gameGridPane.prefWidthProperty().bind(gameGridPane.heightProperty());
        gameGridPane.minWidthProperty().bind(gameGridPane.heightProperty());
        gameGridPane.maxWidthProperty().bind(gameGridPane.heightProperty());

        piecesPane.widthProperty().addListener(stageSizeListener);
        piecesPane.heightProperty().addListener(stageSizeListener);
    }

    /**
     * Handles launch dice button press
     *
     * @param mouseEvent The action event from the button
     */
    public void launchDice(ActionEvent mouseEvent) {
        launchDiceButton.setDisable(true);  // disables the button until next turn
        Player currPlayer = gameManager.getCurrentPlayer();
        int val = diceNode.handleLaunchDice();
        playTurn(currPlayer, val, false);
    }

    /**
     * Plays the action of the current turn for the current player
     * (move player piece, display the location dialog)
     *
     * @param player          The current player
     * @param movement        The movement of the player (from the dice value or from the BlackHole square)
     * @param isBlackHoleTurn Specifies if this turn has been triggered by a black hole
     */
    public void playTurn(Player player, int movement, boolean isBlackHoleTurn) {
        double time = updatePlayerPosition(player, movement, isBlackHoleTurn);
        Thread taction = new Thread(() -> {
            try {
                Thread.sleep((long) time);
                Platform.runLater(() -> {
                    try {
                        doAction(player);
                        launchDiceButton.setDisable(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        taction.start();
    }

    /**
     * Switches turn
     * Sets the new current player and updates all turn related data
     * Triggers game end if necessary
     */
    public void switchTurn() {
        Player currentPlayer = gameManager.getCurrentPlayer();
        int currentPlayerIndex = gameManager.getPlayersList().indexOf(currentPlayer);
        Player nextCurrentPlayer;

        if (currentPlayer.getMoneyBalance() <= 0) {
            AlertBox.show(Alert.AlertType.INFORMATION, currentPlayer.getName().concat(" is excluded !"));
            gameManager.getPlayersList().remove(currentPlayer);
            gameManager.setNbPlayers(gameManager.getNbPlayers() - 1);
            removePiece(currentPlayer.getName());
            removeOwnedLocations(currentPlayer);
        }

        if (gameManager.getPlayersList().contains(currentPlayer)) {
            if (currentPlayerIndex + 1 < gameManager.getNbPlayers())
                nextCurrentPlayer = gameManager.getPlayersList().get(currentPlayerIndex + 1);
            else nextCurrentPlayer = gameManager.getPlayersList().get(0);
        } else {
            if (currentPlayerIndex < gameManager.getNbPlayers())
                nextCurrentPlayer = gameManager.getPlayersList().get(currentPlayerIndex);
            else nextCurrentPlayer = gameManager.getPlayersList().get(0);
        }

        // Sets the next player
        gameManager.setCurrentPlayer(nextCurrentPlayer);
        gameManager.setCurrentPlayerName(gameManager.getCurrentPlayer().getName());

        if (gameManager.getCurrentPlayer().isInJail()) {
            gameManager.getCurrentPlayer().setInJail(false);
            AlertBox.show(Alert.AlertType.INFORMATION, gameManager.getCurrentPlayerName().concat(": You are free, you will be able to play next turn !"));
            switchTurn();
        }

        // Checks if the game is finished
        if (gameManager.getNbPlayers() <= 1) {
            AlertBox.show(Alert.AlertType.INFORMATION, "Game finished ! The winner is ".concat(gameManager.getCurrentPlayer().getName()));
            gameManager.endGame();
        }
    }

    /**
     * Updates the position of the given player
     * Performs a smooth animation on the players piece
     *
     * @param player      The player whose position should be updated
     * @param diceValue   the dice value
     * @param isBlackHole Specifies if this turn has been triggered by a black hole
     * @return The time of the transition
     */
    public double updatePlayerPosition(Player player, int diceValue, boolean isBlackHole) {
        Piece p = getPieceFromId(player.getName());
        boolean isOnALine = (0 <= p.getPosition() && p.getPosition() < 5) || (10 <= p.getPosition() && p.getPosition() < 15);

        //calcul des nouvelles coordonnées colonne ligne du Point2D
        Point2D oldColRow = PlayerPositions.getColRowFromPosition(p.getPosition());
        p.setPosition(p.getPosition() + diceValue);
        if (p.getPosition() >= PlayerPositions.BOARD_SIZE * 4)
            p.setPosition(p.getPosition() - PlayerPositions.BOARD_SIZE * 4);
        Point2D newColRow = PlayerPositions.getColRowFromPosition(p.getPosition());

        double mvtX = (piecesPane.getWidth() / 6) * newColRow.getX() - (piecesPane.getWidth() / 6) * oldColRow.getX();
        double mvtY = (piecesPane.getHeight() / 6) * newColRow.getY() - (piecesPane.getHeight() / 6) * oldColRow.getY();

        if (isBlackHole) {
            Duration duration = Duration.millis(200);
            TranslateTransition blackHoleTransition = new TranslateTransition(duration, p);
            blackHoleTransition.setCycleCount(1);
            blackHoleTransition.setAutoReverse(false);
            blackHoleTransition.setByX(mvtX);
            blackHoleTransition.setByY(mvtY);
            blackHoleTransition.play();

            player.setPosition(p.getPosition());
            return duration.toMillis();
        }

        Duration timeX = Duration.millis(400 * Math.abs(newColRow.getX() - oldColRow.getX()));
        Duration timeY = Duration.millis(400 * Math.abs(newColRow.getY() - oldColRow.getY()));
        double totalTime = timeX.toMillis() + timeY.toMillis();

        TranslateTransition transitionX = new TranslateTransition(timeX, p);
        transitionX.setCycleCount(1);
        transitionX.setAutoReverse(false);
        transitionX.setByX(mvtX);

        TranslateTransition transitionY = new TranslateTransition(timeY, p);
        transitionY.setCycleCount(1);
        transitionY.setAutoReverse(false);
        transitionY.setByY(mvtY);

        if (isOnALine) { //le pion est initialement sur une ligne (haut ou bas)
            transitionX.setOnFinished(event -> {
                transitionY.play();
            });
            transitionX.setByY(Math.random() * 10 - Math.random() * 10);
            transitionX.play();
        } else { //le pion est initialement sur une colonne (gauche ou droite)
            transitionY.setOnFinished(event -> {
                transitionX.play();
            });
            transitionY.setByX(Math.random() * 10 - Math.random() * 10);
            transitionY.play();
        }

        player.setPosition(p.getPosition());
        return totalTime;
    }

    ChangeListener<Number> stageSizeListener = (observableValue, number, t1) -> {
        for (Player p : gameManager.getPlayersList()) {
            Piece piece = getPieceFromId(p.getName());
            Point2D pieceColRow = PlayerPositions.getColRowFromPosition(p.getPosition());
            double newLayX = (gameGridPane.getWidth() / 6) * pieceColRow.getX() + 1;
            double newLayY = (gameGridPane.getHeight() / 6) * pieceColRow.getY() + 1;
            piece.setTranslateX(newLayX + (gameGridPane.getWidth() / 6 / 2) - (piece.getWidth() / 2));
            piece.setTranslateY(newLayY + (gameGridPane.getHeight() / 6 / 2) - (piece.getHeight() / 2));
        }
    };

    /**
     * Displays the dialog corresponding to the current location, for the current player
     *
     * @param player The current player
     * @throws IOException Thrown by displayLocationDialog method
     */
    private void doAction(Player player) throws IOException {
        Location currentLocation = GameManager.locations[player.getPosition()];
        gameManager.displayLocationDialog(player, currentLocation);
    }

    /**
     * Retireves the Piece instance with the given id
     *
     * @param idPlayer The id of the player
     * @return The piece of the player
     */
    public Piece getPieceFromId(String idPlayer) {
        for (Node n : piecesPane.getChildren()) {
            if (n instanceof Piece && n.getId().equals(idPlayer)) {
                return (Piece) n;
            }
        }
        return null;
    }

    /**
     * Removes the piece of a player from the board
     *
     * @param idPlayer The id of the player
     */
    public void removePiece(String idPlayer) {
        piecesPane.getChildren().removeIf(n -> n instanceof Piece && n.getId().equals(idPlayer));
    }

    /**
     * Sets the owner to null for each lcoation of the given player
     *
     * @param player The given player
     */
    private void removeOwnedLocations(Player player) {
        for (Location location : GameManager.locations) {
            if (location instanceof OwnableLocation) {
                if (((OwnableLocation) location).getOwner() == player) {
                    ((OwnableLocation) location).setOwned(false);
                    ((OwnableLocation) location).setOwner(null);
                    ((OwnableLocation) location).setOwnerName(null);
                }
            }
        }
    }

    /**
     * Handles the save game button press
     *
     * @param event The event from the button
     */
    public void saveGameButtonHandler(ActionEvent event) {
        gameManager.saveGameAndClose();
    }
}