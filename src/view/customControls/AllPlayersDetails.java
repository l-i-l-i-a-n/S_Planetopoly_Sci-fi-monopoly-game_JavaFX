package view.customControls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Player;
import model.managers.GameManager;
import view.controllers.Manageable;
import view.utils.ColorTableCellFactory;
import java.io.IOException;

public class AllPlayersDetails extends VBox implements Manageable {
    /**
     * The path to the fxml file
     */
    private final static String ALL_PLAYERS_DETAILS_FXML = "/fxml/customControls/AllPlayersDetails.fxml";

    /**
     * The gameManager instance, used to access game data
     */
    private GameManager gameManager;

    /**
     * GameManager setter
     * @param gameManager The new GameManager instance
     */
    @Override
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        playersTableView.setItems(gameManager.getPlayersList());
    }

    /**
     * FXML components
     */
    @FXML
    private TableView<Player> playersTableView;
    @FXML
    private TableColumn<Player, String> colorTableColumn;
    @FXML
    private TableColumn<Player, String> nameTableColumn;
    @FXML
    private TableColumn<Player, Integer> balanceTableColumn;

    /**
     * AllPlayerDetails constructor
     * @throws IOException Thrown by load method
     */
    public AllPlayersDetails() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ALL_PLAYERS_DETAILS_FXML));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    /**
     * Initializes the components
     */
    @FXML
    private void initialize() {
        colorTableColumn.setCellValueFactory(celldata -> celldata.getValue().colorProperty().asString());
        colorTableColumn.setCellFactory(new ColorTableCellFactory());
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        balanceTableColumn.setCellValueFactory(cellData -> cellData.getValue().moneyBalanceProperty().asObject());
    }
}
