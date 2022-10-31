package lpnu.mapper;

import lpnu.entity.User;
import lpnu.dto.UserDTO;


public class UserMapper {
    public static UserDTO toDTO(final User user) {
        final UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setStatus(user.getStatus());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public static User toEntity(final UserDTO userDTO) {
        final User user = new User();

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setBirthday(userDTO.getBirthday());
        user.setStatus(userDTO.getStatus());
        user.setRole(userDTO.getRole());

        return user;
    }
}
