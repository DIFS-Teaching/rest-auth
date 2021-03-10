/**
 * 
 */
package cz.vut.fit.pis.auth;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author burgetr
 */
@ApplicationScoped
public class LocalIdentityStore implements IdentityStore
{

    public CredentialValidationResult validate(UsernamePasswordCredential credential)
    {
        if (credential.compareTo("test", "secret"))
        {
            return new CredentialValidationResult("test", Set.of("admin"));
        }
        else
        {
            return CredentialValidationResult.INVALID_RESULT;
        }
    }
    
}
