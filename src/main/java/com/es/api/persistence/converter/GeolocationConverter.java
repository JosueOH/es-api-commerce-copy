package com.es.api.persistence.converter;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.HibernateException;

import com.es.api.messages.ErrorMessage;
import com.es.api.models.GeolocationModel;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter to transform a {@link GeolocationModel} into a database-compatible String
 * and vice versa.
 */
@Converter(autoApply = true)
public class GeolocationConverter implements AttributeConverter<GeolocationModel, String> {

    /**
     * Converts a {@link GeolocationModel} to a String for database storage.
     * The format of the returned string is "latitude,longitude".
     *
     * @param geolocation the geolocation model to convert
     * @return a comma-separated string representation of latitude and longitude, 
     *         or {@code null} if the geolocation is {@code null}
     */
    @Override
    public String convertToDatabaseColumn(GeolocationModel geolocation) {
        return Objects.toString(geolocation, null);
    }

    /**
     * Converts a database String into a {@link GeolocationModel}.
     *
     * @param geolocationData the stored geolocation string (e.g.,"40.7128,-74.0060")
     * @return a {@link GeolocationModel} object or null if input is invalid
     */
    @Override
    public GeolocationModel convertToEntityAttribute(String geolocationData) {
        if (Strings.isEmpty(geolocationData))
            return null;

        try {
            String[] geolocationElements = geolocationData.split(",");
            Double latitude = Double.parseDouble(geolocationElements[0].trim());
            Double longitude = Double.parseDouble(geolocationElements[1].trim());
            return new GeolocationModel(latitude, longitude);
        } catch (Exception e) {
            throw new HibernateException(ErrorMessage.GEOLOCATION_INVALID_DATA);
        }
    }
}