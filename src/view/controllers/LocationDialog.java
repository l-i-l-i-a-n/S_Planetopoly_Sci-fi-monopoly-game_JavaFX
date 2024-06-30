package view.controllers;

import javafx.stage.Stage;
import model.Player;
import model.locations.Location;
import model.managers.GameManager;

import java.io.IOException;

public abstract class LocationDialog implements Manageable {
    /**
     * The location corresponding to this dialog
     */
    protected Location location;

    /**
     * The stage used to display this dialog
     */
    protected Stage stage;

    /**
     * The current player
     */
    protected Player player;

    /**
     * The gameManager instance, used to access game data
     */
    protected GameManager gameManager;

    /**
     * Stage setter
     * @param stage The new stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Player setter
     * @param player The current player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Location setter
     *
     * @param location The current location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * GameManager setter
     *
     * @param gameManager The new GameManager instance
     */
    @Override
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
