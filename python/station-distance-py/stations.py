import csv


class Stations:
    """
    The Station class manages the collection of stations and provides some methods for it.
    """
    # List of used keys/columns of the data
    _USED_KEYS: list[str] = ['DS100', 'NAME', 'Laenge', 'Breite']

    # Path to input csv file
    _CSV_PATH: str = 'data/D_Bahnhof_2020_alle.CSV'

    _station_collection: dict = {}

    def __init__(self):
        station_list: list = []
        # Read input csv file
        with open(self._CSV_PATH, 'r') as infile:
            for line in csv.DictReader(infile, delimiter=';'):
                # Filter on only Fernverkehrshalte (FV)
                if line['Verkehr'] == 'FV':
                    # Convert fields Laenge and Breite to floats
                    line['Laenge'] = float(line['Laenge'].replace(',', '.'))
                    line['Breite'] = float(line['Breite'].replace(',', '.'))
                    # Some stations have multiple DS100 codes
                    # Therefore they need to be added individually.
                    ds100codes = line['DS100'].split(',')
                    for code in ds100codes:
                        x: dict = line.copy()
                        x['DS100'] = code
                        # Filter dict to contain only used key/value pairs
                        reduced_dict: dict = {key: x[key] for key in self._USED_KEYS}
                        station_list.append(reduced_dict)
        # Create an indexed dictionary
        self._station_collection = dict((station['DS100'], station) for station in station_list)
        print(self._station_collection)

    def get_station_name(self, acronym: str) -> str:
        """Returns the station name for a given station acronym

        Parameters
        ----------
        acronym : str
            The acronym (DS100-Code) of the requested station

        Returns
        -------
        str
            Name of the station

        Raises
        ------
        StationNotFoundException
            If no station for the given acronym (DS100 code) could be found
        """
        if acronym in self._station_collection:
            return self._station_collection[acronym]['NAME']
        else:
            raise StationNotFoundException

    def get_station_coords(self, acronym: str) -> tuple[float, float]:
        """Return the coordinates for a given station acronym

        Parameters
        ----------
        acronym : str
            The acronym (DS100-Code) of the requested station

        Returns
        -------
        tuple[float, float]
            Coordinate tuple (longitude, latitude)

        Raises
        ------
        StationNotFoundException
            If no station for the given acronym (DS100 code) could be found
        """
        if acronym in self._station_collection:
            return self._station_collection[acronym]['Laenge'],\
                self._station_collection[acronym]['Breite']
        else:
            raise StationNotFoundException


class StationNotFoundException(Exception):
    # Custom Exception for missing requested stations
    """Requested station couldn't be found"""
    pass
