package cz.vut.fit.pis.auth;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author burgetr
 */
@Path("ping")
public class PingResource 
{
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
        return "Hello, " + login + "! This is a protected resource.";
    }

}
