package org.app.portfolio.repository;

import org.app.portfolio.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {


    @Query("SELECT m FROM Experience m JOIN FETCH m.user g WHERE g.id = :userId")
    List<Experience> findAllByUser(String userId);
}
