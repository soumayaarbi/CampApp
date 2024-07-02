package com.example.campapp.repositories;

import com.example.campapp.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByCentreIdCentre(Long idCentre);
    List<Feedback> findByUserId(Integer userId);
    long countByCentreIdCentre(Long idCentre);
}
