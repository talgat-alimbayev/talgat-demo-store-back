package talgat.demo.store.back.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
@MappedSuperclass
@Data
public abstract class UserAbstract {
    protected String username;
    protected String password;
    protected String fullName;
    protected String address;
    protected String email;
    protected String role;
}
