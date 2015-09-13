package net.blaklizt.symbiosis.sym_persistence;

import net.blaklizt.symbiosis.sym_persistence.dao.DaoManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        SymbiosisAuthUser symbiosisAuthUser = new SymbiosisAuthUser();

        symbiosisAuthUser.setAccessSystemID(1);
    }

}
