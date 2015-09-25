package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/20/15
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonUtilitiesTest {

    @BeforeClass public void setUp() { System.out.println("RUNNING TEST SETUP"); }

    @AfterClass public void tearDown() { System.out.println("RUNNING TEST TEAR DOWN"); }

    @Test
    public void testIsNullOrEmptyTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.isNullOrEmpty");
        Assert.assertTrue(CommonUtilities.isNullOrEmpty(null));
        Assert.assertTrue(CommonUtilities.isNullOrEmpty(""));
        Assert.assertTrue(CommonUtilities.isNullOrEmpty(new String()));
//        Assert.assertTrue(CommonUtilities.isNullOrEmpty(String.valueOf(null)));

        Assert.assertFalse(CommonUtilities.isNullOrEmpty(new String("0394580")));
        Assert.assertFalse(CommonUtilities.isNullOrEmpty("test string"));
        Assert.assertFalse(CommonUtilities.isNullOrEmpty(String.valueOf(false)));

    }

    @Test
    public void testIsNumeric()
    {
        System.out.println("RUNNING TEST: CommonUtilities.IsNumeric");
        Assert.assertFalse(CommonUtilities.isNumeric(null));
        Assert.assertFalse(CommonUtilities.isNumeric("test string"));
        Assert.assertTrue(CommonUtilities.isNumeric("0"));
        Assert.assertTrue(CommonUtilities.isNumeric("12.0"));
        Assert.assertTrue(CommonUtilities.isNumeric("6663.11"));
        Assert.assertTrue(CommonUtilities.isNumeric("-6663.11"));
        Assert.assertTrue(CommonUtilities.isNumeric("-0"));
    }

    @Test
    public void testIsValidEmailTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.isValidEmail");
        Assert.assertTrue(CommonUtilities.isValidEmail("t@t.te"));
        Assert.assertTrue(CommonUtilities.isValidEmail("t@d12.te"));
        Assert.assertTrue(CommonUtilities.isValidEmail("1t@d12.te"));
        Assert.assertTrue(CommonUtilities.isValidEmail("test@test.com"));
        Assert.assertTrue(CommonUtilities.isValidEmail("test@test.co.za"));
        Assert.assertTrue(CommonUtilities.isValidEmail("test.123-yer@test.co.za"));
        Assert.assertTrue(CommonUtilities.isValidEmail("test123@test.co.za"));

        Assert.assertFalse(CommonUtilities.isValidEmail("t@t.t"));
        Assert.assertFalse(CommonUtilities.isValidEmail("test@123@test.co.za"));
        Assert.assertFalse(CommonUtilities.isValidEmail("test#123@test.co.za"));
        Assert.assertFalse(CommonUtilities.isValidEmail("test*123@test.co.za"));
        Assert.assertFalse(CommonUtilities.isValidEmail(null));
        Assert.assertFalse(CommonUtilities.isValidEmail(""));
        Assert.assertFalse(CommonUtilities.isValidEmail("t@t"));
    }

    @Test
    public void testToCamelCaseTest() {
        System.out.println("RUNNING TEST: CommonUtilities.toCamelCase");
        Assert.assertEquals(CommonUtilities.toCamelCase(null), null);
        Assert.assertEquals(CommonUtilities.toCamelCase(""), "");
        Assert.assertEquals(CommonUtilities.toCamelCase("12"), "12");
        Assert.assertEquals(CommonUtilities.toCamelCase("1 t"), "1 T");
        Assert.assertEquals(CommonUtilities.toCamelCase("t 1"), "T 1");
        Assert.assertEquals(CommonUtilities.toCamelCase("T 1"), "T 1");
        Assert.assertEquals(CommonUtilities.toCamelCase("teSt DAta"), "Test Data");
        Assert.assertEquals(CommonUtilities.toCamelCase("MORE TEST DATA"), "More Test Data");
        Assert.assertEquals(CommonUtilities.toCamelCase("more test data"), "More Test Data");
    }

    @Test
    public void testAlignStringToLengthTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.alignStringToLength");
        Assert.assertEquals(CommonUtilities.alignStringToLength("test", 7)  , "test   ");
        Assert.assertEquals(CommonUtilities.alignStringToLength(null, 7)    , "       ");
        Assert.assertEquals(CommonUtilities.alignStringToLength("", 7)      , "       ");
        Assert.assertEquals(CommonUtilities.alignStringToLength(" test ", 7), " test  ");
    }

    @Test
    public void testFormatDoubleToMoneyTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.formatDoubleToMoney");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(0, "R"), "R0.00");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(100, "R"), "R100.00");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(1000, "R"), "R1,000.00");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(1000.55, "R"), "R1,000.55");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(1000.554, "R"), "R1,000.55");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(1000.556, "R"), "R1,000.56");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(-1000.556, "R"), "-R1,000.56");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(123456789.456, "R"), "R123,456,789.46");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(123456789.456, "$"), "$123,456,789.46");
        Assert.assertEquals(CommonUtilities.formatDoubleToMoney(-123456789.456, "ZAR"), "-ZAR123,456,789.46");
    }

    @Test
    public void testRoundTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.round");
        Assert.assertEquals(CommonUtilities.round(0), 0);
        Assert.assertEquals(CommonUtilities.round(0.123), 0);
        Assert.assertEquals(CommonUtilities.round(0.4), 0);
        Assert.assertEquals(CommonUtilities.round(-0.4), 0);
        Assert.assertEquals(CommonUtilities.round(-0.4), 0);

        Assert.assertEquals(CommonUtilities.round(1), 1);
        Assert.assertEquals(CommonUtilities.round(1.4), 1);
        Assert.assertEquals(CommonUtilities.round(1.6), 2);
        Assert.assertEquals(CommonUtilities.round(1.666), 2);
        Assert.assertEquals(CommonUtilities.round(-50.4), -50);
        Assert.assertEquals(CommonUtilities.round(-50.6), -51);
    }
}
