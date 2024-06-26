package com.example.campapp.services;

import com.example.campapp.entities.CentreDeCamping;
import com.example.campapp.entities.Feedbacks;
import com.example.campapp.entities.Utilisateur;
import com.example.campapp.repositories.CentreDeCampingRepository;
import com.example.campapp.repositories.FeedbacksRepository;
import com.example.campapp.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j

public class FeedbacksServiceImpl implements IFeedbacksService{
    FeedbacksRepository feedbacksRepository ;
    CentreDeCampingRepository centreDeCampingRepository ;
    UtilisateurRepository utilisateurRepository ;

    @Override
    public Feedbacks addFeedbacks(Feedbacks f) {
        CentreDeCamping centre = centreDeCampingRepository.findById(f.getCentre().getIdCentre()).get();
        Utilisateur user = utilisateurRepository.findById(f.getUtilisateur().getIdUtilisateur()).get();
        f.setCentre(centre);
        f.setUtilisateur(user);
        return feedbacksRepository.save(f);
    }



    @Override
    public Feedbacks retrievePublication(Long idFeed) {
        return feedbacksRepository.findById(idFeed).get();
    }

    @Override
    public void removeFeedbacks(Long idFeedback) {
          feedbacksRepository.deleteById(idFeedback);
    }

    @Override
    public Feedbacks modifyFeedbacks(Feedbacks feedbacks) {
        Feedbacks feed=feedbacksRepository.save(feedbacks);
        return feed;
    }

    @Override
    public List<Feedbacks> retrieveAllFeedbacks() {
        List<Feedbacks> listf = feedbacksRepository.findAll();
        log.info("nombre total des reclamations : " + listf.size());
        for (Feedbacks f : listf) {
            log.info("feedback : " + f);
        }
        return listf;
    }



    @Override
    public List<Feedbacks> retrieveAllReponsesParPublication(Long idCentre) {
        return feedbacksRepository.findByCentreIdCentre(idCentre);
    }





}
