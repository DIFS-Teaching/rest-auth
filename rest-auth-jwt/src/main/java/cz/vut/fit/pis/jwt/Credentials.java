/**
 * 
 */
package cz.vut.fit.pis.jwt;

/**
 * @author burgetr
 *
 */
public class Credentials
{
    public String login;
    public String password;
    
    public String getLogin()
    {
        return login;
    }
    
    public void setLogin(String login)
    {
        this.login = login;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
}