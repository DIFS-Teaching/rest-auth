package cz.vut.fit.pis.jwt;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) {
        if (credentials != null
                && "test".equals(credentials.getLogin())
                && "secret".equals(credentials.getPassword())) {
            
            try
            {
                String token = JwtTokenGenerator.generateJWTString("/jwt-token.json", credentials.getLogin());
                return Response.ok(token).build();
            } catch (Exception e) {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
            }
            
        } else {
            return Response.status(Status.FORBIDDEN).entity("invalid login").build();
        }
    }
    
    
}
