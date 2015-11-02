package org.renci.hearsay.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Role;
import org.renci.hearsay.dao.model.User;

public class UserTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();
    }

    @Test
    public void testAddUserWithRole() throws HearsayDAOException {

        RoleDAOImpl roleDAO = new RoleDAOImpl();
        roleDAO.setEntityManager(em);

        Role role = new Role("ADMIN");
        em.getTransaction().begin();
        role.setId(roleDAO.save(role));
        em.getTransaction().commit();

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setEntityManager(em);

        User newUser = new User();
        newUser.setUsername("admin");
        newUser.setPassword("admin");
        newUser.getRoles().add(role);

        em.getTransaction().begin();
        newUser.setId(userDAO.save(newUser));
        em.getTransaction().commit();

    }

    @Test
    public void testAddRoleToExistingUser() throws HearsayDAOException {

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setEntityManager(em);

        RoleDAOImpl roleDAO = new RoleDAOImpl();
        roleDAO.setEntityManager(em);

        Role role = roleDAO.findByName("CARNAC");
        if (role == null) {
            role = new Role("CARNAC");
            em.getTransaction().begin();
            role.setId(roleDAO.save(role));
            em.getTransaction().commit();
        }

        String username = "jreilly";
        User hearsayUser = userDAO.findByUsername(username);

        if (hearsayUser == null) {
            hearsayUser = new User();
            hearsayUser.setUsername(username);
        }

        hearsayUser.getRoles().add(role);
        em.getTransaction().begin();
        hearsayUser.setId(userDAO.save(hearsayUser));
        em.getTransaction().commit();

    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }
}
