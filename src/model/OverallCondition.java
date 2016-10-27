package model;

/**
 * This enum represents an overall water condition
 *
 */

public enum OverallCondition {
    SAFE("Safe"),
    TREATABLE("Treatable"),
    UNSAFE("Unsafe");

    private String condition;

    OverallCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return condition;
    }
}
