package org.renci.hearsay.web.ldap;

import java.util.Collection;
import java.util.HashSet;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.UserDAO;
import org.renci.hearsay.dao.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

@Component
public class CustomLDAPAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    private static final Logger logger = LoggerFactory.getLogger(CustomLDAPAuthoritiesPopulator.class);

    @Autowired
    UserDAO userDAO;

    public CustomLDAPAuthoritiesPopulator() {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData,
            String username) {
        logger.info("ENTERING getGrantedAuthorities(DirContextOperations, String)");
        Collection<GrantedAuthority> ret = new HashSet<GrantedAuthority>();

        if (userDAO == null) {
            return ret;
        }

        logger.info("userDAO is not null");

        User user = null;
        try {
            user = userDAO.findByUsername(username);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        if (user == null) {
            try {
                user = new User();
                user.setUsername(username);
                user.setId(userDAO.save(user));
            } catch (HearsayDAOException e) {
                e.printStackTrace();
            }
        } else {
            user.getRoles().forEach(a -> ret.add(new SimpleGrantedAuthority(a.getName())));
        }

        return ret;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
