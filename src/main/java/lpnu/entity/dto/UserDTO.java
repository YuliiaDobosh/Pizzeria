package lpnu.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.UserRole;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @Email
    private String email;
    @NotNull
    private UserRole role;

}