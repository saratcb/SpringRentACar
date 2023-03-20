package academy.mindswap.rentacar.dto;

import academy.mindswap.rentacar.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

        @NotBlank(message = "Must have first name")
        private String firstName;
        @NotBlank(message = "Must have last name")
        private String lastName;
        @NotBlank(message = "Must have email")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
        private String email;

        @NotBlank(message = "Must have password")
        @Size(min = 8, message = "password must be at least 8 char long")
        private String password;

        @NotBlank(message = "Must have password")
        @Size(min = 8, message = "password must be at least 8 char long")
        private String retypedPassword;

        @NotNull(message = "Must have role")
        private UserRole role;


    }
