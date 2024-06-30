package model.persistance;
import model.managers.GameManager;

import java.io.*;

public interface IPersistence {

    /**
     * Retrieves the gameFilePath from system preferences and returns the corresponding file
     *
     * @return The file containing previously saved game data
     */
    File getGameFile();

    /**
     * Stores the gameFilePath in system preferences
     *
     * @param file The file which we want to store the path
     */
    void setGameFile(File file);

    /**
     * Serializes game data to the given file
     *
     * @param gameDataFile The file in which we want to store the data
     * @param gameManager  The game manager containing the data
     */
    void saveGameDataToFile(File gameDataFile, GameManager gameManager);

    /**
     * De-serializes game data from the given file
     *
     * @param gameDataFile The file containing our data
     * @param gameManager  The game manager to store the de-serialized data
     */
    void readGameDataFromFile(File gameDataFile, GameManager gameManager);

}
