/**
 * 
 */
package cz.vut.fit.pis.auth;

import java.util.Set;

import cz.vut.fit.pis.auth.data.User;
import cz.vut.fit.pis.auth.service.UserManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;


/**
 * Custom Identity Store for basic authentication that uses the provided UserManager for user management.
 * 
 * @author burgetr
 */
@ApplicationScoped
public class LocalIdentityStore implements IdentityStore
{
    @Inject
    private UserManager userMgr;
    
    @Inject
    private Pbkdf2PasswordHash pbkdf2PasswordHash;

    @Override
    public CredentialValidationResult validate(Credential credential)
    {
        if (credential instanceof UsernamePasswordCredential)
        {
            UsernamePasswordCredential authdata = (UsernamePasswordCredential) credential;
            String login = authdata.getCaller();
            User user = userMgr.find(login);
            if (user != null && pbkdf2PasswordHash.verify(authdata.getPasswordAsString().toCharArray(), user.getPassword()))
                return new CredentialValidationResult(login, Set.of("admin"));
            else
                return CredentialValidationResult.INVALID_RESULT;
        }
        else
        {
            return CredentialValidationResult.INVALID_RESULT;
        }
    }

}
