package model;

import java.io.Serializable;

/**
 * This enum represents a water condition
 *
 */

public enum WaterCondition implements Serializable {
    WASTE("Waste"),
    TREATABLE_CLEAR("Treatable Clean"),
    TREATABLE_MUDDY("Treatable Muddy"),
    POTABLE("Potable");

    private final String condition;

    /**
     * Constructor for this enum.
     * @param condition the string representing the condition
     */
    WaterCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return condition;
    }
}
