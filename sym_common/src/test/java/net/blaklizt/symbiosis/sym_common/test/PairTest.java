package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.utilities.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PairTest {


    @Test
    public void testFilter() throws Exception {


        Pair[] filtered = Pair.filter(new Pair("hello", "world"), new Pair("null", null));
        assertEquals(1, filtered.length);

    }
}
