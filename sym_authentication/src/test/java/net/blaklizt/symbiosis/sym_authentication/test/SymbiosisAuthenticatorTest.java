package net.blaklizt.symbiosis.sym_authentication.test;

import net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/20/15
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class SymbiosisAuthenticatorTest {

	private SymbiosisAuthenticator symbiosisAuthenticator;

	@Before
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

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testLoadUserByUsername() throws Exception {
//		System.out.print("Doing null check on loadUserByUsername");
//		assert (symbiosisAuthenticator.loadUserByUsername(null) == null);
	}

	public void testEncodePassword() throws Exception {

	}

	public void testIsPasswordValid() throws Exception {

	}

	public void testRegisterUser() throws Exception {

	}

	public void testRegisterNewUser() throws Exception {

	}

	public void testAuthenticateUser() throws Exception {

	}
}
