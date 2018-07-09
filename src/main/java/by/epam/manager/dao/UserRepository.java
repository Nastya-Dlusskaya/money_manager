package by.epam.manager.dao;

import by.epam.manager.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByLoginAndPassword(String login, String password);
}
