package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.enumeration.HTTPCompressionType;
import org.testng.annotations.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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


public class HTTPCompressionTypeTests {

    @Test
    public void testIsCompressedEncodingType() {
        assertTrue(HTTPCompressionType.isCompressedEncodingType("gzip"));
        assertTrue(HTTPCompressionType.isCompressedEncodingType("zlib"));
        assertTrue(HTTPCompressionType.isCompressedEncodingType("x-compress"));
        assertTrue(HTTPCompressionType.isCompressedEncodingType("x-zip"));
        assertTrue(HTTPCompressionType.isCompressedEncodingType("x-zip, test"));
        assertTrue(HTTPCompressionType.isCompressedEncodingType("none, test, x-zip"));
        assertFalse(HTTPCompressionType.isCompressedEncodingType("none"));
        assertFalse(HTTPCompressionType.isCompressedEncodingType(null));
    }

}
