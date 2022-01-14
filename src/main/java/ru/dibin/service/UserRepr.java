package ru.dibin.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.dibin.persist.Role;
import ru.dibin.persist.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// DTO
@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserRepr {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;

    @Email
    private String email;

    @Min(1)
    private Integer age;

    private Set<Role> roles;

    public UserRepr(String username) {
        this.username = username;
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.roles = new HashSet<>(user.getRoles());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRepr userRepr = (UserRepr) o;
        return id.equals(userRepr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
