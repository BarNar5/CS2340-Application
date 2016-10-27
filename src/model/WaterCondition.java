package model;

/**
 * This enum represents a water condition
 *
 */

public enum WaterCondition {
    WASTE("Waste"),
    TREATABLE_CLEAR("Treatable Clean"),
    TREATABLE_MUDDY("Treatable Muddy"),
    POTABLE("Potable");

    private String condition;

    WaterCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return condition;
    }
}
