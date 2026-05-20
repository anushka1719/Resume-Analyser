package com.anushka.resume_analyzer.repository;

import com.anushka.resume_analyzer.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findBySkillsContaining(String skill);
}