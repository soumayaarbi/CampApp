package com.example.campapp.services;

import com.example.campapp.entities.Boutique;

import java.util.List;
import java.util.Optional;

public interface IBoutiqueService {


    Boutique createBoutique(Boutique boutique);
    Boutique updateBoutique(Long id, Boutique boutique);
    void deleteBoutique(Long id);
    Boutique getBoutiqueById(Long id);
    List<Boutique> getAllBoutiques();
    Boutique getBoutiqueByNomBoutique(String nom);




}
