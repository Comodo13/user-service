package max.userservice.controller;


import max.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserDTO userDTO) {
        return userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
    }
}
