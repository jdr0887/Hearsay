package org.renci.hearsay.web.ldap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

@Component
public class HearsayUserDetailsContextMapper implements UserDetailsContextMapper, Serializable {

    private static final long serialVersionUID = 8214889562260886458L;

    private static final Logger logger = LoggerFactory.getLogger(HearsayUserDetailsContextMapper.class);

    @Autowired
    UserDAO userDAO;

    public HearsayUserDetailsContextMapper() {
        super();
    }

    @Override
    public UserDetails mapUserFromContext(DirContextOperations context, String username,
            Collection<? extends GrantedAuthority> authorities) {

        User ret = null;
        List<GrantedAuthority> mappedAuthorities = new ArrayList<GrantedAuthority>();

        if (userDAO == null) {
            ret = new User(username, "", true, true, true, true, mappedAuthorities);
            return ret;
        }

        logger.info("userDAO is not null");

        org.renci.hearsay.dao.model.User user = null;
        try {
            user = userDAO.findByUsername(username);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        if (user == null) {
            try {
                user = new org.renci.hearsay.dao.model.User();
                user.setUsername(username);
                user.setId(userDAO.save(user));
            } catch (HearsayDAOException e) {
                e.printStackTrace();
            }
        } else {
            user.getRoles().forEach(a -> mappedAuthorities.add(new SimpleGrantedAuthority(a.getName())));
        }

        return new User(username, "", true, true, true, true, mappedAuthorities);
    }

    @Override
    public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) {

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
