package com.example.campapp.controllers;

import com.example.campapp.entities.CentreDeCamping;
import com.example.campapp.entities.Feedbacks;
import com.example.campapp.services.ICentreDeCamping;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/Centre")
public class CentreRestController {

    ICentreDeCamping iCentreDeCamping ;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/retrieve-all-centres")
    public List<CentreDeCamping> getCentres() {
        return iCentreDeCamping.retrieveAllCentre();
    }
}
