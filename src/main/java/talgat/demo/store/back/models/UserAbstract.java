package talgat.demo.store.back.models;

import lombok.Data;

@Data
public abstract class UserAbstract {
    protected String username;
    protected String password;
    protected String fullName;
    protected String address;
    protected String email;
    protected String role;
}
