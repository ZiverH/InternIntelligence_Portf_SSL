package org.app.portfolio.repository;

import org.app.portfolio.model.Education;
import org.app.portfolio.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {

    @Query("SELECT m FROM Education m JOIN FETCH m.user g WHERE g.id = :userId")
    List<Education> findAllByUser(String userId);
}
