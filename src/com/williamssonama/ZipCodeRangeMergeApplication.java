package com.williamssonama;

/**
 * BACKGROUND
 * Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. For example if the ranges are:
 * <p>
 * [94133,94133] [94200,94299] [94600,94699]
 * <p>
 * Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.
 * <p>
 * Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.
 * <p>
 * PROBLEM
 * Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.
 * <p>
 * NOTES
 * - The ranges above are just examples, your implementation should work for any set of arbitrary ranges
 * - Ranges may be provided in arbitrary order
 * - Ranges may or may not overlap
 * - Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices
 * <p>
 * EXAMPLES:
 * If the input = [94133,94133] [94200,94299] [94600,94699]
 * Then the output should be = [94133,94133] [94200,94299] [94600,94699]
 * <p>
 * If the input = [94133,94133] [94200,94299] [94226,94399]
 * Then the output should be = [94133,94133] [94200,94399]
 * <p>
 * ZipCodeRangeMergeApplication is an application which solves the above given problem
 */

import java.util.*;

public class ZipCodeRangeMergeApplication {

    public static void main(String[] args) {


        List<ZipCodeRange> zipCodes = getZipCodeRanges();

        if (zipCodes.size() == 0) {
            System.out.println("No valid ZipCodeRange Input, exiting program!!");
            return;
        }

        if (zipCodes.size() == 1) {
            System.out.println("The merged ZipCodeRange is : " + zipCodes);
            return;
        }

        List<ZipCodeRange> zipCodesMerged = mergeZipCodeRanges(zipCodes);

        System.out.println("The merged ZipCodeRange is : " + zipCodesMerged);
    }

    /**
     *
     * @param zipCodes
     * @return
     */
    private static List<ZipCodeRange> mergeZipCodeRanges(List<ZipCodeRange> zipCodes) {
        List<ZipCodeRange> zipCodesMerged = new ArrayList<>();

        sortZipCodeRange(zipCodes);

        zipCodesMerged.add(zipCodes.get(0));

        for (int i = 1; i < zipCodes.size(); i++) {
            int sizeMergedRangeList = zipCodesMerged.size() - 1;
            ZipCodeRange lastMergedRange = zipCodesMerged.get(sizeMergedRangeList);
            ZipCodeRange currentRange = zipCodes.get(i);
            int lastMergedRangeLower = lastMergedRange.getLower();
            int lastMergedRangeUpper = lastMergedRange.getUpper();
            int currentRangeLower = currentRange.getLower();
            int currentRangeSUpper = currentRange.getUpper();


            if (lastMergedRangeUpper >= currentRangeLower) {

                ZipCodeRange newZipCodeRange = new ZipCodeRange(lastMergedRangeLower,
                        lastMergedRangeUpper > currentRangeSUpper ? lastMergedRangeUpper : currentRangeSUpper);
                zipCodesMerged.remove(sizeMergedRangeList);
                zipCodesMerged.add(newZipCodeRange);

            } else {
                zipCodesMerged.add(currentRange);
            }


        }
        return zipCodesMerged;
    }

    /**
     *
     * @param zipCodes
     */
    private static void sortZipCodeRange(List<ZipCodeRange> zipCodes) {
        Collections.sort(zipCodes, new Comparator<ZipCodeRange>() {
            @Override
            public int compare(ZipCodeRange o1, ZipCodeRange o2) {
                return o1.getLower() - o2.getLower();
            }
        });
    }

    /**
     *
     * @return
     */
    private static List<ZipCodeRange> getZipCodeRanges() {
        Scanner myObj = new Scanner(System.in);
        int zipCodeRangeArrayLengthInt;
        while (true) {

            System.out.println("Enter Length of Zip Code Range Array");
            String zipCodeRangeArrayLengthStr = myObj.nextLine();  // Read user input

            if (ZipCodeValidationUtils.isValidPositiveInteger(zipCodeRangeArrayLengthStr)) {
                zipCodeRangeArrayLengthInt = Integer.parseInt(zipCodeRangeArrayLengthStr);
                break;
            }
            System.out.println("Incorrect value of Length of Zip Code Range Array");
        }

        List<ZipCodeRange> zipCodes = new ArrayList<>();

        for (int i = 1; i <= zipCodeRangeArrayLengthInt; ) {
            System.out.println("Enter Lower bound of ZipCodeRange : " + i);
            String zipCodeRangeLowerStr = myObj.nextLine();
            int zipCodeRangeLowerInt = 0;
            if (ZipCodeValidationUtils.isValidZipCode(zipCodeRangeLowerStr)) {
                zipCodeRangeLowerInt = Integer.parseInt(zipCodeRangeLowerStr);
            } else {
                System.out.println("Invalid value for lower bound of ZipCodeRange : " + i);
                continue;
            }

            System.out.println("Enter upper bound of ZipCodeRange : " + i);
            String zipCodeRangeUpperStr = myObj.nextLine();
            int zipCodeRangeUpperInt = 0;
            if (ZipCodeValidationUtils.isValidZipCode(zipCodeRangeUpperStr)) {
                zipCodeRangeUpperInt = Integer.parseInt(zipCodeRangeUpperStr);
            } else {
                System.out.println("Invalid value for upper bound of ZipCodeRange : " + i);
                continue;
            }

            if (zipCodeRangeLowerInt > zipCodeRangeUpperInt) {
                System.out.println("Invalid Zip Code Range, lower bound cannot be greater than upper bound");
            }


            zipCodes.add(new ZipCodeRange(zipCodeRangeLowerInt, zipCodeRangeUpperInt));
            i++;

        }
        return zipCodes;
    }
}

/**
 * A class to represent a ZipCodeRange which will have a lower and upper bound
 */

class ZipCodeRange {
    private int upper;

    public int getUpper() {
        return upper;
    }

    private int lower;

    public int getLower() {
        return lower;
    }

    ZipCodeRange(int lower, int upper) {
        this.upper = upper;
        this.lower = lower;
    }

    public String toString() {
        return "[" + lower + "," + upper + "]";
    }

}

/**
 * ZipCodeValidationUtils is a utility class to perform utility checks on the input provided to
 * ZipCodeRangeMergeApplication
 */

class ZipCodeValidationUtils {

    static boolean isValidZipCode(String zipCodeStr) {
        boolean isValidPositiveInteger = isValidPositiveInteger(zipCodeStr);
        boolean isNot5Digit = zipCodeStr.length() != 5 ? true : false;

        if (isNot5Digit) {
            System.out.println("The ZipCode bounds have to be 5 digit");
            return false;
        }
        return isValidPositiveInteger;
    }

    static boolean isValidPositiveInteger(String numStr) {
        int intVal;
        try {
            intVal = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            return false;
        }

        return intVal > 0 ? true : false;
    }


}
