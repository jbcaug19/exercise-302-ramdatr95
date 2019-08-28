package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Controller
public class HomeController {

    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String listJobs(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model){
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Job job, BindingResult result){
        if (result.hasErrors()){
            return "jobform";
        }
        jobRepository.save(job);
        return "redirect:/";
    }

    @Entity
    public static class Job {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @NotNull
        @Size(min=4)
        private String title;

        @NotNull
        @Size(min=3)
        private String employer;

        @NotNull
        @Size(min=10)
        private String description;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEmployer() {
            return employer;
        }

        public void setEmployer(String employer) {
            this.employer = employer;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}