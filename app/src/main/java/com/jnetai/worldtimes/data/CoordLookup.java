package com.jnetai.worldtimes.data;

import java.util.Map;

/**
 * Coordinate-based timezone lookup using the web app's coordinate dataset.
 * Finds nearest city by great-circle distance.
 */
public class CoordLookup {

    /**
     * Find the nearest city timezone for given lat/lon coordinates.
     * Uses Haversine formula to compute great-circle distance.
     */
    public static CityData.City findNearestCity(double lat, double lon) {
        CityData.City nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Map.Entry<String, CityData.City> entry : CityData.CITIES.entrySet()) {
            CityData.City city = entry.getValue();
            double dist = haversineDistance(lat, lon, city.latitude, city.longitude);
            if (dist < minDistance) {
                minDistance = dist;
                nearest = city;
            }
        }
        return nearest;
    }

    /**
     * Haversine distance between two lat/lon points in km.
     */
    private static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371.0; // Earth radius in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
