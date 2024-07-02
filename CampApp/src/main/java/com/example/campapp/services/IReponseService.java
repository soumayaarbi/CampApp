package com.example.campapp.services;

import com.example.campapp.entities.Reponse;

import java.util.List;

public interface IReponseService {
    Reponse addReponse(Reponse reponse);
    void removeReponse(Long reponseId);
    List<Reponse> getReponsesByReclamation(Long reclamationId);
}
