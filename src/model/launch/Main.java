package model.launch;

import javafx.application.Application;
import javafx.stage.Stage;
import model.managers.GameManager;
import model.persistance.FilePersistence;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameManager manager = new GameManager(primaryStage, new FilePersistence());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
