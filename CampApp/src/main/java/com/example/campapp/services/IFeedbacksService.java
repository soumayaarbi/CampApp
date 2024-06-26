package com.example.campapp.services;

import com.example.campapp.entities.Feedbacks;

import java.util.List;

public interface IFeedbacksService {
    public Feedbacks addFeedbacks(Feedbacks f);
    public void removeFeedbacks(Long idFeedback);
    public Feedbacks modifyFeedbacks(Feedbacks feedbacks);
    public List<Feedbacks> retrieveAllFeedbacks();
    public Feedbacks retrievePublication(Long idFeed);

    public List<Feedbacks> retrieveAllReponsesParPublication(Long idCentre) ;
}
