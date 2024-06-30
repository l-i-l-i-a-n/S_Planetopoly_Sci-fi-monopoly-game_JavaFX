package view.controllers;

import model.managers.GameManager;

import java.io.IOException;

/**
 * Used to provide the controllers an access to the game manager
 */
public interface Manageable {
    /**
     * GameManager setter
     *
     * @param gameManager The new GameManager instance
     */
    void setGameManager(GameManager gameManager) throws IOException;
}
