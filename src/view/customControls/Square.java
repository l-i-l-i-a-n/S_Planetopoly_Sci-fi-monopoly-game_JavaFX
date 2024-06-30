package view.customControls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.locations.Location;
import model.locations.OwnableLocation;

import java.io.IOException;

public class Square extends GridPane {
    /**
     * The path to the fxml file
     */
    private static final String SQUARE_FXML = "/fxml/customControls/Square.fxml";

    /**
     * FXML components
     */
    @FXML
    private Label nameSquareLabel;
    @FXML
    private Label priceSquareLabel;
    @FXML
    private Label ownerNameSquareLabel;
    @FXML
    private Label taxSquareLabel;


    /**
     * The location corresponding to this square
     */
    private Location location;

    /**
     * Location getter
     *
     * @return The value of "location"
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Location setter
     * Also initializes some UI elements
     *
     * @param location The new value of "location"
     */
    public void setLocation(Location location) {
        this.location = location;
        nameSquareLabel.setText(this.location.getName());
        if (this.location instanceof OwnableLocation) {
            priceSquareLabel.textProperty().bind((((OwnableLocation) this.location).priceProperty().asString().concat("$")));
            taxSquareLabel.textProperty().bind(((OwnableLocation) this.location).taxProperty().asString().concat("$"));
            ownerNameSquareLabel.textProperty().bind(((OwnableLocation) this.location).ownerNameProperty());
        }
    }

    /**
     * Square constructor
     *
     * @throws IOException Thrown by load method
     */
    public Square() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SQUARE_FXML));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }
}
