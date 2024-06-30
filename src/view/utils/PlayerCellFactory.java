package view.utils;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Player;
import view.utils.PlayerCell;

public class PlayerCellFactory implements Callback<ListView<Player>, ListCell<Player>> {
    @Override
    public ListCell<Player> call(ListView<Player> playerListView) {
        return new PlayerCell();
    }
}
