package kz.jm.nazira.dao;

import kz.jm.nazira.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDao {
    public List<User> index();

    public User show(Long id);

    public void save(User person);

    public void update(Long id, User updatedPerson);

    public void delete(Long id);

    public UserDetails getUserByName(String username);
}
