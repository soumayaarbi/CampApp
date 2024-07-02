package com.example.campapp.services;

import com.example.campapp.entities.Reponse;
import com.example.campapp.repositories.ReponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReponseServiceImpl implements IReponseService {
    private final ReponseRepository reponseRepository;

    @Override
    public Reponse addReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    @Override
    public void removeReponse(Long reponseId) {
        reponseRepository.deleteById(reponseId);
    }

    @Override
    public List<Reponse> getReponsesByReclamation(Long reclamationId) {
        return reponseRepository.findAll()
                .stream()
                .filter(reponse -> reponse.getReclamation().getIdReclamation().equals(reclamationId))
                .collect(Collectors.toList());
    }
}
