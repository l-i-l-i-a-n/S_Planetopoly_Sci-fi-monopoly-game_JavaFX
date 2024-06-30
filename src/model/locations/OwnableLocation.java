package model.locations;

import javafx.beans.property.*;
import model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class OwnableLocation extends Location {

    /**
     * The price of this location
     */
    private IntegerProperty price = new SimpleIntegerProperty();

    public IntegerProperty priceProperty() {
        return price;
    }

    /**
     * Price getter
     *
     * @return The price of this location
     */
    public Integer getPrice() {
        return price.get();
    }

    /**
     * Price setter
     *
     * @param price The new price of this location
     */
    public void setPrice(Integer price) {
        this.price.set(price);
    }

    /**
     * The tax of this location
     * The money other players must give to the owner of this location if they arrive on this location's square and they are not the owner
     */
    private IntegerProperty tax = new SimpleIntegerProperty();

    public IntegerProperty taxProperty() {
        return tax;
    }

    /**
     * Tax getter
     *
     * @return The tax for this location
     */
    public Integer getTax() {
        return tax.get();
    }

    /**
     * Tax setter
     *
     * @param tax The new tax for this location
     */
    public void setTax(Integer tax) {
        this.tax.set(tax);
    }

    /**
     * The color of this location
     */
    private String color;

    /**
     * Color getter
     *
     * @return The color of this location
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Color setter
     *
     * @param color The new color of this location
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * True if this location is owned by a Player, false otherwise
     */
    private boolean isOwned = false;

    /**
     * IsOwned getter
     *
     * @return True if this location is owned, false otherwise
     */
    public boolean isOwned() {
        return isOwned;
    }

    /**
     * IsOwned setter
     *
     * @param owned The new value of isOwned
     */
    public void setOwned(boolean owned) {
        isOwned = owned;
    }


    /**
     * The Player instance corresponding to the owner of this location
     */
    private Player owner;

    /**
     * Owner getter
     *
     * @return The owner of this location
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Owner setter
     *
     * @param owner The new owner of this location
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * The name of the player who owns this location
     * Used to display the name in the UI
     */
    private StringProperty ownerName = new SimpleStringProperty();

    public StringProperty ownerNameProperty() {
        return ownerName;
    }

    /**
     * OwnerName getter
     *
     * @return The name of the player who owns this location
     */
    public String getOwnerName() {
        return ownerName.get();
    }

    /**
     * OwnerName setter
     *
     * @param ownerName The new ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName.set(ownerName);
    }

    /**
     * OwnableLocation constructor
     *
     * @param name  The name of the location
     * @param price The price of the location
     * @param tax   The tax of the location
     * @param color The color of the location
     */
    public OwnableLocation(String name, Integer price, Integer tax, String color) {
        super(name);
        this.setPrice(price);
        this.setTax(tax);
        this.setColor(color);
    }

    /**
     * Defines how OwnableLocation objects will be de-serialized
     * Used for persistence
     *
     * @param objectInputStream The stream where the data comes from
     * @throws IOException            Thrown by the objectOutputStream
     * @throws ClassNotFoundException Thrown by the objectOutputStream
     */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.price = new SimpleIntegerProperty();
        this.tax = new SimpleIntegerProperty();
        this.ownerName = new SimpleStringProperty();
        setPrice((Integer) objectInputStream.readObject());
        setTax((Integer) objectInputStream.readObject());
        setColor((String) objectInputStream.readObject());
        setOwned((Boolean) objectInputStream.readObject());
        setOwner((Player) objectInputStream.readObject());
        setOwnerName((String) objectInputStream.readObject());
    }

    /**
     * Defines how OwnableLocation objects will be serialized
     * Used for persistence
     *
     * @param objectOutputStream The stream where to write the data
     * @throws IOException Thrown by the objectOutputStream
     */
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(getPrice());
        objectOutputStream.writeObject(getTax());
        objectOutputStream.writeObject(color);
        objectOutputStream.writeObject(isOwned);
        objectOutputStream.writeObject(owner);
        objectOutputStream.writeObject(getOwnerName());
    }

}
