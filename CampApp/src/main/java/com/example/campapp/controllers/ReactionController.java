package com.example.campapp.controllers;

import com.example.campapp.services.ReactionService;
import com.example.campapp.entities.Reaction;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Reaction")
public class ReactionController {
    ReactionService reactionService;
    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/getReaction/{idReaction}")
    public Reaction getReaction(@PathVariable int idReaction){
        return reactionService.retrieveReaction(idReaction);
    }

    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/getReaction")
    public List<Reaction> getAllReactions(){
        return reactionService.retrieveAllReactions();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addRea   ction")
    public Reaction addReaction(@RequestBody Reaction r){
        return reactionService.addReaction(r);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteReaction/{idReaction}")
    public void deleteReaction(@PathVariable int idReaction){
        reactionService.deleteReaction(idReaction);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/updateReaction")
    public Reaction updateCommentaire(@RequestBody Reaction c){
        return reactionService.updateReaction(c);
    }

    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/getReactionsLikeParPublication/{idFeedback}")
    public List<Reaction> getReactionsLikeParPublication(@PathVariable Long idFeedback)
    {
        return reactionService.getReactionsLikeParPublication(idFeedback);
    }

    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/getReactionsDisLikeParPublication/{idFeedback}")
    public List<Reaction> getReactionsDisLikeParPublication(@PathVariable Long idFeedback)
    {
        return reactionService.getReactionsDisLikeParPublication(idFeedback);
    }
}
