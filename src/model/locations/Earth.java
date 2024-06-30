package model.locations;

import model.utils.Windows;

public class Earth extends Location {
    /**
     * The amount of money players receive when they arrive on the Earth square
     */
    private Integer paycheck;

    /**
     * Paycheck getter
     *
     * @return The value of paycheck
     */
    public Integer getPaycheck() {
        return paycheck;
    }

    /**
     * Earth constructor
     * Also sets this.dialogWindow to the right value
     *
     * @param name     The name of this location ("Earth")
     * @param paycheck The given paycheck value
     */
    public Earth(String name, Integer paycheck) {
        super(name);
        this.paycheck = paycheck;
        this.dialogWindow = Windows.EARTH_DIALOG;
    }

}
