package view.utils;

import javafx.scene.control.Alert;

/**
 * Provides a simple method to display an alert with a message
 */
public abstract class AlertBox {
    /**
     * Shows an alert with the given type and message
     *
     * @param type    The alert type (information, error, success, ...)
     * @param message The message to display in the alert
     */
    public static void show(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.getDialogPane().getStylesheets().add(AlertBox.class.getResource("/style/generalStyle.css").toExternalForm());
        alert.setTitle("Planetopoly");
        alert.setHeaderText(type.toString());
        alert.setContentText(message);
        alert.showAndWait();
    }
}
