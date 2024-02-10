package max.userservice.service;


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

    public String registerUser(String username, String password, String email) {
        // Your implementation for user registration
        return "User registered successfully";
    }

    public String loginUser(String username, String password) {
        // Your implementation for user login
        return "User logged in successfully";
    }
}
