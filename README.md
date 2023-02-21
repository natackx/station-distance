# Station Distance

Implementation of the web service for distance measurements between long-distance stations. Two variants of the service were created, one in Python and one in Kotlin.

## Endpoints

The API has an endpoint that is passed two DS100 codes and responds with a json body containing the names of the stations and the distance.

Route:

```
GET /api/v1/distance/{CODE1}/{CODE2}
```

The Response has the following scheme:

```json
{
"from": "Frankfurt(Main)Hbf",
"to": "Berlin Hbf",
"distance": 423,
"unit": "km"
}
```


## Data

The csv file of all German train stations [provided](https://data.deutschebahn.com/dataset/data-haltestellen.html) by Deutsche Bahn is used as the data set.



## Python

The python variant was created with FastAPI and uses python version 3.10.

 Run the dev server:

```bash
 uvicorn main:app --reload
```

The API is then available at:

```
http://localhost:8000
```

Or open swagger with the URL in your browser:

```bash
firefox http://localhost:8000/docs
```



## Kotlin

The kotlin variant was created with Spring Boot Web.

Run the dev server:

```bash
 ./gradlew bootRun
```

The API is then available at:

```
http://localhost:8080
```
