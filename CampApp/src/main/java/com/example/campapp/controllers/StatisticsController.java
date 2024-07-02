package com.example.campapp.controllers;

import com.example.campapp.services.FeedbackServiceImpl;
import com.example.campapp.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/stat")
public class StatisticsController {

    private final FeedbackServiceImpl feedbackService;
    private final ReservationService reservationService;


    @GetMapping("/statistics/{centreId}")
    public Map<String, Long> getStatisticsByCentreId(@PathVariable Long centreId) {
        Map<String, Long> stats = new HashMap<>();
        stats.put("feedbacks", feedbackService.countFeedbacksByCentreId(centreId));
        stats.put("reservations", reservationService.countReservationsByCentreId(centreId));

        return stats;
    }
}
