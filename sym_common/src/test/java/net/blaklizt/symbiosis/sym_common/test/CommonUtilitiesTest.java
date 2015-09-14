package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import net.blaklizt.symbiosis.sym_common.utilities.IOUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/20/15
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonUtilitiesTest {

	private static final String TEST_IMG_NAME = "test.png";

    @BeforeClass public void setUp() { System.out.println("RUNNING TEST SETUP"); }

    @AfterClass public void tearDown() { System.out.println("RUNNING TEST TEAR DOWN"); }

    @Test
    public void isNullOrEmptyTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.isNullOrEmpty");
        Assert.assertTrue(CommonUtilities.isNullOrEmpty(null));
        Assert.assertTrue(CommonUtilities.isNullOrEmpty(""));

	    Assert.assertFalse(CommonUtilities.isNullOrEmpty(String.valueOf(0)));
	    Assert.assertFalse(CommonUtilities.isNullOrEmpty("0394580"));
        Assert.assertFalse(CommonUtilities.isNullOrEmpty("test string"));
        Assert.assertFalse(CommonUtilities.isNullOrEmpty(String.valueOf(false)));

    }

    @Test
    public void isValidEmailTest()
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
    public void toCamelCaseTest() {
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
    public void alignStringToLengthTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.alignStringToLength");
        Assert.assertEquals(CommonUtilities.alignStringToLength("test", 7)  , "test   ");
        Assert.assertEquals(CommonUtilities.alignStringToLength(null, 7)    , "       ");
        Assert.assertEquals(CommonUtilities.alignStringToLength("", 7)      , "       ");
        Assert.assertEquals(CommonUtilities.alignStringToLength(" test ", 7), " test  ");
    }

    @Test
    public void formatDoubleToMoneyTest()
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
    public void roundTest()
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

	public InputStream getInputStream() {
		return getClass().getResourceAsStream("/" + TEST_IMG_NAME);
	}

    @Test
    public void testFileIO()
    {

	    int count = 0;

	    try
	    {
		    IOUtils.writeToFile(getInputStream(), IOUtils.getOSDefaultTempDir() + TEST_IMG_NAME + ++count);
		    IOUtils.writeToFile(getInputStream(), IOUtils.getOSDefaultTempDir() + TEST_IMG_NAME + ++count, true, 1024L);
		    IOUtils.writeToFile(getInputStream(), IOUtils.getOSDefaultTempDir() + TEST_IMG_NAME + ++count, true, 10240L);
		    IOUtils.writeToFile(getInputStream(), IOUtils.getOSDefaultTempDir() + TEST_IMG_NAME + ++count, true, 100L);
		    IOUtils.writeToFile(getInputStream(), IOUtils.getOSDefaultTempDir() + TEST_IMG_NAME + ++count, false);

	    }
	    catch (Exception e) { e.printStackTrace(); Assert.fail(); }
	    finally
	    {
		    File fileToDelete;
		    for (int c = count; c > 0; c++) {
			    if ((fileToDelete = new File(IOUtils.getOSDefaultTempDir() + TEST_IMG_NAME + count)).exists()) {
				    fileToDelete.delete();
			    }
		    }
	    }
	}
}
