package ru.sber.core.utils;

import org.mkfl3x.jsondelta.JsonDelta;
import org.mkfl3x.jsondelta.JsonDeltaReport;

public class JsonComparator {

    public static JsonDeltaReport compare(String expected, String actual) {
        return new JsonDelta().compare(expected, actual);
    }

}
