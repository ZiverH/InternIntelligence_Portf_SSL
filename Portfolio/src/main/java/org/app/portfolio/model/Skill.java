package org.app.portfolio.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.app.portfolio.model.enums.Level;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Level level;
    String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;



}
