package com.example.campapp.services;

import com.example.campapp.entities.Feedback;

import java.util.List;
import java.util.Optional;

public interface IFeedbackService {
    Feedback addFeedback(Feedback feedback, Long idCentre, Integer userId);

    Feedback addFeedback(Feedback feedback, Long centreId);
    void removeFeedback(Long feedbackId);
    List<Feedback> getAllFeedbacks();
    Feedback modifyFeedback(Feedback feedback);
    Optional<Feedback> getFeedbackById(Long id);
    List<Feedback> getFeedbacksByCentreId(Long idCentre);

    List<Feedback> getFeedbacksByUserId(Integer userId);
}
