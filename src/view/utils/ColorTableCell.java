package view.utils;

import javafx.scene.control.TableCell;
import javafx.scene.layout.Region;
import model.Player;
import model.utils.PlayerColors;

/**
 * Defines how the color cells are displayed in AllPlayersDetails
 */
public class ColorTableCell extends TableCell<Player, String> {
    private final Region graphic = new Region();

    public ColorTableCell() {
        graphic.setId("graphic");
    }

    @Override
    protected void updateItem(String value, boolean empty) {
        super.updateItem(value, empty);
        setText(null);
        String style = null;
        if (value != null && !empty) {
            style = String.format("-fx-background-color: %s", PlayerColors.colorHexNameConvert().get(value));
        }
        graphic.setStyle(style);
        setGraphic(graphic);
    }
}
