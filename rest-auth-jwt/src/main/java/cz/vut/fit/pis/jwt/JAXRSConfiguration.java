package cz.vut.fit.pis.jwt;

import org.eclipse.microprofile.auth.LoginConfig;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

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
