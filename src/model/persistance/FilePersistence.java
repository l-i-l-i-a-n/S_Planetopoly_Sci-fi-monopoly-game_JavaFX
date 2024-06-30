package model.persistance;

import javafx.collections.FXCollections;
import model.Player;
import model.locations.Location;
import model.managers.GameManager;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class FilePersistence implements IPersistence {

    /**
     * Retrieves the gameFilePath from system preferences and returns the corresponding file
     *
     * @return The file containing previously saved game data
     */
    @Override
    public File getGameFile() {
        Preferences preferences = Preferences.userNodeForPackage(GameManager.class);
        String filePath = preferences.get("gameDataFilePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Stores the gameFilePath in system preferences
     *
     * @param file The file which we want to store the path
     */
    @Override
    public void setGameFile(File file) {
        Preferences preferences = Preferences.userNodeForPackage(GameManager.class);
        if (file != null) {
            preferences.put("gameDataFilePath", file.getPath());
        } else {
            preferences.remove("gameDataFilePath");
        }
    }

    /**
     * Serializes game data to the given file
     *
     * @param gameDataFile The file in which we want to store the data
     * @param gameManager  The game manager containing the data
     */
    @Override
    public void saveGameDataToFile(File gameDataFile, GameManager gameManager) {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(gameDataFile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            /**/
            PlayersListWrapper playersListWrapper = new PlayersListWrapper(new ArrayList<>(gameManager.playersListProperty()));
            objectOutputStream.writeObject(playersListWrapper);

            objectOutputStream.writeObject(gameManager.getCurrentPlayerName());
            objectOutputStream.writeObject(gameManager.getNbPlayers());

            objectOutputStream.writeObject(GameManager.locations);
            /**/

            setGameFile(gameDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * De-serializes game data from the given file
     *
     * @param gameDataFile The file containing our data
     * @param gameManager  The game manager to store the de-serialized data
     */
    @Override
    public void readGameDataFromFile(File gameDataFile, GameManager gameManager) {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(gameDataFile);
            objectInputStream = new ObjectInputStream(fileInputStream);

            /**/
            PlayersListWrapper playersListWrapper = (PlayersListWrapper) objectInputStream.readObject();
            gameManager.setPlayersList(FXCollections.observableArrayList(playersListWrapper.getPlayersList()));

            gameManager.setCurrentPlayerName((String) objectInputStream.readObject());
            Player currPlayer = null;
            for (Player p : gameManager.getPlayersList()) {
                if (p.getName().equals(gameManager.getCurrentPlayerName()))
                    currPlayer = p;
            }
            gameManager.setCurrentPlayer(currPlayer);
            gameManager.setNbPlayers((Integer) objectInputStream.readObject());

            GameManager.locations = (Location[]) objectInputStream.readObject();
            /**/

            setGameFile(gameDataFile);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
