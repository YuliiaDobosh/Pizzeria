package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.Status;
import lpnu.entity.enumeration.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    private LocalDate birthday;
    private Status status;
    private UserRole role;

}