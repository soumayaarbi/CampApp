package com.example.campapp.controllers;

import com.example.campapp.entities.Feedback;
import com.example.campapp.entities.User;
import com.example.campapp.services.IFeedbackService;
import com.example.campapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    private final IFeedbackService feedbackService;
    private final UserService userService;

    @Autowired
    public FeedbackController(IFeedbackService feedbackService, UserService userService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
    }

    @PostMapping("/add/{idCentre}")
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback, @PathVariable("idCentre") Long idCentre) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       Integer userId = getUserIdFromAuthentication(authentication);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            Feedback createdFeedback = feedbackService.addFeedback(feedback, idCentre, userId);
            return ResponseEntity.ok(createdFeedback);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





    private Integer getUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getId(); // Cast principal to your User class and get the ID
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFeedback(@PathVariable Long id) {
        feedbackService.removeFeedback(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @PutMapping("/modify-feedback")
    public ResponseEntity<Feedback> modifyFeedback(@RequestBody Feedback feedback) {
        Feedback updatedFeedback = feedbackService.modifyFeedback(feedback);
        return ResponseEntity.ok(updatedFeedback);
    }


    @GetMapping("/{idCentre}")
    public ResponseEntity<List<Feedback>> getFeedbacksByCentreId(@PathVariable("idCentre") Long idCentre) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByCentreId(idCentre);
        if (feedbacks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedbacks);
    }
    @GetMapping("/user/{userId}")
    public List<Feedback> getFeedbacksByUserId(@PathVariable Integer userId) {
        return feedbackService.getFeedbacksByUserId(userId);
    }


}
