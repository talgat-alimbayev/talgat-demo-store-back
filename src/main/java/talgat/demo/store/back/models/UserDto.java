package talgat.demo.store.back.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto extends UserAbstract{
    private Long id;

    public UserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fullName = user.getFullName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
