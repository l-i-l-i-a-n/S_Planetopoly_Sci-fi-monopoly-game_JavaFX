package model.persistance;

import model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * The class used to store our players observable collection which is not serializable by default
 */
public class PlayersListWrapper implements Serializable {
    /**
     * The serializable players list
     */
    private List<Player> playersList;

    /**
     * PlayersList getter
     *
     * @return The players list
     */
    public List<Player> getPlayersList() {
        return playersList;
    }

    /**
     * PlayersListWrapper constructor
     *
     * @param playersList The initial players list
     */
    public PlayersListWrapper(List<Player> playersList) {
        this.playersList = playersList;
    }

    /**
     * Defines how PlayersListWrapper objects will be de-serialized
     *
     * @param objectInputStream The stream where the data comes from
     * @throws IOException Thrown by the objectInputStream
     */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.playersList = (List<Player>) objectInputStream.readObject();
    }

    /**
     * Defines how PlayersListWrapper objects will be serialized
     *
     * @param objectOutputStream The stream where to write the data
     * @throws IOException Thrown by the objectOutputStream
     */
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(playersList);
    }
}
