package by.epam.manager.service;

import by.epam.manager.dao.UserRepository;
import by.epam.manager.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User findByLoginAndPassword(String login, String password){
        return userRepository.findByLoginAndPassword(login, password);
    }


}
