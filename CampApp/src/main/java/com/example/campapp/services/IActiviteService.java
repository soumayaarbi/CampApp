package com.example.campapp.services;

import com.example.campapp.entities.Activite;
import com.example.campapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface IActiviteService {
    Activite addActivite(Activite a);
    void removeActivite(Long activiteId);
    Activite getActiviteById(Long id);
    List<Activite> getAllActivites();
    Activite modifyActivite(Activite activite);
    Optional<User> getUserByUsername(String username);
}
