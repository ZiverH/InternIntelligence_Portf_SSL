package org.app.portfolio.repository;

import org.app.portfolio.model.Project;
import org.app.portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("SELECT m FROM Project m JOIN FETCH m.user g WHERE g.id = :userId")
    List<Project> findAllByUser(String userId);
}
