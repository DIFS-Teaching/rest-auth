package cz.vut.fit.pis.auth;

import java.security.Principal;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;


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
