package com.anushka.resume_analyzer.controller;

import com.anushka.resume_analyzer.model.Resume;
import com.anushka.resume_analyzer.repository.ResumeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "*")
public class ResumeController {

    @Autowired
    private ResumeRepository repo;

    // ---------- TEST ----------
    @GetMapping("/test")
    public String test() {
        return "Backend is working!";
    }

    // ---------- ADD RESUME ----------
    @PostMapping("/add")
    public Resume addResume(@Valid @RequestBody Resume resume) {
        return repo.save(resume);
    }

    // ---------- GET ALL ----------
    @GetMapping("/all")
    public List<Resume> getAllResumes() {
        return repo.findAll();
    }

    // ---------- SEARCH BY SKILL ----------
    @GetMapping("/search")
    public List<Resume> searchBySkill(@RequestParam String skill) {
        return repo.findBySkillsContaining(skill);
    }

    // ---------- RANK (based on frontend score) ----------
    @GetMapping("/rank")
    public List<Resume> rankResumes() {
        List<Resume> list = repo.findAll();
        list.sort((a, b) -> b.getScore() - a.getScore());
        return list;
    }

    // ---------- DELETE ALL (optional but useful for testing) ----------
    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        repo.deleteAll();
        return "All resumes deleted successfully!";
    }
}