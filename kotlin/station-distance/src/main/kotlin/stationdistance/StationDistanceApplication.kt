package stationdistance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@SpringBootApplication
class StationDistanceApplication

fun main(args: Array<String>) {
	runApplication<StationDistanceApplication>(*args)
}

private val stations = Stations()

@RestController
class GetDistance {
	@GetMapping("/api/v1/distance/{startstation}/{endstation}")
	fun index(@PathVariable startstation: String, @PathVariable endstation: String): Response {
		val unit = "km"
		val startName : String
		val endName : String
		val distance : Int

		try {
			// Get names of startstation and endstation
			startName = stations.getStationName(startstation)
			endName = stations.getStationName(endstation)
			// Calculate distance between the two stations
			distance = GeoServices.getDistanceOfCoords(stations.getStationCoords(startstation),
													   stations.getStationCoords(endstation))
		} catch (e: StationNotFoundException) {
			// When no station could be found for a given 'DS100' code, send a 400 error code.
			throw ResponseStatusException(HttpStatus.BAD_REQUEST,
				"DS100 codes couldn\'t be resolved!", e)
		}

		return Response(startName, endName, distance, unit);
	}
}

/**
 * Data class of Json Response
 * @param from name of the first station given
 * @param to name of the second station given
 * @param distance between the given stations
 * @param unit unit of distance measure (km, mile)
 */
data class Response(val from: String, val to: String, val distance: Int, val unit: String)