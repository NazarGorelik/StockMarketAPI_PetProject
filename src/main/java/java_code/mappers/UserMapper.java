package java_code.mappers;

import java_code.dto.UserDTO;
import java_code.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
}
