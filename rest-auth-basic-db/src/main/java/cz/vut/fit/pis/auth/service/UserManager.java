/**
 * 
 */
package cz.vut.fit.pis.auth.service;

import java.util.List;

import cz.vut.fit.pis.auth.data.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


/**
 * User manager service (business logic).
 * @author burgetr
 */
@RequestScoped
public class UserManager 
{
    @PersistenceContext
    private EntityManager em;

    public UserManager() 
    {
    }
    
    @Transactional
    public User save(User p)
    {
    	return em.merge(p);
    }
	
    @Transactional
    public void remove(User p)
    {
    	em.remove(em.merge(p));
    }
    
    public User find(String login)
    {
    	return em.find(User.class, login);
    }

    public List<User> findAll()
    {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

}
