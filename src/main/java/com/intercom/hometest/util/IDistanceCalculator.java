package com.intercom.hometest.util;

import com.intercom.hometest.model.GPSCoordinate;

public interface IDistanceCalculator {
    double distanceInKilometer(GPSCoordinate a, GPSCoordinate b);
}
