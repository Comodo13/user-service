package max.userservice.service;


import max.userservice.model.User;
import max.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // Your implementation for user registration
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        // Your implementation for finding user by username
        return userRepository.findByUsername(username);
    }

    public String loginUser(String username, String password) {
        // Your implementation for user login
        return "User logged in successfully";
    }
}
