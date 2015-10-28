package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.mail.EMailer;
import net.blaklizt.symbiosis.sym_common.utilities.Reflection;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;


public class ReflectionTest {

    public boolean test1;
    public Boolean test2;
    public String test3;
    public Number test4;

    @Test
    public void testGetAllFields() throws Exception {

        Collection<Field> allFields = Reflection.getAllFields(ReflectionTest.class);
        boolean foundAll = true;
        for (Field aField : allFields) {
            //check for fields both on key entity and on base entity
            foundAll |= aField.getName().equals("test1") |
                        aField.getName().equals("test2") |
                        aField.getName().equals("test3") |
                        aField.getName().equals("test4");
            System.out.println(aField.getName());
        }
        assertTrue(foundAll);
    }

    @Test
    public void testGetAllFieldsFiltered() throws Exception {

        Collection<Field> allFields =
            Reflection.getAllFields(ReflectionTest.class, (field) -> !field.getName().equals("test1"));
        boolean foundAll = true;
        for (Field aField : allFields) {
            //check for fields both on key entity and on base entity
            foundAll |= aField.getName().equals("test2") |
                    aField.getName().equals("test3") |
                    aField.getName().equals("test4");
            System.out.println("Found field: " + aField.getName());
        }
        assertTrue(foundAll);
        assertTrue("Number of fields found: " + allFields.size(), allFields.size() == 3);
    }

    @Test
    public void testGetMethod() throws Exception {


        Field primitiveBoolean = getClass().getField("test1");
        String method = Reflection.getMethod(primitiveBoolean);
        assertEquals("isTest1", method);

        Field wrapperBoolean = getClass().getField("test2");
        method = Reflection.getMethod(wrapperBoolean);
        assertEquals("isTest2", method);

        Field stringField = getClass().getField("test3");
        method = Reflection.getMethod(stringField);
        assertEquals("getTest3", method);

    }

    @Test
    public void testSetMethod() throws Exception {

        Field primitiveBoolean = getClass().getField("test1");
        String method = Reflection.setMethod(primitiveBoolean);
        assertEquals("setTest1", method);
    }

    @Test
    public void testHasSetter() throws Exception {

        Field setterField = getClass().getField("test1");
        assertTrue(Reflection.hasSetterMethod(setterField, getClass()));

        Field noSetterField = getClass().getField("test2");
        assertFalse(Reflection.hasSetterMethod(noSetterField, getClass()));

        Field widenedField = getClass().getField("test3");
        assertFalse(Reflection.hasSetterMethod(widenedField, getClass()));


    }

    @Test
    public void testGetField() throws Exception {

        Optional<Field> result = Reflection.getField("username", EMailer.PopupAuthenticator.class);
        assertNotNull(result);
        assertTrue(result.isPresent());

        result = Reflection.getField("requestingUserName", EMailer.PopupAuthenticator.class);
        assertNotNull(result);
        assertTrue(result.isPresent());

    }


    @Test
    public void testHasAnyAnnotations() throws Exception {

        boolean hasAnnotations = Reflection.hasAnyAnnotation(Runnable.class, FunctionalInterface.class, Deprecated.class);
        assertTrue("Should have detected FunctionalInterface annotation", hasAnnotations);

        hasAnnotations = Reflection.hasAnyAnnotation(Runnable.class, Deprecated.class);
        assertFalse("Should have detected no annotations", hasAnnotations);

    }

    public void setTest1(boolean test1) {
        this.test1 = test1;
    }

    public void setTest4(Long test4) {
        this.test4 = test4;
    }
}
