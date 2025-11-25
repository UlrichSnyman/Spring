package dev.tutorial4.bezkoderapp.controller;

import dev.tutorial4.bezkoderapp.model.Tutorial;
import dev.tutorial4.bezkoderapp.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    @PostMapping("/tutorials")
    public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
    }

    @GetMapping("/test")
    public String test() {
        return "Hello! Docker and Spring are working.";
    }
}