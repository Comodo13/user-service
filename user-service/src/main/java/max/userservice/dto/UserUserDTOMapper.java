package max.userservice.dto;

import max.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserUserDTOMapper {
    User userDtoToUser(UserDTO source);
    UserDTO userToDto(User destination);
}
