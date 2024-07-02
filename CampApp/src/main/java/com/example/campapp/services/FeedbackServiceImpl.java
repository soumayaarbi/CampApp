package com.example.campapp.services;

import com.example.campapp.entities.CentreDeCamping;
import com.example.campapp.entities.Feedback;
import com.example.campapp.entities.User;
import com.example.campapp.repositories.CentreDeCampingRepository;
import com.example.campapp.repositories.FeedbackRepository;
import com.example.campapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class FeedbackServiceImpl implements IFeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final CentreDeCampingRepository centreRepository;
    private UserRepository userRepository;
    @Override
    public Feedback addFeedback(Feedback feedback, Long idCentre, Integer userId) {
        Optional<CentreDeCamping> centre = centreRepository.findById(idCentre);
        Optional<User> user = userRepository.findById(userId);

        if (centre.isPresent() && user.isPresent()) {
            feedback.setCentre(centre.get());
            feedback.setUser(user.get());
            return feedbackRepository.save(feedback);
        } else {
            throw new RuntimeException("Centre or User not found");
        }
    }


    @Override
    public Feedback addFeedback(Feedback feedback, Long centreId) {
        return null;
    }

    @Override
    public void removeFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback modifyFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }
    @Override
    public List<Feedback> getFeedbacksByCentreId(Long idCentre) {
        return feedbackRepository.findByCentreIdCentre(idCentre);
    }
    @Override
    public List<Feedback> getFeedbacksByUserId(Integer userId) {
        return feedbackRepository.findByUserId(userId);
    }
    public long countFeedbacksByCentreId(Long idCentre) {
        return feedbackRepository.countByCentreIdCentre(idCentre);
    }

}
