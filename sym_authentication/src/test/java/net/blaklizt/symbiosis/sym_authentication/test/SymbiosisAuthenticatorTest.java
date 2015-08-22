package net.blaklizt.symbiosis.sym_authentication.test;

import net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/20/15
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class SymbiosisAuthenticatorTest {

	private SymbiosisAuthenticator symbiosisAuthenticator;

	@BeforeClass
	public void setUp() throws Exception
	{
		try
		{
//			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("sym_authentication-spring-context.xml");
//			symbiosisAuthenticator = (SymbiosisAuthenticator)classPathXmlApplicationContext.getBean("symbiosisAuthenticator");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() throws Exception {

	}

	@Test(enabled=false, dependsOnMethods={"testAuthenticateUser"})
	public void testLoadUserByUsername() throws Exception {
		System.out.print("Doing null check on loadUserByUsername");
		Assert.assertNotNull(symbiosisAuthenticator.loadUserByUsername(null));
	}

	@Test(enabled=false)
	public void testEncodePassword() throws Exception {

	}

	@Test(enabled=false)
	public void testIsPasswordValid() throws Exception {

	}

	@Test(enabled=false)
	public void testRegisterUser() throws Exception {

	}

	@Test(enabled=false)
	public void testRegisterNewUser() throws Exception {

	}

	@Test(enabled=false, dependsOnMethods={"method1"})
	public void testAuthenticateUser() throws Exception {

	}
}
