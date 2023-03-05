package cz.vut.fit.pis.auth;

import jakarta.annotation.security.DeclareRoles;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures a JAX-RS endpoint with authorization.
 *
 * @author burgetr
 */
@ApplicationPath("resources")
@DeclareRoles({ "admin", "customer" })
@BasicAuthenticationMechanismDefinition(realmName = "admin-area")
public class JAXRSConfiguration extends Application {

}
