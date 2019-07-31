package com.williamssonama;

/**
 * ZipCodeValidationUtils is a utility class to perform utility checks on the input provided to
 * ZipCodeRangeMergeApplication
 */

public class ZipCodeValidationUtils {

    public static boolean isValidZipCode(String zipCodeStr) {
        boolean isValidPositiveInteger = isValidPositiveInteger(zipCodeStr);
        boolean isNot5Digit = zipCodeStr.length() != 5 ? true : false;

        if (isNot5Digit) {
            System.out.println("The ZipCode bounds have to be 5 digit");
            return false;
        }
        return isValidPositiveInteger;
    }

    public static boolean isValidPositiveInteger(String numStr) {
        int intVal;
        try {
            intVal = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            return false;
        }

        return intVal > 0 ? true : false;
    }
}