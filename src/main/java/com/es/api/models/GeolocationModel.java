package com.es.api.models;

/**
 * Represents a geographical location with latitude and longitude coordinates.
 * This record provides an immutable data structure for storing geographic
 * coordinates.
 *
 * @param latitude  the latitude of the location
 * @param longitude the longitude of the location
 */
public record GeolocationModel(
        Double latitude,
        Double longitude) {

    // ~ METHOD(S) ---------------------------------------------------------

    /**
     * Returns a string representation of the geolocation.
     * The format of the returned string is "latitude,longitude".
     *
     * @return A string containing the latitude and longitude separated by a comma.
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.latitude())
                .append(",")
                .append(this.longitude())
                .toString();
    }
}