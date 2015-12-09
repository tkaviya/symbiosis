package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.interfaces.SymReflection;
import org.testng.annotations.Test;

import static net.blaklizt.symbiosis.sym_core_lib.utilities.CommonUtilities.*;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/20/15
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonUtilitiesTests implements SymReflection {

    @Test
    public void testJoin() {
        System.out.println("RUNNING TEST: join");
        assertEquals("hello123", join("hello", 123));
        assertEquals("helloDATA", join("hello", "", "DATA"));
        assertEquals("Test123Works", join(null, "Test", null, 123, "Works"));
        assertEquals("12102015", join(null, 12, 10, null, 2015));
        assertEquals("12/10/2015", join(null, 12, "/", 10, "/", null, 2015));
        assertNull(join(null, null, null));
    }

    @Test
    public void testJoinWithDelimiter() {
        System.out.println("RUNNING TEST: joinWithDelimiter");
        assertEquals("hello123", joinWithDelimiter(null, "hello", 123));
        assertEquals("hello-123", joinWithDelimiter('-', "hello", 123));
        assertEquals("hello/123", joinWithDelimiter("/", "hello", 123));
        assertEquals("helloDATA", joinWithDelimiter(null, "hello", "", "DATA"));
        assertEquals("Test-123-Works", joinWithDelimiter('-', null, "Test", null, 123, "Works"));
        assertEquals("12/10/2015", joinWithDelimiter("/", null, 12, 10, null, 2015));
        assertNull(joinWithDelimiter(null, null, null));
    }

    @Test
    public void testCapitalise() {
        System.out.println("RUNNING TEST: capitalise");
        assertTrue(capitalize("hello").equals("Hello"));
        assertTrue(capitalize("1").equals("1"));
        assertTrue(capitalize("TestString").equals("TestString"));
        assertTrue(capitalize("").equals(""));
        assertTrue(capitalize("hello1").equals("Hello1"));
        assertTrue(capitalize("1hello").equals("1hello"));
        assertNull(capitalize(null));
    }

    @Test
    public void testDeCapitalise() {
        System.out.println("RUNNING TEST: deCapitalise");
        assertTrue(deCapitalize("Hello").equals("hello"));
        assertTrue(deCapitalize("1").equals("1"));
        assertTrue(deCapitalize("TestString").equals("testString"));
        assertTrue(deCapitalize("").equals(""));
        assertTrue(deCapitalize("Hello1").equals("hello1"));
        assertTrue(deCapitalize("1hello").equals("1hello"));
        assertNull(deCapitalize(null));
    }

    @Test
    public void testGetTempFolderLocation() {
        System.out.println("RUNNING TEST: getTempFolderLocation");
        assertNotNull(tempFolderLocation());
    }

    @Test
    public void testToCamelCase() {
        System.out.println("RUNNING TEST: toCamelCase");
        assertNull(toCamelCase(null));
        assertEquals(toCamelCase(""), "");
        assertEquals(toCamelCase("12"), "12");
        assertEquals(toCamelCase("1 t"), "1 T");
        assertEquals(toCamelCase("t 1"), "T 1");
        assertEquals(toCamelCase("T 1"), "T 1");
        assertEquals(toCamelCase("teSt DAta"), "Test Data");
        assertEquals(toCamelCase("MORE TEST DATA"), "More Test Data");
        assertEquals(toCamelCase("more test data"), "More Test Data");
    }

    @Test
    public void testAlignStringToLength()
    {
        System.out.println("RUNNING TEST: alignStringToLength");
        assertEquals(alignStringToLength("test", 7), "test   ");
        assertEquals(alignStringToLength(null, 7), "       ");
        assertEquals(alignStringToLength("", 7), "       ");
        assertEquals(alignStringToLength(" test ", 7), " test  ");
    }

    @Test
    public void testFormatDoubleToMoney()
    {
        System.out.println("RUNNING TEST: formatDoubleToMoney");
        assertEquals(formatDoubleToMoney(0, "R"), "R0.00");
        assertEquals(formatDoubleToMoney(100, "R"), "R100.00");
        assertEquals(formatDoubleToMoney(1000, "R"), "R1,000.00");
        assertEquals(formatDoubleToMoney(1000.55, "R"), "R1,000.55");
        assertEquals(formatDoubleToMoney(1000.554, "R"), "R1,000.55");
        assertEquals(formatDoubleToMoney(1000.556, "R"), "R1,000.56");
        assertEquals(formatDoubleToMoney(-1000.556, "R"), "-R1,000.56");
        assertEquals(formatDoubleToMoney(123456789.456, "R"), "R123,456,789.46");
        assertEquals(formatDoubleToMoney(123456789.456, "$"), "$123,456,789.46");
        assertEquals(formatDoubleToMoney(-123456789.456, "ZAR"), "-ZAR123,456,789.46");
    }

    @Test
    public void testRound()
    {
        System.out.println("RUNNING TEST: round");
        assertEquals(round(0), 0);
        assertEquals(round(0.123), 0);
        assertEquals(round(0.4), 0);
        assertEquals(round(-0.4), 0);
        assertEquals(round(-0.4), 0);
        assertEquals(round(1), 1);
        assertEquals(round(1.4), 1);
        assertEquals(round(1.6), 2);
        assertEquals(round(1.666), 2);
        assertEquals(round(-50.4), -50);
        assertEquals(round(-50.6), -51);
    }
}
