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


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/go")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserDTO registeredUserDTO = userService.registerUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(registeredUserDTO);
    }

    @PutMapping("/go")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserUpdateDTO userDTO) {
        UserDTO updatedUserDTO = userService.updateUser(userDTO);
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
