package com.example.campapp.services;

import com.example.campapp.entities.Reclamation;
import com.example.campapp.entities.User;
import com.example.campapp.repositories.ReclamationRepository;
import com.example.campapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ReclamServiceImpl implements IReclamationService {
    private final ReclamationRepository reclamationRepository;
    private final UserRepository userRepository;

    @Override
    public Reclamation addReclamation(Reclamation r) {
        return reclamationRepository.save(r);
    }

    @Override
    public void removeReclamation(Long ReclamationId) {
        reclamationRepository.deleteById(ReclamationId);
    }



    @Override
    public List<Reclamation> getAllReclamation() {
        return reclamationRepository.findAllReclamations();
    }

    @Override
    public Reclamation modifyReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Map<String, Long> countReclamationsBySujet() {
        List<Reclamation> reclamations = reclamationRepository.findAll();
        return reclamations.stream()
                .collect(Collectors.groupingBy(Reclamation::getSujet, Collectors.counting()));
    }

    @Override
    public List<Reclamation> getReclamationsByDay(Date date) {
        return reclamationRepository.findByDate(date);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Map<String, Long> countReclamationsByMonth() {
        List<Reclamation> reclamations = reclamationRepository.findAll();
        Map<String, Long> countByMonth = new HashMap<>();

        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");

        for (Reclamation reclamation : reclamations) {
            String month = monthFormat.format(reclamation.getDate());
            countByMonth.put(month, countByMonth.getOrDefault(month, 0L) + 1);
        }

        return countByMonth;
    }

    @Override
    public List<Reclamation> getReclamationsByYear(int year) {
        Calendar calendar = Calendar.getInstance();
        return reclamationRepository.findAll().stream()
                .filter(reclamation -> {
                    calendar.setTime(reclamation.getDate());
                    return calendar.get(Calendar.YEAR) == year;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reclamation> getReclamationById(Long id) {
        return reclamationRepository.findById(id);
    }
}
