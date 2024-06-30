package view.customControls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import model.managers.GameManager;
import model.locations.Location;
import model.utils.PlayerPositions;

import java.io.IOException;

public class Board extends GridPane {
    /**
     * The path to the fxml file
     */
    private final static String PLATEAU_FXML = "/fxml/customControls/Board.fxml";

    /**
     * FXML components
     */
    @FXML
    public GridPane grid;

    /**
     * Locations used to create the board
     */
    private Location[] locations = GameManager.locations;

    /**
     * Board constructor
     *
     * @throws IOException Thrown by load method
     */
    public Board() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PLATEAU_FXML));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    /**
     * Initializes the controls
     *
     * @throws IOException Thrown by the Square constructor
     */
    @FXML
    private void initialize() throws IOException {
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(50);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(50);

        for (int i = 0; i <= PlayerPositions.BOARD_SIZE; i++) {
            grid.getColumnConstraints().add(col);
            grid.getRowConstraints().add(row);
        }

        int j = 0;
        for (int i = 0; i < PlayerPositions.BOARD_SIZE * 4; i++) {
            Square square = new Square();
            square.setLocation(locations[j]);
            square.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color:" + locations[j].getColor());
            grid.add(square, (int) PlayerPositions.getColRowFromPosition(i).getX(), (int) PlayerPositions.getColRowFromPosition(i).getY());
            j++;
        }
    }
}
