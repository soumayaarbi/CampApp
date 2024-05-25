package com.example.campapp.services;

import com.example.campapp.entities.CentreDeCamping;
import com.example.campapp.repositories.CentreDeCampingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentreDeCampingService {
    @Autowired
    private CentreDeCampingRepository centreDeCampingRepository;

    public CentreDeCamping findById(Long id) {
        return centreDeCampingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Centre de camping not found with id: " + id));
    }
    public CentreDeCamping createCentreDeCamping(CentreDeCamping centreDeCamping) {
        return centreDeCampingRepository.save(centreDeCamping);
    }

    public List<CentreDeCamping> getAllCentresDeCamping() {
        return centreDeCampingRepository.findAll();
    }

    public CentreDeCamping getCentreDeCampingById(Long id) {
        return centreDeCampingRepository.findById(id).orElse(null);
    }

    public CentreDeCamping updateCentreDeCamping(Long id, CentreDeCamping updatedCentreDeCamping) {
        CentreDeCamping centreDeCamping = centreDeCampingRepository.findById(id).orElse(null);
        if (centreDeCamping != null) {
            updatedCentreDeCamping.setIdCentre(id);
            return centreDeCampingRepository.save(updatedCentreDeCamping);
        }
        return null;
    }

    public boolean deleteCentreDeCamping(Long id) {
        CentreDeCamping centreDeCamping = centreDeCampingRepository.findById(id).orElse(null);
        if (centreDeCamping != null) {
            centreDeCampingRepository.delete(centreDeCamping);
            return true;
        }
        return false;
    }
    public List<String> getAllLieux() {
        return centreDeCampingRepository.findAll()
                .stream()
                .map(CentreDeCamping::getLieu)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<CentreDeCamping> findCentresDeCampingByLieu(String lieu) {
        return centreDeCampingRepository.findByLieu(lieu);
    }
}
