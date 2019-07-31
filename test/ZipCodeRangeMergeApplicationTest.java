import com.williamssonama.ZipCodeRange;
import com.williamssonama.ZipCodeRangeMergeApplication;
import com.williamssonama.ZipCodeValidationUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ZipCodeRangeMergeApplicationTest {

    ZipCodeRange range1 = new ZipCodeRange(12346, 12350);
    ZipCodeRange range2 = new ZipCodeRange(12351, 12355);
    ZipCodeRange range3 = new ZipCodeRange(12356, 12360);
    ZipCodeRangeMergeApplication application;

    @Before
    public void setUp() {
        application = new ZipCodeRangeMergeApplication();
    }


    @Test
    public void testPositiveZipCodeRangeNoOverlapSorted() {
        List<ZipCodeRange> input = createPositiveZipCodeRangeNoOverlapSorted();
        List<ZipCodeRange> output = application.mergeZipCodeRanges(input);

        assertEquals(output.size(), input.size());
        assertEquals(output, input);
    }

    @Test
    public void testPositiveZipCodeRangeOneOverlapSorted() {
        List<ZipCodeRange> input = createPositiveZipCodeRangeOneOverlapSorted();
        List<ZipCodeRange> output = application.mergeZipCodeRanges(input);
        int outputWithdifference = output.size() + 1;
        assertEquals(outputWithdifference, input.size());
        assertNotEquals(output.get(0), input.get(0));
        assertEquals(output.get(1), input.get(2));
        assertEquals(output.get(0).getUpper(), input.get(1).getUpper());

    }

    @Test
    public void testPositiveZipCodeRangeNoOverlapNonSorted() {
        List<ZipCodeRange> input = createPositiveZipCodeRangeNoOverlapNonSorted();
        List<ZipCodeRange> output = application.mergeZipCodeRanges(input);

        assertEquals(output.size(), input.size());
        assertEquals(output, input);
    }

    @Test
    public void testPositiveZipCodeRangeOneOverlapNonSorted() {

        List<ZipCodeRange> input = createPositiveZipCodeRangeOneOverlapNonSorted();
        List<ZipCodeRange> output = application.mergeZipCodeRanges(input);

        int outputWithdifference = output.size() + 1;
        assertEquals(outputWithdifference, input.size());
        assertNotEquals(output.get(0), input.get(0));
        assertEquals(output.get(1), input.get(2));
        assertEquals(output.get(0).getUpper(), input.get(1).getUpper());
    }

    @Test
    public void testPositiveZipCodeRangeLowerEqualToUpper() {

        List<ZipCodeRange> input = createPositiveZipCodeRangeLowerEqualToUpper();
        List<ZipCodeRange> output = application.mergeZipCodeRanges(input);
        int outputWithdifference = output.size() + 1;
        assertEquals(outputWithdifference, input.size());
        assertNotEquals(output.get(0), input.get(0));
        assertEquals(output.get(1), input.get(2));
        assertEquals(output.get(0).getUpper(), input.get(1).getUpper());
    }

    @Test
    public void testPositiveZipCodeRangeSameRangeLowerEqualToUpper() {

        List<ZipCodeRange> input = createPositiveZipCodeRangeSameRangeLowerEqualToUpper();
        List<ZipCodeRange> output = application.mergeZipCodeRanges(input);

        assertEquals(input, output);
    }

    @Test
    public void testPositiveZipCodeRangeAllRangeOverlap() {

        List<ZipCodeRange> input = createPositiveZipCodeRangeAllRangeOverlap();
        List<ZipCodeRange> output = application.mergeZipCodeRanges(input);

        assertNotEquals(input, output);
        assertEquals(output.size(), 1);
        assertEquals(output.get(0).getLower(), input.get(0).getLower());
        assertEquals(output.get(0).getUpper(), input.get(2).getUpper());
    }

    @Test
    public void testNegativeZipCodeRangeNot5Digit() {

        assertEquals(ZipCodeValidationUtils.isValidZipCode("1234"), false);
    }


    @Test
    public void testIsValidPositiveInteger() {

        assertEquals(ZipCodeValidationUtils.isValidPositiveInteger("223s"), false);
        assertEquals(ZipCodeValidationUtils.isValidPositiveInteger("223"), true);
        assertEquals(ZipCodeValidationUtils.isValidPositiveInteger("-223"), false);
        assertEquals(ZipCodeValidationUtils.isValidPositiveInteger("0"), false);
    }

    private List<ZipCodeRange> createPositiveZipCodeRangeNoOverlapSorted() {

        List<ZipCodeRange> zipCodeRanges = new ArrayList<>();

        zipCodeRanges.add(range1);
        zipCodeRanges.add(range2);
        zipCodeRanges.add(range3);

        return zipCodeRanges;
    }

    private List<ZipCodeRange> createPositiveZipCodeRangeOneOverlapSorted() {

        List<ZipCodeRange> zipCodeRanges = new ArrayList<>();


        zipCodeRanges.add(range1);
        range2.setLower(12349);
        zipCodeRanges.add(range2);
        zipCodeRanges.add(range3);

        return zipCodeRanges;
    }

    private List<ZipCodeRange> createPositiveZipCodeRangeNoOverlapNonSorted() {

        List<ZipCodeRange> zipCodeRanges = new ArrayList<>();

        zipCodeRanges.add(range1);
        zipCodeRanges.add(range3);
        zipCodeRanges.add(range2);

        return zipCodeRanges;
    }


    private List<ZipCodeRange> createPositiveZipCodeRangeOneOverlapNonSorted() {

        List<ZipCodeRange> zipCodeRanges = new ArrayList<>();

        zipCodeRanges.add(range1);
        zipCodeRanges.add(range3);
        range2.setLower(12349);
        zipCodeRanges.add(range2);

        return zipCodeRanges;
    }

    private List<ZipCodeRange> createPositiveZipCodeRangeLowerEqualToUpper() {

        List<ZipCodeRange> zipCodeRanges = new ArrayList<>();

        zipCodeRanges.add(range1);
        range2.setLower(12350);
        zipCodeRanges.add(range2);
        zipCodeRanges.add(range3);

        return zipCodeRanges;
    }

    private List<ZipCodeRange> createPositiveZipCodeRangeSameRangeLowerEqualToUpper() {

        List<ZipCodeRange> zipCodeRanges = new ArrayList<>();

        range1.setLower(12350);
        zipCodeRanges.add(range1);

        return zipCodeRanges;
    }


    private List<ZipCodeRange> createPositiveZipCodeRangeAllRangeOverlap() {

        List<ZipCodeRange> zipCodeRanges = new ArrayList<>();

        zipCodeRanges.add(range1);
        range2.setLower(12350);
        zipCodeRanges.add(range2);
        range3.setLower(12355);
        zipCodeRanges.add(range3);

        return zipCodeRanges;
    }
    
}
