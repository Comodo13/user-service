package max.userservice.service;


import max.userservice.dto.UserUpdateDTO;
import max.userservice.exception.EntityNotFoundException;
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

    public User updateUser(UserUpdateDTO userUpdateDTO) {
        User existingUser = userRepository.findById(userUpdateDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(userUpdateDTO.getUserId()));

        // Update only the non-null fields from the update DTO
        if (userUpdateDTO.getUsername() != null) {
            existingUser.setUsername(userUpdateDTO.getUsername());
        }
        if (userUpdateDTO.getEmail() != null) {
            // Check if the updated email is already in use by another user
            if (!existingUser.getEmail().equals(userUpdateDTO.getEmail())
                    && userRepository.existsByEmail(userUpdateDTO.getEmail())) {
                throw new ValidationException("Email already in use");
            }
            existingUser.setEmail(userUpdateDTO.getEmail());
        }
        if (userUpdateDTO.getFullName() != null) {
            existingUser.setFullName(userUpdateDTO.getFullName());
        }
        if (userUpdateDTO.getBio() != null) {
            existingUser.setBio(userUpdateDTO.getBio());
        }
        if (userUpdateDTO.getProfileImage() != null) {
            existingUser.setProfileImage(userUpdateDTO.getProfileImage());
        }
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
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
