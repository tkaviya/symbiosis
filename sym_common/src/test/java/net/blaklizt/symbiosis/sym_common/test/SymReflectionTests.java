package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.interfaces.SymReflection;
import org.testng.annotations.Test;

import java.util.Optional;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/******************************************************************************
 * *
 * Created:     28 / 10 / 2015                                             *
 * Platform:    Red Hat Linux 9                                            *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * Copyright:   Blaklizt Entertainment                                     *
 * Website:     http://www.blaklizt.net                                    *
 * Contact:     blaklizt@gmail.com                                         *
 * *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License as published by    *
 * the Free Software Foundation; either version 2 of the License, or       *
 * (at your option) any later version.                                     *
 * *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
 * GNU General Public License for more details.                            *
 * *
 ******************************************************************************/


public class SymReflectionTests implements SymReflection {
    @Test
    public void testCurrentMethodName() {
        Optional<String> current = currentMethodName();
        System.out.println(format("RUNNING TEST: %s", current.get()));
        System.out.println(format("Current Method Name = %s", current.get()));
        assertEquals(current.get(), "testCurrentMethodName", current.get());
    }

    @Test
    public void testCallerMethodName() {
        Optional<String> caller = callerMethodName();
        System.out.println(format("RUNNING TEST: %s", currentMethodName().get()));
        System.out.println(format("Caller Method Name = %s", caller.get()));
        assertNotNull(caller.get(), caller.get());
    }

    @Test
    public void testCurrentClassName() {
        Optional<String> current = currentClassName();
        System.out.println(format("RUNNING TEST: %s", currentMethodName().get()));
        System.out.println(format("Current Class Name = %s", current.get()));
        assertEquals(current.get(), this.getClass().getSimpleName(), current.get());
    }

    @Test
    public void testCallerClassName() {
        Optional<String> caller = callerClassName();
        System.out.println(format("RUNNING TEST: %s", currentMethodName().get()));
        System.out.println(format("Caller Class Name = %s", caller.get()));
        assertNotNull(caller.get(), caller.get());
    }

    @Test
    public void testCurrentClassFullName() {
        Optional<String> current = currentClassFullName();
        System.out.println(format("RUNNING TEST: %s", currentMethodName().get()));
        System.out.println(format("Current Class Name = %s", current.get()));
        assertEquals(current.get(), this.getClass().toString().replaceFirst("class ", ""), current.get());
    }

    @Test
    public void testCallerClassFullName() {
        Optional<String> caller = callerClassFullName();
        System.out.println(format("RUNNING TEST: %s", currentMethodName().get()));
        System.out.println(format("Caller Class Name = %s", caller.get()));
        assertNotNull(caller.get(), caller.get());
    }

    @Test
    public void testCurrentFileName() {
        Optional<String> current = currentFileName();
        System.out.println(format("RUNNING TEST: %s", current.get()));
        System.out.println(format("Current File Name = %s", current.get()));
        assertEquals(current.get(), this.getClass().getSimpleName() + ".java", current.get());
    }

    @Test
    public void testCallerFileName() {
        Optional<String> caller = callerFileName();
        System.out.println(format("RUNNING TEST: %s", currentMethodName().get()));
        System.out.println(format("Caller File Name = %s", caller.get()));
        assertNotNull(caller.get(), caller.get());
    }

    @Test
    public void testCurrentLineNumber() {
        Optional<Integer> current = currentLineNumber();
        System.out.println(format("RUNNING TEST: %s", currentMethodName().get()));
        System.out.println(format("Current Line Number = %d", current.get()));
        assertNotNull(current.get().toString(), current.get());
    }

    @Test
    public void testCallerLineNumber() {
        Optional<Integer> caller = callerLineNumber();
        System.out.println(format("RUNNING TEST: %s", currentMethodName().get()));
        System.out.println(format("Caller Line Number = %d", caller.get()));
        assertNotNull(caller.get().toString(), caller.get());
    }

}
