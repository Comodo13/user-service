package max.userservice.controller;


import lombok.AllArgsConstructor;
import max.userservice.dto.UserDTO;
import max.userservice.dto.UserUserDTOMapper;
import max.userservice.model.User;
import max.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    @Autowired
        UserService userService;

    @Autowired
    UserUserDTOMapper userMapper;
    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        User user = userMapper.userDtoToUser(userDTO);
        return userMapper.userToDto(userService.registerUser(user));
    }

//    @PostMapping("/login")
//    public String loginUser(@RequestBody UserDTO userDTO) {
//        return userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
//    }
}
