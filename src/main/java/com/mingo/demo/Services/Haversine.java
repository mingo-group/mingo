package com.mingo.demo.Services;

import java.util.List;

// Java program for the haversine formula - this is the formula that calculates the
// distance in miles between two latitude and longitudes.

// big freaking hint - if you want to change the units that it returns in,  change the units
// of the earth's radius where it appears.
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

//    Each of these basically takes a distance and calculates the Northern, Southern,
//    Eastern or Western bound for the search box.
    public static Double  givenLatLongAndRadiusInMilesReturnNorthernBound(double lat1,
                                                                               double long1,
                                                                               double radius) {
        double earthRadius = 3958.8;


        return lat1 + (radius/earthRadius * (180.0 / Math.PI));
    }

    public static Double  givenLatLongAndRadiusInMilesReturnSouthernBound(double lat1,
                                                                double long1,
                                                                double radius) {
        double earthRadius = 3958.8;
        return lat1 - (radius/earthRadius * (180 / Math.PI));
    }

    public static Double  givenLatLongAndRadiusInMilesReturnWesternBound(double lat1,
                                                                double long1,
                                                                double radius) {
        double earthRadius = 3958.8;
        double earthRadiusAtGivenLatitude = earthRadius * Math.cos(Math.toRadians(lat1));
        return long1 + (radius/earthRadiusAtGivenLatitude * (180 / Math.PI));
    }

    public static Double  givenLatLongAndRadiusInMilesReturnEasternBound(double lat1,
                                                                  double long1,
                                                                  double radius) {
        double earthRadius = 3958.8;
        double earthRadiusAtGivenLatitude = earthRadius * Math.cos(Math.toRadians(lat1));
        return long1 - (radius/earthRadiusAtGivenLatitude * (180 / Math.PI));
    }

}
