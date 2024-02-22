package max.userservice.service;


import max.userservice.dto.UserCreateDTO;
import max.userservice.dto.UserDTO;
import max.userservice.dto.UserUpdateDTO;
import max.userservice.dto.UserUserDTOMapper;
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

    private final UserUserDTOMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher, UserUserDTOMapper userMapper) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.userMapper = userMapper;
    }



    public UserDTO registerUser(UserCreateDTO userCreateDTO) {
        validateUser(userMapper.userCreateDTOToUser(userCreateDTO));
        User persistedUser = userRepository.save(userMapper.userCreateDTOToUser(userCreateDTO));
        eventPublisher.publishEvent(new UserCreatedEvent(this, persistedUser));
        return userMapper.userToUserDTO(persistedUser);
    }

    private void validateUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already in use");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ValidationException("Username already in use");
        }
    }


    public UserDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User existingUser = userRepository.findById(userUpdateDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(userUpdateDTO.getUserId()));
        validateUpdate(userUpdateDTO, existingUser);
        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }
    private void validateUpdate(UserUpdateDTO userUpdateDTO, User existingUser) {
        if (userUpdateDTO.getUsername() != null || userUpdateDTO.getEmail() != null) {
            validateUserUpdate(userUpdateDTO, existingUser);
        }

        if (userUpdateDTO.getUsername() != null) {
            existingUser.setUsername(userUpdateDTO.getUsername());
        }

        if (userUpdateDTO.getEmail() != null) {
            existingUser.setEmail(userUpdateDTO.getEmail());
        }

        if (userUpdateDTO.getFullName() != null) {
            existingUser.setFullName(userUpdateDTO.getFullName());
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
    }

    private void validateUserUpdate(UserUpdateDTO userUpdateDTO, User existingUser) {
        User anotherUserWithSameEmail = userRepository.findByEmail(userUpdateDTO.getEmail());
        if (anotherUserWithSameEmail != null
                && !anotherUserWithSameEmail.getId().equals(userUpdateDTO.getUserId())) {
            throw new ValidationException("Email already in use");
        }
        User anotherUserWithSameUsername = userRepository.findByUsername(userUpdateDTO.getUsername());
        if (anotherUserWithSameUsername != null
                && !anotherUserWithSameUsername.getId().equals(userUpdateDTO.getUserId())) {
            throw new ValidationException("Username already in use");
        }
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
