package talgat.demo.store.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends UserAbstract{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
