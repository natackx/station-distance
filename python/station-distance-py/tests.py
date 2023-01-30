from geo_services import GeoServices
from stations import Stations


def test_get_distance_of_coords():
    # Test method GeoServices.get_distance_of_coords
    assert GeoServices.get_distance_of_coords((10.0, 53.0), (10.0, 53.0)) == 0
    assert round(GeoServices.get_distance_of_coords((8.663789, 50.107145),
                                                    (13.369545, 52.525592))) == 423
    assert GeoServices.get_distance_of_coords((8.663789, 50.107145),
                                              (13.369545, 52.525592))\
           == GeoServices.get_distance_of_coords((13.369545, 52.525592),
                                                 (8.663789, 50.107145))


def test_get_station_names():
    # Test fetching names of stations from Stations class
    stations = Stations()
    assert stations.get_station_name('AH') == 'Hamburg Hbf'
    assert stations.get_station_name('ADF') == 'Hamburg Dammtor'
    assert stations.get_station_name('ADST') == 'Hamburg Dammtor'


def test_get_station_coords():
    # Test fetching names of stations from Stations class
    stations = Stations()
    assert stations.get_station_coords('AH') == (10.006909, 53.552736)
    assert stations.get_station_coords('ADF') == (9.989566, 53.560751)
    assert stations.get_station_coords('ADST') == (9.989566, 53.560751)


# Run tests
test_get_distance_of_coords()
test_get_station_names()
test_get_station_coords()
