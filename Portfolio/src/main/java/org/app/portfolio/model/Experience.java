package org.app.portfolio.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String companyName;
    LocalDate startDate;
    LocalDate endDate;
    String description;
    String position;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
