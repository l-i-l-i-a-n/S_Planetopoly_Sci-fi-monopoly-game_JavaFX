package view.customControls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class Piece extends VBox {
    /**
     * The path to the fxml file
     */
    private static final String PIECE_FXML = "/fxml/customControls/Piece.fxml";

    /**
     * FXML components
     */
    @FXML
    private Circle circle;
    @FXML
    private Label labelPlayerName;


    /**
     * The current position of this piece
     */
    private int position = 0;

    /**
     * Position getter
     *
     * @return The value of "position"
     */
    public int getPosition() {
        return position;
    }

    /**
     * Position setter
     *
     * @param position The new position of this piece
     */
    public void setPosition(int position) {
        this.position = position;
    }


    /**
     * The color of this piece, corresponds to the player color
     */
    private ObjectProperty<Paint> color = new SimpleObjectProperty<>();

    public ObjectProperty<Paint> colorProperty() {
        return color;
    }

    /**
     * Color getter
     *
     * @return The value of "color"
     */
    public Paint getColor() {
        return this.color.get();
    }

    /**
     * Color setter
     *
     * @param color The new value of "color"
     */
    public void setColor(Paint color) {
        this.color.set(color);
    }

    /**
     * Piece constructor
     *
     * @throws IOException Thrown by load method
     */
    public Piece() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PIECE_FXML));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    /**
     * Initializes controls
     */
    @FXML
    private void initialize() {
        circle.fillProperty().bind(this.color);
    }
}
