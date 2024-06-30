package view.customControls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class Dice extends GridPane {
    /**
     * The path to the fxml file
     */
    private final static String DICE_FXML = "/fxml/customControls/Dice.fxml";

    /**
     * FXML components
     */
    @FXML
    private Text diceText;
    @FXML
    private Button launchDiceButton;
    @FXML
    private ImageView diceImageView;

    /**
     * The Dice instance used to randomize the game and display the dice image
     */
    model.Dice dice = new model.Dice();

    /**
     * Dice constructor
     *
     * @throws IOException Thrown by load method
     */
    public Dice() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(DICE_FXML));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    /**
     * Initializes controls
     */
    @FXML
    private void initialize() {
        diceImageView.imageProperty().bind(dice.imgProperty());
    }

    /**
     * Called by the launch dice button handler method
     *
     * @return The randomized dice value
     */
    public int handleLaunchDice() {
        dice.doRandom();
        return dice.getValue();
    }
}
