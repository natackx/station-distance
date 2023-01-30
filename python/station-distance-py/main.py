from fastapi import FastAPI, HTTPException

from geo_services import GeoServices
from stations import Stations, StationNotFoundException

app = FastAPI()

# Initialize Stations class as source of data on stations
stations = Stations()


@app.get("/api/v1/distance/{startstation}/{endstation}")
async def get_distance(startstation: str, endstation: str):
    """
    Returns the distance and names of two station identified by their 'DS100' codes.

        Parameters
        ----------
        startstation : str
            'DS100' of the startstation
        endstation : str
            'DS100' of the endstation

        Returns
        -------
        dict
            Dict with keys: 'from', 'to', 'distance', 'unit'
    """
    unit: str = 'km'
    try:
        # Get names of startstation and endstation
        startname: str = stations.get_station_name(startstation)
        endname: str = stations.get_station_name(endstation)
        # Calculate distance between the two stations
        distance: float = GeoServices.get_distance_of_coords(stations.get_station_coords(startstation),
                                                             stations.get_station_coords(endstation),
                                                             unit=unit)
    except StationNotFoundException:
        # When no station was found for a given 'DS100' code, send a 400 error code.
        raise HTTPException(status_code=400, detail='Station could not be found')

    return {"from": startname, "to": endname, "distance": round(distance), "unit": unit}
