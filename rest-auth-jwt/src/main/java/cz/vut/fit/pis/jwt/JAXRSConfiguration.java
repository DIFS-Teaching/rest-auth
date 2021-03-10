package cz.vut.fit.pis.jwt;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;

/**
 * Configures a JAX-RS endpoint with authorization.
 *
 * @author burgetr
 */
@ApplicationPath("resources")
@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({ "admin", "staff", "customer" })
public class JAXRSConfiguration extends Application {

}
