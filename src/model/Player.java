package model;

import javafx.beans.property.*;
import javafx.scene.paint.Paint;
import model.locations.OwnableLocation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    /**
     * The color of this player
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
        return color.get();
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
     * The name of this player
     */
    private StringProperty name = new SimpleStringProperty();

    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Name getter
     *
     * @return The value of "name"
     */
    public String getName() {
        return name.get();
    }

    /**
     * Name setter
     *
     * @param name The new value of "name"
     */
    public void setName(String name) {
        this.name.set(name);
    }


    /**
     * The money this players has
     */
    private IntegerProperty moneyBalance = new SimpleIntegerProperty();

    public IntegerProperty moneyBalanceProperty() {
        return moneyBalance;
    }

    /**
     * MoneyBalance getter
     *
     * @return The current vale of moneyBalance
     */
    public Integer getMoneyBalance() {
        return moneyBalance.get();
    }

    /**
     * MoneyBalance setter
     *
     * @param moneyBalance The new value of moneyBalance
     */
    public void setMoneyBalance(Integer moneyBalance) {
        this.moneyBalance.set(moneyBalance);
    }


    /**
     * The current position of this player
     */
    private int position;

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
     * @param position The new position of this player
     */
    public void setPosition(int position) {
        this.position = position;
    }


    /**
     * True if this player is in jail at the moment, false otherwise
     */
    private boolean inJail = false;

    /**
     * InJail getter
     *
     * @return the value of inJail
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * InJail setter
     *
     * @param inJail The new value of inJail
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    /**
     * The list of locations this player owns at the moment
     */
    private List<OwnableLocation> ownedLocations = new ArrayList<>();

    /**
     * OwnedLocations getter
     *
     * @return The list of locations owned by this player
     */
    public List<OwnableLocation> getOwnedLocations() {
        return ownedLocations;
    }

    /**
     * OwnedLocations setter
     *
     * @param ownedLocations The new list of locations owned by this player
     */
    public void setOwnedLocations(List<OwnableLocation> ownedLocations) {
        this.ownedLocations = ownedLocations;
    }

    /**
     * Player constructor
     *
     * @param name  The name of this player
     * @param color The color of this player
     */
    public Player(String name, Paint color) {
        this.setColor(color);
        this.setName(name);
        this.setMoneyBalance(200000);
        this.setPosition(0);
    }

    /**
     * Defines how Player objects will be de-serialized
     * Used for persistence
     *
     * @param objectInputStream The stream where the data comes from
     * @throws IOException            Thrown by the objectOutputStream
     * @throws ClassNotFoundException Thrown by the objectOutputStream
     */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.color = new SimpleObjectProperty<>();
        this.name = new SimpleStringProperty();
        this.moneyBalance = new SimpleIntegerProperty();
        setColor(Paint.valueOf((String) objectInputStream.readObject()));
        setName((String) objectInputStream.readObject());
        setMoneyBalance((Integer) objectInputStream.readObject());
        setPosition((Integer) objectInputStream.readObject());
        setInJail((Boolean) objectInputStream.readObject());
        setOwnedLocations((List<OwnableLocation>) objectInputStream.readObject());
    }

    /**
     * Defines how Player objects will be serialized
     * Used for persistence
     *
     * @param objectOutputStream The stream where to write the data
     * @throws IOException Thrown by the objectOutputStream
     */
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(getColor().toString());
        objectOutputStream.writeObject(getName());
        objectOutputStream.writeObject(getMoneyBalance());
        objectOutputStream.writeObject(position);
        objectOutputStream.writeObject(inJail);
        objectOutputStream.writeObject(ownedLocations);
    }

}
