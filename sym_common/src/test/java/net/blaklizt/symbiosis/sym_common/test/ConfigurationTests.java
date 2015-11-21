package net.blaklizt.symbiosis.sym_common.test;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ConfigurationTests {

    @Test
    public void testGetNewLogger() {
        //check that logger is named correctly
        assertEquals(Configuration.getNewLogger(this.getClass().getSimpleName()).getName(), "ConfigurationTests");
    }

    @Test
    public void testGetSymbiosisProperty() {
        //test property reading
        assertEquals(Configuration.getSymbiosisProperty("testMaster"), "Tich de Blak");
    }

    @Test
    public void testGetProperty() {
        //test property reading
        assertEquals(Configuration.getProperty("test", "testConfig"), "TestConfig");
        assertEquals(Configuration.getProperty("test", "invalidConfig", "defaultValue"), "defaultValue");
    }

    @Test
    public void testGetCurrencySymbol() {
        assertEquals(Configuration.getCurrencySymbol(), "R");
    }

    @Test
    public void testGetCountryCodePrefix() {
        assertEquals(Configuration.getCountryCodePrefix(), "27");
    }

    @Test
    public void testGetConfigurations() {
        String[] multiConfigs = Configuration.getProperties("testMultiConfig");
        assertEquals(multiConfigs.length, 3);
        assertEquals(multiConfigs[0], "Config1");
        assertEquals(multiConfigs[0], "Config2");
        assertEquals(multiConfigs[0], "Config3");
    }
}