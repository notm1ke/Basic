package co.m1ke.basic.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Comparables {

    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isLong(String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isByte(String input) {
        try {
            Byte.parseByte(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isBoolean(String input) {
        try {
            Boolean.parseBoolean(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUUID(String input) {
        try {
            UUID.fromString(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isJson(String input) {
        try {
            new JSONObject(input);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

}
