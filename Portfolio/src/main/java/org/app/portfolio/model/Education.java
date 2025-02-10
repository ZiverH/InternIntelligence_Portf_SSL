package org.app.portfolio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String institutionName;
    String degree;
    String fieldOfStudy;
    LocalDate startDate;
    LocalDate graduationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}