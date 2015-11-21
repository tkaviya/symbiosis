package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_common.utilities.Validator;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_option;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.*;

@ContextConfiguration(locations = { "classpath:test-spring-config.xml" })
public class SymbiosisDBEnumHelperTests extends AbstractTestNGSpringContextTests {

    SymbiosisDBEnumHelper symbiosisDBEnumHelper;

    //setup options
    symbiosis_option sync_folder = (symbiosis_option)new symbiosis_option().setValues("SYNC_FOLDER", true);
    symbiosis_option sync_type = (symbiosis_option)new symbiosis_option().setValues("SYNC_TYPE", true);

    //setup channel
    symbiosis_channel android = (symbiosis_channel)new symbiosis_channel().setValues("ANDROID", true);

    @BeforeClass
    public void setUp() throws Exception {

        /* insert symbiosis configuration data into database */
        DaoManager.getInstance().getOptionDao().save(sync_folder);
        DaoManager.getInstance().getOptionDao().save(sync_type);
        DaoManager.getInstance().getChannelDao().save(android);

        //cache config options
        symbiosisDBEnumHelper = SymbiosisDBEnumHelper.getDaoHelper(DaoManager.getInstance().getOptionDao());
        symbiosisDBEnumHelper = SymbiosisDBEnumHelper.getDaoHelper(DaoManager.getInstance().getChannelDao());
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetSymbiosisDBEnumHelper() throws Exception {
        assertNotNull(symbiosisDBEnumHelper);
    }

    @Test
    public void testGetMappedID() throws Exception {

        //test mapped values
        Long syncFolderOptionId = symbiosisDBEnumHelper.getMappedID(OptionHelper.SYNC_FOLDER);
        Long syncTypeOptionId = symbiosisDBEnumHelper.getMappedID(OptionHelper.SYNC_TYPE);
        Long androidChannelId = symbiosisDBEnumHelper.getMappedID(ChannelHelper.ANDROID);

        System.out.println(format("Id of Option SYNC_FOLDER = %s", syncFolderOptionId));
        System.out.println(format("Id of Option SYNC_TYPE = %s", syncTypeOptionId));
        System.out.println(format("Id of Channel ANDROID = %s", androidChannelId));

        assertTrue(Validator.isNumeric(syncFolderOptionId));
        assertTrue(Validator.isNumeric(syncTypeOptionId));
        assertTrue(Validator.isNumeric(androidChannelId));

        assertEquals(sync_folder.getId(), syncFolderOptionId);
        assertEquals(sync_type.getId(), syncTypeOptionId);
        assertEquals(android.getId(), androidChannelId);
    }
}