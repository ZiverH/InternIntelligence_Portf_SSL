package org.app.portfolio.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String surname;
    @Column(unique=true)
    String email;
    String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Skill> skills = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Education> educationSet = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Experience> experienceSet = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Project> projects = new HashSet<>();
}
