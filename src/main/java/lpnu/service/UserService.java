package lpnu.service;

import lpnu.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO create(UserDTO userDTO);

    UserDTO findById(Long id);

    UserDTO update(UserDTO userDTO);

    void delete(Long id);
}