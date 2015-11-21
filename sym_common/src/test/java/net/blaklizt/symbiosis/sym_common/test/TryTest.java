package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.structure.Try;
import org.junit.Test;

import static org.junit.Assert.*;


public class TryTest {


    @Test
    public void testTry() throws Exception {
        Try<String, RuntimeException> aTry = Try.attempt(() -> "hello world");

        assertNotNull(aTry);
        assertTrue(aTry.get().isPresent());
        assertFalse(aTry.error().isPresent());

        aTry = Try.attempt(() -> {
            throw new RuntimeException();
        });

        assertNotNull(aTry);
        assertFalse(aTry.get().isPresent());
        assertTrue(aTry.error().isPresent());


    }
}
