package com.example.Assignment.repository;

import com.example.Assignment.entity.Project;
import com.example.Assignment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
//    // Find projects by manager
    List<Project> findByManager(Users manager);
//
//    // Find projects by status
//    List<Project> findByStatus(Project.ProjectStatus status);
//
//    // Find projects by team member
//    @Query("SELECT p FROM Project p WHERE :userId MEMBER OF p.teamMemberIds")
//    List<Project> findByTeamMemberId(@Param("userId") Long userId);
//
//    // Find projects where user is manager or team member
//    @Query("SELECT p FROM Project p WHERE p.managerId = :userId OR :userId MEMBER OF p.teamMemberIds")
//    List<Project> findByUserInvolvement(@Param("userId") Long userId);
//
//    // Find overdue projects
//    @Query("SELECT p FROM Project p WHERE p.deadline < :currentDate AND p.status IN ('PLANNING', 'ACTIVE')")
//    List<Project> findOverdueProjects(@Param("currentDate") LocalDate currentDate);
//
//    // Find projects by name containing
//    List<Project> findByNameContainingIgnoreCase(String name);
} 