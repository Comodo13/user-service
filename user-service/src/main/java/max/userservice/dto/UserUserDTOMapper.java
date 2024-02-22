package max.userservice.dto;

import max.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserUserDTOMapper {
    User userCreateDTOToUser(UserCreateDTO source);

    User userUpdateDTOToUser(UserUpdateDTO source);
    UserCreateDTO userToUserCreateDTO(User destination);

    UserDTO userToUserDTO(User user);


}
