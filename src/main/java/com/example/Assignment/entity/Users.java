package com.example.Assignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

//    @JsonBackReference
    @OneToMany(mappedBy = "manager")
    private List<Project> managedProjects;

//    @JsonBackReference
    @ManyToMany(mappedBy = "team")
    private List<Project> teamProjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Project> getManagedProjects() {
//        return managedProjects;
//    }
//
//    public void setManagedProjects(List<Project> managedProjects) {
//        this.managedProjects = managedProjects;
//    }
//
//    public List<Project> getTeamProjects() {
//        return teamProjects;
//    }
//
//    public void setTeamProjects(List<Project> teamProjects) {
//        this.teamProjects = teamProjects;
//    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
