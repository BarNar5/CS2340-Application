package model;

/**
 * A class to hold locations we want to display on the map
 */
public class Location {


    private final int id;
    private final double latitude;
    private final double longitude;
    private final String title;
    private final String description;


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


    public Location(int id, double lat, double lg, String ti, String desc) {
        this.id = id;
        latitude = lat;
        longitude = lg;
        title = ti;
        description = desc;
    }

    public int getId() {
        return id;
    }


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