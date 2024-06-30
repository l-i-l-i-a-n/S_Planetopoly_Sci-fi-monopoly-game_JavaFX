package model;

import javafx.beans.property.*;

import javafx.scene.image.Image;
import model.utils.DiceImages;

public class Dice {
    /**
     * The value of this dice
     */
    private Integer value;

    /**
     * Value getter
     *
     * @return The dice value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * The image corresponding to the dice value
     */
    private ObjectProperty<Image> img = new SimpleObjectProperty<>();

    public ObjectProperty<Image> imgProperty() {
        return img;
    }

    /**
     * Image setter
     *
     * @param img The new image
     */
    public void setImage(Image img) {
        this.img.set(img);
    }

    /**
     * Method that randomizes the value of this dice
     */
    public void doRandom() {
        this.value = (int) Math.ceil(Math.random() * 6);
        this.setImage(DiceImages.images[this.getValue() - 1]);
    }
}
