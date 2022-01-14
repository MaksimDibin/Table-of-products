package ru.dibin.persist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.dibin.service.UserRepr;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false, length = 512)
    private String password;

    @Column
    private String email;

    @Column
    private Integer age;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(UserRepr user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.roles = user.getRoles();
    }
}
