package max.userservice.service;


import max.userservice.dto.UserUpdateDTO;
import max.userservice.events.UserCreatedEvent;
import max.userservice.exception.EntityNotFoundException;
import max.userservice.exception.ValidationException;
import max.userservice.model.User;
import max.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already in use");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ValidationException("Username already in use");
        }

        User persistedUser = userRepository.save(user);
        eventPublisher.publishEvent(new UserCreatedEvent(this, persistedUser));
        return persistedUser;
    }

    public User updateUser(UserUpdateDTO userUpdateDTO) {
        User existingUser = userRepository.findById(userUpdateDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(userUpdateDTO.getUserId()));



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
        return userRepository.findByUsername(username);
    }

    public String loginUser(String username, String password) {
        // Your implementation for user login
        return "User logged in successfully";
    }


    public boolean deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
            return true; // Deletion successful
        } catch (EmptyResultDataAccessException ex) {
            // If the user with the given ID does not exist
            return false; // Deletion failed
        } catch (Exception ex) {
            // Other exceptions such as database errors
            ex.printStackTrace(); // Log the exception
            return false; // Deletion failed
        }
    }
}
