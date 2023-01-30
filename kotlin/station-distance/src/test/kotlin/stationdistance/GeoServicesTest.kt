package stationdistance

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GeoServicesTest {
    private val pointA: Pair<Double, Double> = Pair(8.663789, 50.107145)
    private val pointB: Pair<Double, Double> = Pair(13.369545, 52.525592)

    // Test method GeoServices.get_distance_of_coords
    @Test
    fun testGetDistanceOfCoords() {
        assertEquals(0, GeoServices.getDistanceOfCoords(pointA, pointA));
        assertEquals(423, GeoServices.getDistanceOfCoords(pointA, pointB));
        assertEquals(GeoServices.getDistanceOfCoords(pointA, pointB), GeoServices.getDistanceOfCoords(pointB, pointA));
    }
}