package view.utils;

import javafx.scene.control.ListCell;
import model.Player;
import model.utils.PlayerColors;

/**
 * Defines how players names are displayed in the list view of InitPlayersWindow
 */
public class PlayerCell extends ListCell<Player> {
    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void updateItem(Player player, boolean isEmpty) {
        super.updateItem(player, isEmpty);

        if (isEmpty || player == null) {
            setText(null);
            setGraphic(null);
            setStyle(null);
        } else {
            stringBuilder.append(player.getName());
            setText(stringBuilder.toString());
            setStyle("-fx-font-weight: bold; -fx-text-fill: " + PlayerColors.colorHexNameConvert().get(player.getColor().toString()));

            stringBuilder.delete(0, stringBuilder.length());
        }
    }
}