package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.utilities.Validator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/20/15
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ValidatorTests {

    @Test
    public void testIsNullOrEmptyTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.isNullOrEmpty");
        Assert.assertTrue(Validator.isNullOrEmpty(null));
        Assert.assertTrue(Validator.isNullOrEmpty(""));
        Assert.assertTrue(Validator.isNullOrEmpty(new String()));
        Assert.assertFalse(Validator.isNullOrEmpty(new String("0394580")));
        Assert.assertFalse(Validator.isNullOrEmpty("test string"));
        Assert.assertFalse(Validator.isNullOrEmpty(String.valueOf(false)));

    }

    @Test
    public void testIsNumeric()
    {
        System.out.println("RUNNING TEST: CommonUtilities.IsNumeric");
        Assert.assertFalse(Validator.isNumeric(null));
        Assert.assertFalse(Validator.isNumeric("test string"));
        Assert.assertTrue(Validator.isNumeric("0"));
        Assert.assertTrue(Validator.isNumeric("12.0"));
        Assert.assertTrue(Validator.isNumeric("6663.11"));
        Assert.assertTrue(Validator.isNumeric("-6663.11"));
        Assert.assertTrue(Validator.isNumeric("-0"));
        Assert.assertTrue(Validator.isNumeric(new BigDecimal(0)));
        Assert.assertTrue(Validator.isNumeric(new BigDecimal(0.4)));
        Assert.assertTrue(Validator.isNumeric(new Float(0.4)));
        Assert.assertTrue(Validator.isNumeric(1L));
        Assert.assertFalse(Validator.isNumeric(new Object()));
        Assert.assertFalse(Validator.isNumeric(new String()));
    }

    @Test
    public void testIsValidEmailTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.isValidEmail");
        Assert.assertTrue(Validator.isValidEmail("t@t.te"));
        Assert.assertTrue(Validator.isValidEmail("t@d12.te"));
        Assert.assertTrue(Validator.isValidEmail("1t@d12.te"));
        Assert.assertTrue(Validator.isValidEmail("test@test.com"));
        Assert.assertTrue(Validator.isValidEmail("test@test.co.za"));
        Assert.assertTrue(Validator.isValidEmail("test.123-yer@test.co.za"));
        Assert.assertTrue(Validator.isValidEmail("test123@test.co.za"));
        Assert.assertFalse(Validator.isValidEmail("t@t.t"));
        Assert.assertFalse(Validator.isValidEmail("test@123@test.co.za"));
        Assert.assertFalse(Validator.isValidEmail("test#123@test.co.za"));
        Assert.assertFalse(Validator.isValidEmail("test*123@test.co.za"));
        Assert.assertFalse(Validator.isValidEmail(null));
        Assert.assertFalse(Validator.isValidEmail(""));
        Assert.assertFalse(Validator.isValidEmail("t@t"));
    }

    @Test
    public void testIsValidPassword()
    {
        System.out.println("RUNNING TEST: CommonUtilities.isValidPassword");
        Assert.assertFalse(Validator.isValidPassword("12345"));
        Assert.assertTrue(Validator.isValidPassword("l2n5k4jl56n2(*&^*(^"));
        Assert.assertFalse(Validator.isValidPassword("12345abcde12345abcde12345abcde12345abcde12345abcde1"));
    }

    @Test
    public void testIsValidMsisdn() {
        System.out.println("RUNNING TEST: CommonUtilities.isValidMsisdn");

        Assert.assertTrue(Validator.isValidMsisdn("0123456789"));
        Assert.assertTrue(Validator.isValidMsisdn("27123456789", "27"));
        Assert.assertTrue(Validator.isValidMsisdn("00263123456789", "263"));
        Assert.assertTrue(Validator.isValidMsisdn("263123456789", "263"));
        Assert.assertTrue(Validator.isValidMsisdn("+27123456789", "27"));

        Assert.assertFalse(Validator.isValidMsisdn("1234567890"));
        Assert.assertFalse(Validator.isValidMsisdn("12345"));
        Assert.assertFalse(Validator.isValidMsisdn("abcdefg"));
        Assert.assertFalse(Validator.isValidMsisdn("00263123456789", "27"));
        Assert.assertFalse(Validator.isValidMsisdn("263123456789", "264"));
        Assert.assertFalse(Validator.isValidMsisdn("+27123456789", "28"));
        Assert.assertFalse(Validator.isValidMsisdn("28123456789", "27"));
    }

    @Test
    public void testIsValidName() {
        System.out.println("RUNNING TEST: CommonUtilities.isValidFirstName");
        Assert.assertFalse(Validator.isValidName("a"));
        Assert.assertTrue(Validator.isValidName("Jason"));
        Assert.assertFalse(Validator.isValidName("Jason1"));
        Assert.assertFalse(Validator.isValidName("12345abcde12345abcde12345abcde12345abcde12345abcde1"));
        Assert.assertFalse(Validator.isValidName("1234567890"));
    }

    @Test
    public void testIsValidUsername() {
        System.out.println("RUNNING TEST: CommonUtilities.isValidUsername");
        Assert.assertFalse(Validator.isValidUsername("a"));
        Assert.assertTrue(Validator.isValidUsername("j_boss"));
        Assert.assertFalse(Validator.isValidUsername("j boss"));
        Assert.assertTrue(Validator.isValidUsername("jBoss1"));
        Assert.assertFalse(Validator.isValidUsername("12345abcde12345abcde12345abcde12345abcde12345abcde1"));
        Assert.assertTrue(Validator.isValidUsername("abcdefghij"));
        Assert.assertTrue(Validator.isValidUsername("test_1"));
        Assert.assertFalse(Validator.isValidUsername("test_@"));
        Assert.assertTrue(Validator.isValidUsername("_josh.boss"));
        Assert.assertTrue(Validator.isValidUsername("j-boss"));
    }
}
