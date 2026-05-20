package com.anushka.resume_analyzer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String qualification;

    private String skills;

    // NEW FIELDS 👇
    private int experience;   // in years

    private int projects;     // number of projects

    private String role;

    private int score;

    private String transition;

    public Resume() {}

    public Resume(String name, String qualification, String skills,
                  int experience, int projects,
                  String role, int score, String transition) {
        this.name = name;
        this.qualification = qualification;
        this.skills = skills;
        this.experience = experience;
        this.projects = projects;
        this.role = role;
        this.score = score;
        this.transition = transition;
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQualification() {
        return qualification;
    }

    public String getSkills() {
        return skills;
    }

    public int getExperience() {
        return experience;
    }

    public int getProjects() {
        return projects;
    }

    public String getRole() {
        return role;
    }

    public int getScore() {
        return score;
    }

    public String getTransition() {
        return transition;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setProjects(int projects) {
        this.projects = projects;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTransition(String transition) {
        this.transition = transition;
    }
}