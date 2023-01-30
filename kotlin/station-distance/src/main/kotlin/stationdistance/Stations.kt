package stationdistance

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.nio.file.Paths
import java.nio.file.Files


// Path to input csv file
private const val CSVPATH: String = "data/D_Bahnhof_2020_alle.CSV"

/**
 * The Station class manages the collection of stations and provides some methods for it.
 */
class Stations {
    private val stationMap: MutableMap<String, Station> = mutableMapOf()

    init {
        val reader = Files.newBufferedReader(Paths.get(CSVPATH))
        // Parse csv and drop first line (column titles)
        val csvParser = CSVParser(reader, CSVFormat.newFormat(';')).drop(1)
        for (line in csvParser) {
            // Filter on only Fernverkehrshalte (FV)
            if (line[4] == "FV") {
                // Some stations have multiple DS100 codes
                // Therefore they need to be added individually.
                line[1].split(',').forEach {
                    // Create Station instance and add it to stationMap
                    stationMap[it] = Station(ds100 = it,
                        name = line[3],
                        coordinates = Pair(line[5].replace(',', '.').toDouble(),
                            line[6].replace(',', '.').toDouble()))
                }
            }
        }
    }

    /**
     * Returns the station name for a given station DS100 code
     * @param ds100 The DS100-Code of the requested station
     * @return Name of the station
     * @throws StationNotFoundException
     */
    fun getStationName(ds100: String) : String {
        val station : Station? = stationMap[ds100]
        if (station != null) {
            return station.name
        } else {
            throw StationNotFoundException("No station with ds100 code $ds100")
        }
    }

    /**
     * Returns the coordinates for a given station DS100 code
     * @param ds100 The DS100-Code of the requested station
     * @return Coordinate pair (longitude, latitude)
     * @throws StationNotFoundException
     */
    fun getStationCoords(ds100: String) : Pair<Double, Double> {
        val station : Station? = stationMap[ds100]
        if (station != null) {
            return station.coordinates
        } else {
            throw StationNotFoundException("No station with ds100 code $ds100")
        }
    }
}

data class Station (
    val ds100: String,
    val name: String,
    val coordinates: Pair<Double, Double>
)
/** Custom Exception for missing requested stations
* Custom Exception according: https://stackoverflow.com/a/68775013
*/
class StationNotFoundException(message: String? = null,
                               cause: Throwable? = null) : Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}