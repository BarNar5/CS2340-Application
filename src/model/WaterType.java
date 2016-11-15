package model;

import java.io.Serializable;

/**
 * This enum represents a water type
 *
 */

public enum WaterType implements Serializable {
    BOTTLED("Bottled"),
    WELL("Well"),
    STREAM("Stream"),
    LAKE("Lake"),
    SPRING("Spring"),
    OTHER("Other");

    private final String type;

    /**
     * Constructor for this enum.
     * @param type the string representing the type
     */
    WaterType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
