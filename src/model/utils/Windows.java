package model.utils;

/**
 * Enum used as a shorthand for fxml files
 */
public enum Windows {
    HOME_MENU,
    INIT_PLAYERS,
    MAIN_GAME,
    OWNABLE_LOCATION_DIALOG,
    PAY_TAX_DIALOG,
    OWNED_PLANET_DIALOG,
    OWNED_STATION_DIALOG,
    EARTH_DIALOG,
    JAIL_DIALOG,
    ASTEROID_DIALOG,
    BLACK_HOLE_DIALOG;

    /**
     * Returns the path corresponding to the enum value
     *
     * @return The string path
     */
    @Override
    public String toString() {
        switch (this) {
            case HOME_MENU:
                return "/fxml/windows/HomeMenuWindow.fxml";
            case INIT_PLAYERS:
                return "/fxml/windows/InitPlayersWindow.fxml";
            case MAIN_GAME:
                return "/fxml/windows/MainGameWindow.fxml";
            case OWNABLE_LOCATION_DIALOG:
                return "/fxml/windows/OwnableLocationDialogWindow.fxml";
            case PAY_TAX_DIALOG:
                return "/fxml/windows/PayTaxDialogWindow.fxml";
            case OWNED_PLANET_DIALOG:
                return "/fxml/windows/OwnedPlanetDialogWindow.fxml";
            case OWNED_STATION_DIALOG:
                return "/fxml/windows/OwnedStationDialogWindow.fxml";
            case EARTH_DIALOG:
                return "/fxml/windows/EarthDialogWindow.fxml";
            case JAIL_DIALOG:
                return "/fxml/windows/JailDialogWindow.fxml";
            case ASTEROID_DIALOG:
                return "/fxml/windows/AsteroidDialogWindow.fxml";
            case BLACK_HOLE_DIALOG:
                return "/fxml/windows/BlackHoleDialogWindow.fxml";
            default:
                throw new IllegalArgumentException();
        }
    }
}
