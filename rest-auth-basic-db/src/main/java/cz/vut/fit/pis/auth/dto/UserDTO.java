package cz.vut.fit.pis.auth.dto;

import cz.vut.fit.pis.auth.data.User;

public class UserDTO
{
    private String login;
    private String realName;
    private String password;
    
    public UserDTO()
    {
    }
    
    public UserDTO(User src)
    {
        this.login = src.getLogin();
        this.realName = src.getRealName();
        this.password = ""; // passwords are not copied by default (security)
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
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
