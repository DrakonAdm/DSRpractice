package com.csa.app.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
// import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "person", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String surname;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    //email
    @NonNull
    private String username;
    @NonNull
    private String password;

    private String phone;

    private String city;

    private String country;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<Person> friends = new HashSet<>();

    public Person(@NonNull String name, @NonNull String surname, @NonNull LocalDate date, @NonNull String username, @NonNull String password) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.username = username;
        this.password = password;
    }

}
