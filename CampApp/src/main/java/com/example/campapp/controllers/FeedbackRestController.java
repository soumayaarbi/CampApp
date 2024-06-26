package com.example.campapp.controllers;

import com.example.campapp.services.IFeedbacksService;
import com.example.campapp.entities.Feedbacks;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Feedbacks")
public class FeedbackRestController {
    IFeedbacksService iFeedbacksService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/retrieve-all-feedbacks")
    public List<Feedbacks> getFeedbacks() {
        List<Feedbacks> listFeedbacks = iFeedbacksService.retrieveAllFeedbacks();
        return listFeedbacks;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add-feedbacks")
    public Feedbacks addFeedback(@RequestBody Feedbacks f) {
        return iFeedbacksService.addFeedbacks(f);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/remove-feedbacks/{idFeedback}")
    public void removefeedbacks(@PathVariable("idFeedback") Long feedid) {
        iFeedbacksService.removeFeedbacks(feedid);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/modify-feedbacks")
    public Feedbacks modifyFeedbacks(@RequestBody Feedbacks feedbacks) {
        Feedbacks modifiedfeedback = iFeedbacksService.modifyFeedbacks(feedbacks);
        return modifiedfeedback;
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getPublication/{idPublication}")
    public Feedbacks getPublication(@PathVariable long idPublication){
        return iFeedbacksService.retrievePublication(idPublication);
    }


    @GetMapping("getReponsesParPublication/{idCentre}")
    public List<Feedbacks> getReposesParPublication(@PathVariable Long idCentre){
        return iFeedbacksService.retrieveAllReponsesParPublication(idCentre);
    }
}
