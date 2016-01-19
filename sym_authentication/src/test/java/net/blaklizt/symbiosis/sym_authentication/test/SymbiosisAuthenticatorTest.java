package net.blaklizt.symbiosis.sym_authentication.test;

import net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_auth_user;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator.getAuthUserByUserId;
import static net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator.getUserByUsername;
import static net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator.hashPassword;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.SYMBIOSIS;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.WEB;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

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
			ClassPathXmlApplicationContext classPathXmlApplicationContext =
				new ClassPathXmlApplicationContext("test-sym_authentication-spring-context.xml");
			symbiosisAuthenticator = (SymbiosisAuthenticator)classPathXmlApplicationContext
				.getBean("symbiosisAuthenticator");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
//
//	@AfterClass
//	public void tearDown() throws Exception {
//
//	}

	@Test
	public void testHashPassword() throws Exception {
		System.out.println("Testing salted hashing");
		String hashedPassword = hashPassword("test", "test");
		System.out.println("Salted hashed password = " + hashedPassword);
		assertNotNull(hashedPassword);
	}

	@Test
	public void testGetUserByUsername() throws Exception {
		System.out.println("Testing getUserByUsername");
		ResponseObject<symbiosis_user> userResponse = getUserByUsername("tkaviya", SYMBIOSIS, WEB);
		System.out.println("getUserByUsername response = " + userResponse.getResponseCode());
		assertNull(userResponse);
	}

	@Test
	public void testGetAuthUserByUserId() throws Exception {
		System.out.println("Testing getUserByUsername");
		ResponseObject<symbiosis_auth_user> userResponse = getAuthUserByUserId(1L, SYMBIOSIS, WEB);
		System.out.println("getUserByUsername response = " + userResponse.getResponseCode());
		assertNull(userResponse);
	}

//	@Test(enabled=false)
//	public void testEncodePassword() throws Exception {
//
//	}
//
//	@Test(enabled=true, dependsOnMethods={"testRegisterNewUser"})
//	public void testIsPasswordValid() throws Exception {
//
//	}
//
//	@Test(enabled=false)
//	public void testRegisterUser() throws Exception {
//
//	}
//
//	@Test(enabled=true)
//	public void testRegisterNewUser() throws Exception {
//
//	}
//
//	@Test(enabled=true, dependsOnMethods={"testRegisterNewUser"})
//	public void testAuthenticateUser() throws Exception {
//
//	}
}
