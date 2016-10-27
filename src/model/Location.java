package model;

/**
 * A class to hold locations we want to display on the map
 */
public class Location {


    /**
     * Properties of a location.
     *
     */
    private final int id;
    private final double latitude;
    private final double longitude;
    private final String title;
    private final String description;


    /**
     * Getters for all the properties.
     *
     */
    public int getId() {
        return id;
    }
    public double getLongitude() {
        return longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }


    /**
     * Create a new location.
     *
     * @param id the id of this location (corresponding to water report)
     * @param lat latitude of this location
     * @param lg longitude of this location
     * @param ti title of this location
     * @param desc description of this location
     */
    public Location(int id, double lat, double lg, String ti, String desc) {
        this.id = id;
        latitude = lat;
        longitude = lg;
        title = ti;
        description = desc;
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (that == this) {
            return true;
        }
        if (!(that instanceof Location)) {
            return false;
        }
        Location location = (Location) that;
        return this.id == location.id;
    }
}