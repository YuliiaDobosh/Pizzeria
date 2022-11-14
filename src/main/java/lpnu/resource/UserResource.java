package lpnu.resource;

import lpnu.entity.dto.UserDTO;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable final Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody @Validated final UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody final UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable final Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}