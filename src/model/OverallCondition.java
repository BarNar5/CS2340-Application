package model;

import java.io.Serializable;

/**
 * This enum represents an overall water condition
 *
 */

public enum OverallCondition implements Serializable {
    SAFE("Safe"),
    TREATABLE("Treatable"),
    UNSAFE("Unsafe");

    private final String condition;

    /**
     * Constructor for this enum.
     * @param condition the string representing the condition
     */
    OverallCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return condition;
    }
}
