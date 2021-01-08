package kz.jm.nazira.dao;

import kz.jm.nazira.model.Role;
import kz.jm.nazira.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> index() {
        List<User> users = entityManager.createQuery("from User").getResultList();
        return users;
    }

    @Override
    public User show(Long id) {
        User user = entityManager.createQuery("from User where id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        return user;
    }

    @Override
    public void save(User user) {
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
      //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void update(Long id, User updatedUser) {
        User user = show(id);
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setAge(updatedUser.getAge());
        user.setUsername(updatedUser.getUsername());
       // user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
       user.setPassword(updatedUser.getPassword());
    }

    @Override
    public void delete(Long id) {
        User user = show(id);
        entityManager.remove(user);
    }

    @Override
    public UserDetails getUserByName(String username){
        UserDetails user = entityManager.createQuery("from User where username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        return user;
    }

}
