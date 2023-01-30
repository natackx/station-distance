from fastapi.testclient import TestClient

from main import app

client = TestClient(app)


def test_get_distance():
    # Test successful GET request
    response = client.get("/api/v1/distance/FF/BLS")
    assert response.status_code == 200
    assert response.json() == {
            "from": "Frankfurt(Main)Hbf",
            "to": "Berlin Hbf",
            "distance": 423,
            "unit": "km"
    }


def test_get_distance_error():
    # Test failed GET request by passing wrong DS100 code
    response = client.get("/api/v1/distance/FFF/BLS")
    assert response.status_code == 400


test_get_distance()
test_get_distance_error()
