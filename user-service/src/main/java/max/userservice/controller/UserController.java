package max.userservice.controller;


import jakarta.validation.Valid;
import max.userservice.dto.UserCreateDTO;
import max.userservice.dto.UserDTO;
import max.userservice.dto.UserUpdateDTO;
import max.userservice.dto.UserUserDTOMapper;
import max.userservice.model.User;
import max.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/")
public class UserController {

    UserService userService;

    UserUserDTOMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserUserDTOMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @PostMapping("/go")
    public ResponseEntity<UserCreateDTO> registerUser(@Valid @RequestBody UserCreateDTO userDTO) {
        System.out.println(userDTO);
        User user = userMapper.userCreateDTOToUser(userDTO);
        System.out.println(user);
        User registeredUser = userService.registerUser(user);
        UserCreateDTO registeredUserDTO = userMapper.userToUserCreateDTO(registeredUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(registeredUserDTO);
    }

    @PutMapping("/go")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserUpdateDTO userDTO) {
        User updatedUser = userService.updateUser(userDTO);
        UserDTO updatedUserDTO = userMapper.userToUserDTO(updatedUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedUserDTO);
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
        Boolean userWasDeleted = userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userWasDeleted);
    }

//    @PostMapping("/login")
//    public String loginUser(@RequestBody UserDTO userDTO) {
//        return userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
//    }
}
