package stationdistance

import kotlin.math.*

/**
 * Collection of geo services
 */
class GeoServices {

    companion object {
        // approximate radius of earth
        private const val R: Double = 6373.0;

        /**
         * Returns distance between two coordinates.
         * The distance is calculated by the haversine formular
         *
         * @param pointA Coordinates (longitude, latitude) of point a
         * @param pointB Coordinates (longitude, latitude) of point b
         * @param unit Identifier for distance unit: 'km', 'mile'. Defaults to 'km'.
         * @return Distance between the two point in the given unit rounded to Int.
         */
        fun getDistanceOfCoords(pointA: Pair<Double, Double>,
                                pointB: Pair<Double, Double>,
                                unit: String = "km") : Int {

            // Extract coordinate values as radians
            val lonA: Radian = pointA.first.toRadian();
            val latA: Radian = pointA.second.toRadian();
            val lonB: Radian = pointB.first.toRadian();
            val latB: Radian = pointB.second.toRadian();

            // Haversine formular
            val dLon: Double = lonB - lonA;
            val dLat: Double = latB - latA;

            val a: Double = sin(dLat / 2).pow(2) + cos(latA) * cos(latB) * sin(dLon/2).pow(2);
            val d: Double = 2 * atan2(sqrt(a), sqrt(1 - a));

            // distance in km
            val distance: Double = R * d;

            return when (unit) {
                "km" -> round(distance).toInt()
                "mile" -> round((distance/1.609344)).toInt()
                else -> round(distance).toInt()
            }
        }
    }
}

// Radian alias with function to convert degree to radian
typealias Radian = Double
fun Double.toRadian(): Radian = this / 180 * Math.PI