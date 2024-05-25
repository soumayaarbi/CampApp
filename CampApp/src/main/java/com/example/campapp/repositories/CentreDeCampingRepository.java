package com.example.campapp.repositories;

import com.example.campapp.entities.CentreDeCamping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentreDeCampingRepository extends JpaRepository<CentreDeCamping, Long> {
    List<CentreDeCamping> findByLieu(String lieu);
}
