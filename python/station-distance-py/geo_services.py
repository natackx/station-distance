from math import sin, cos, sqrt, atan2, radians


class GeoServices:
    """
    Collection of geo services
    """

    @staticmethod
    def get_distance_of_coords(point_a: tuple[float, float], point_b: tuple[float, float], unit: str = 'km') -> float:
        """
        Returns distance between two coordinates.
        The distance is calculated by the haversine formular

        Parameters
        ----------
        point_a : tuple[float, float]
            Coordinates (longitude, latitude) of point a
        point_b : tuple[float, float]
            Coordinates (longitude, latitude) of point b
        unit : str: optional
            Identifier for distance unit: 'km', 'mile'.
            Defaults to 'km'

        Returns
        -------
        float
            Distance between the two point in the given unit
        """

        # approximate radius of earth
        _R: float = 6373.0

        # Extract coordinate values as radians
        lon_a: radians = radians(point_a[0])
        lat_a: radians = radians(point_a[1])
        lon_b: radians = radians(point_b[0])
        lat_b: radians = radians(point_b[1])

        # Haversine formular
        d_lon: float = lon_b - lon_a
        d_lat: float = lat_b - lat_a

        a: float = sin(d_lat / 2)**2 + cos(lat_a) * cos(lat_b) * sin(d_lon / 2)**2
        d: float = 2 * atan2(sqrt(a), sqrt(1 - a))

        # distance in km
        distance: float = _R * d

        match unit:
            case 'km':
                return distance
            case 'mile':
                return distance/1.609344
            case default:
                return distance
