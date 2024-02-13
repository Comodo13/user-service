package max.userservice.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import max.userservice.dto.UserDTO;
import max.userservice.dto.UserUserDTOMapper;
import max.userservice.model.User;
import max.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        User user = userMapper.userDtoToUser(userDTO);
        System.out.println(user);
        User registeredUser = userService.registerUser(user);
        UserDTO registeredUserDTO = userMapper.userToDto(registeredUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(registeredUserDTO);
    }

//    @PostMapping("/login")
//    public String loginUser(@RequestBody UserDTO userDTO) {
//        return userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
//    }
}
