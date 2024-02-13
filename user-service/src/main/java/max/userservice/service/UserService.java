package max.userservice.service;


import max.userservice.exception.ValidationException;
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
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already in use");
        }

        // Check if username is already in use
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ValidationException("Username already in use");
        }
        User persistedUser = userRepository.save(user);
        System.out.println(persistedUser);
        return persistedUser;
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
