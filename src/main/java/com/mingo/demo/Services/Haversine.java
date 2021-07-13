package com.mingo.demo.Services;

import java.util.List;

// Java program for the haversine formula
public class Haversine {

    public static double calculateDistanceBetweenTwoLatLngsReturnInMiles(
                        double lat1, double lon1,
                        double lat2, double lon2) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 3958.8;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
    public static Double  givenLatLongAndRadiusReturnNorthernBound(double lat1,
                                                                               double long1,
                                                                               double radius) {
        return lat1+radius;
    }

    public static Double  givenLatLongAndRadiusReturnSouthernBound(double lat1,
                                                                double long1,
                                                                double radius) {
        return lat1-radius;
    }

    public static Double  givenLatLongAndRadiusReturnWesternBound(double lat1,
                                                                double long1,
                                                                double radius) {
        return long1+radius;
    }

    public static Double  givenLatLongAndRadiusReturnEasternBound(double lat1,
                                                                  double long1,
                                                                  double radius) {
        return long1-radius;
    }

}
