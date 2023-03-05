/**
 * 
 */
package cz.vut.fit.pis.auth;

import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;


/**
 *
 * @author burgetr
 */
@ApplicationScoped
public class LocalIdentityStore implements IdentityStore
{

    @Override
    public CredentialValidationResult validate(Credential credential)
    {
        if (credential instanceof UsernamePasswordCredential
                && ((UsernamePasswordCredential) credential).compareTo("test", "secret"))
        {
            return new CredentialValidationResult("test", Set.of("admin"));
        }
        else
        {
            return CredentialValidationResult.INVALID_RESULT;
        }
    }

}
