package com.example.campapp.services;

import com.example.campapp.entities.Activite;
import com.example.campapp.entities.User;
import com.example.campapp.repositories.ActiviteRepository;
import com.example.campapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ActiviteServiceImpl implements IActiviteService {
    private final ActiviteRepository activiteRepository;
    private final UserRepository userRepository;

    @Override
    public Activite addActivite(Activite a) {
        return activiteRepository.save(a);
    }

    @Override
    public void removeActivite(Long activiteId) {
        activiteRepository.deleteById(activiteId);
    }

    @Override
    public Activite getActiviteById(Long id) {
        return activiteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Activite> getAllActivites() {
        return activiteRepository.findAllActivites();
    }

    @Override
    public Activite modifyActivite(Activite activite) {
        return activiteRepository.save(activite);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
