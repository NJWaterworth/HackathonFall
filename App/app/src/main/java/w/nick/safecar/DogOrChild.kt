package com.example.jasperharrison.locationtest

import w.nick.safecar.NetClientGetOldBoy
import java.lang.Math

class DogOrChild {
    var geoXPos: Double = 0.0
    var geoYPos: Double = 0.0
    var timeStart: Double = 0.0
    var timeElapsed: Double = 0.0
    var name: String = "name"
    var age: Double = 0.0
    var mobility: Double = 0.0
    var threshold: Double = 150.0 //150 ft away
    var angry: Boolean = false

    fun calcMobility(Age: Double) {
        var effectiveAge: Double = 0.0
        if (Age > 6)
            effectiveAge = 6.0
        mobility = effectiveAge / 6
    }

    //Checks a variety of bad conditions for DogOrChild
    fun badness(momXGeoPos: Double, momYGeoPos: Double) {
        if (eDist(momXGeoPos, momYGeoPos, geoXPos, geoYPos) > threshold || hotness(NetClientGetOldBoy.getTemp(momXGeoPos, momYGeoPos)))//NetClientGet is Separate Code
            angry = true
    }

    // Update geo-coordinates
    fun updateCords(xPos: Double, yPos: Double) {
        geoXPos = xPos
        geoYPos = yPos
    }

    //Euclidean distance
    fun eDist(x1: Double, y1: Double, x2: Double, y2: Double) :Double {
        return degToFeet(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)))
    }

    //Converts degree to feet
    fun degToFeet(x: Double): Double {
        var ft: Double = 0.0
        ft = x * 365214.6
        return ft
    }

    //Updates entity
    fun update(time: Double, XGeoPos: Double, YGeoPos: Double) {
        timeElapsed = time - timeStart;
        badness(XGeoPos, YGeoPos)
    }

    //Checks if too hot
    fun hotness(temp: Double): Boolean {
        return if (temp > 100)
            true
        else if (temp >= 93 && timeElapsed > 5)
            true
        else if (temp >= 85 && timeElapsed > 10)
            true
        else if (timeElapsed > 20)
            true
        else
            false
    }
}