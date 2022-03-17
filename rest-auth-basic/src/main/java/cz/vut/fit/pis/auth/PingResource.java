package cz.vut.fit.pis.auth;

import java.security.Principal;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author burgetr
 */
@Path("ping")
public class PingResource 
{
    @Inject
    private Principal principal;
    
    @Context
    private SecurityContext securityContext;
    
    @GET
    @PermitAll
    public String ping() {
        String login = (principal != null) ? principal.getName() : "unknown";
        return "Hello, " + login + "! Ping ok.";
    }
    
    @GET
    @Path("protected")
    @RolesAllowed("admin")
    public String getProtected() {
        String login = (principal != null) ? principal.getName() : "unknown";
        return "Hello, " + login + "! This is a protected resource.";
    }

}
