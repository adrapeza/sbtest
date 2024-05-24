package ru.sber.core.contants;

public class Environment {

    private String apiKey;

    private static Environment _instance = null;

    public static Environment get() {
        if (_instance == null) {
            _instance = new Environment();
        }

        return _instance;
    }

    private Environment() {
        apiKey = "397d69371dd74bbebe363432242305";
    }

    public String getApiKey() {
        return apiKey;
    }



}
