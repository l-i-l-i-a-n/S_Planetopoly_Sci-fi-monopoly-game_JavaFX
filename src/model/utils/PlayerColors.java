package model.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public abstract class PlayerColors {
    /**
     * Returns all the possible values for players color
     *
     * @return The collection of colors
     */
    public static ObservableList<String> getColorsList() {
        return FXCollections.observableArrayList(
                "RED",
                "GREEN",
                "BLUE",
                "ORANGE"
        );
    }

    /**
     * Returns the hexadecimal/string value pair for all colors
     *
     * @return The pairs
     */
    public static Map<String, String> colorHexNameConvert() {
        Map<String, String> colors = new HashMap<>() {{
            put("0xff0000ff", "red");
            put("0x008000ff", "green");
            put("0x0000ffff", "blue");
            put("0xffa500ff", "orange");
        }};
        return colors;
    }
}
