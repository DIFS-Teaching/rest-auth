package cz.vut.fit.pis.auth;

import java.net.URI;
import java.util.List;

import cz.vut.fit.pis.auth.data.User;
import cz.vut.fit.pis.auth.dto.ErrorDTO;
import cz.vut.fit.pis.auth.dto.UserDTO;
import cz.vut.fit.pis.auth.service.UserManager;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
public class UserResource
{
    @Inject
    private UserManager userMgr;
    
    @Inject
    private Pbkdf2PasswordHash pbkdf2PasswordHash;
    
    @Context
    private UriInfo context;

    public UserResource() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers() 
    {
        return userMgr.findAll().stream().map(u -> new UserDTO(u)).toList();
    }

    @Path("/{login}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("login") String login) 
    {
        User u = userMgr.find(login);
        if (u != null)
            return Response.ok(new UserDTO(u)).build();
        else
            return Response.status(Status.NOT_FOUND).entity(new ErrorDTO("not found")).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserDTO user)
    {
        User existing = userMgr.find(user.getLogin());
        if (existing == null)
        {
            User p = new User();
            p.setLogin(user.getLogin());
            p.setRealName(user.getRealName());
            p.setPassword(pbkdf2PasswordHash.generate(user.getPassword().toCharArray()));
            
            User savedUser = userMgr.save(p);
            final URI uri = UriBuilder.fromPath("/users/{resourceServerId}").build(savedUser.getLogin());
            return Response.created(uri).entity(new UserDTO(savedUser)).build();
        }
        else
        {
            return Response.status(Status.CONFLICT).entity(new ErrorDTO("duplicate user")).build();
        }
    }

}
