package net.blaklizt.symbiosis.sym_authentication.session;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 2013/07/08
 * Time: 11:32 PM
 */
public class UserSession
{
	protected String username;
	protected String password;

	public UserSession(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	public String getPassword() { return password; }

	public String getUsername() { return username; }
}
