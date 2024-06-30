package model.utils;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerPositions {
    /**
     * The size of each side of the board
     */
    public static final int BOARD_SIZE = 5;

    /**
     * Returns all the column/row positions of the board
     *
     * @return The positions
     */
    public static List<Point2D> getPositions() {
        List<Point2D> positions = new ArrayList<>();
        int i;
        //Haut
        for (i = 0; i < BOARD_SIZE; i++) {
            positions.add(new Point2D(i, 0));
        }
        //Droite
        for (i = 0; i < BOARD_SIZE; i++) {
            positions.add(new Point2D(BOARD_SIZE, i));
        }
        //Bas
        for (i = BOARD_SIZE; i > 0; i--) {
            positions.add(new Point2D(i, BOARD_SIZE));
        }
        //Gauche
        for (i = BOARD_SIZE; i > 0; i--) {
            positions.add(new Point2D(0, i));
        }
        return positions;
    }

    /**
     * Returns the board column/row pair corresponding to the given int position
     *
     * @param position The position, as a single int value
     * @return The corresponding column and row
     */
    public static Point2D getColRowFromPosition(int position) {
        return getPositions().get(position);
    }
}
