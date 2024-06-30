package view.utils;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import model.Player;

public class ColorTableCellFactory implements javafx.util.Callback<javafx.scene.control.TableColumn<model.Player, String>, javafx.scene.control.TableCell<model.Player, String>> {
    @Override
    public TableCell<Player, String> call(TableColumn<Player, String> playerStringTableColumn) {
        return new ColorTableCell();
    }
}
