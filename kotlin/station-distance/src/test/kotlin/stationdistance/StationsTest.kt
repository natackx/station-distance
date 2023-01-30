package stationdistance

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StationsTest {
    private val stations = Stations()

    // Test fetching names of stations from Stations class
    @Test
    fun testGetStationName() {
        assertEquals("Hamburg Hbf", stations.getStationName("AH"));
        assertEquals("Hamburg Dammtor", stations.getStationName("ADF"));
        assertEquals("Hamburg Dammtor", stations.getStationName("ADST"));
        assertEquals("Frankfurt(Main)Hbf", stations.getStationName("FF"));
        assertEquals("Berlin Hbf", stations.getStationName("BLS"));
    }

    // Test fetching names of stations from Stations class
    @Test
    fun testGetStationCoords() {
        assertEquals(Pair(10.006909, 53.552736), stations.getStationCoords("AH"));
        assertEquals(Pair(9.989566, 53.560751), stations.getStationCoords("ADF"));
        assertEquals(Pair(9.989566, 53.560751), stations.getStationCoords("ADST"));
    }
}