package cz.vut.fit.pis.auth;

import javax.annotation.security.DeclareRoles;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

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
