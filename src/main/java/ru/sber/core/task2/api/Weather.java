package ru.sber.core.task2.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.sber.core.contants.Constants.API_KEY;
import static ru.sber.core.contants.WeatherMethods.CURRENT;
import static ru.sber.core.contants.Urls.BASE_WEATHER_URL;

public class Weather {

    public Response getCurrent(String city) {
        RestAssured.baseURI= BASE_WEATHER_URL;
        RestAssured.basePath= CURRENT;

        Response response = given().
                queryParam("key", API_KEY).
                queryParam("q", city).
                when().
                get().
                then().
                contentType(ContentType.JSON).
                extract().response();
        return response;
    }

    public io.restassured.response.Response getCurrent(String path, String key, String city) {

        RestAssured.baseURI= BASE_WEATHER_URL;
        RestAssured.basePath= path;

        RequestSpecification rs = given();
        if (city != null) {
            rs.param("q", city);
        }
        if (key != null) {
            rs.param("key", city);
        }
        Response response = rs.when().get().then().
                contentType(ContentType.JSON).
                extract().response();
        return response;
    }
}
