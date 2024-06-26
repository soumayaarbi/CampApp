package com.example.campapp.services;

import com.example.campapp.entities.CentreDeCamping;
import com.example.campapp.entities.Feedbacks;
import com.example.campapp.repositories.CentreDeCampingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CentreDeCampingService implements ICentreDeCamping {
    @Autowired
    private CentreDeCampingRepository centreDeCampingRepository;

    public CentreDeCamping findById(Long id) {
        return centreDeCampingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Centre de camping not found with id: " + id));
    }


    @Override
    public List<CentreDeCamping> retrieveAllCentre() {
        return centreDeCampingRepository.findAll();
    }



}
