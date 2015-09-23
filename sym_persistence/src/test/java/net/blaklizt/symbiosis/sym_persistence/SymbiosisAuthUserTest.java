package net.blaklizt.symbiosis.sym_persistence;

import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_auth_user;
import net.blaklizt.symbiosis.sym_persistence.helper.ChannelHelper;
import net.blaklizt.symbiosis.sym_persistence.helper.DaoManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public class SymbiosisAuthUserTest {

    DaoManager daoManager;

    @BeforeClass public void setUp() { System.out.println("RUNNING TEST SETUP"); }

    @AfterClass public void tearDown() { System.out.println("RUNNING TEST TEAR DOWN"); }

    @Test
    public void createSymbiosisAuthUserTest()
    {
        System.out.println("RUNNING TEST: CommonUtilities.isNullOrEmpty");
        symbiosis_auth_user symbiosisAuthUser = new symbiosis_auth_user(
            1L, ChannelHelper.ANDROID.value(), 1L, "1", 1L, new Date(), new Date(), new Date());
    }

}
