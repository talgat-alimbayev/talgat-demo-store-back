package talgat.demo.store.back.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @Size(min = 5, message = "Слишком короткое имя пользователя")
    private String username;
    @Size(min = 5, message = "Слишком короткий пароль")
    private String password;
    @Size(min = 5, message = "Слишком короткое имя")
    private String fullName;
    @Size(min = 5, message = "Слишком короткий адрес")
    private String address;
    @Email(message = "Не похоже на корректный имейл адрес")
    private String email;
    @Pattern(regexp = "ROLE_USER|ROLE_ADMIN", message = "Неверная роль пользователя")
    @NotBlank(message = "Неверная роль пользователя")
    private String role;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fullName = user.getFullName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
