package cz.vut.fit.pis.jwt;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.jwt.JsonWebToken;


/**
 *
 * @author burgetr
 */
@Path("ping")
public class PingResource 
{
    @Inject
    private JsonWebToken token;
    
    @Inject
    private Principal principal;
    

    @GET
    public String ping() {
        return "Ping OK";
    }
    
    @GET
    @Path("protected")
    @RolesAllowed("admin")
    public String getProtected() {
        String login = (principal != null) ? principal.getName() : "unknown";
        return "Hello, " + login + " " + token.getGroups() + "! This is a protected resource.";
    }

}
