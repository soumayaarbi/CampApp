package com.example.campapp.repositories;

import com.example.campapp.entities.CentreDeCamping;
import com.example.campapp.entities.Feedbacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FeedbacksRepository extends JpaRepository<Feedbacks, Long> {

    public List<Feedbacks> findByCentreIdCentre(Long idCentre) ;
}
