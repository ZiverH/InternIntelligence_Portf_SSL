package org.app.portfolio.repository;

import org.app.portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query("SELECT m FROM Skill m JOIN FETCH m.user g WHERE g.id = :userId")
    List<Skill> findAllByUser(String userId);
}
