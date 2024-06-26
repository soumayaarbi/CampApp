package com.example.campapp.services;

import com.example.campapp.entities.Feedbacks;
import com.example.campapp.entities.Reaction;
import com.example.campapp.entities.TypeReaction;
import com.example.campapp.entities.Utilisateur;
import com.example.campapp.repositories.FeedbacksRepository;
import com.example.campapp.repositories.ReactionRepository;
import com.example.campapp.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReactionService implements IReactionService {
    UtilisateurRepository userRepository;
    ReactionRepository reactionRepository;
    FeedbacksRepository feedbacksRepository ;
    @Override
    public Reaction addReaction(Reaction r) {
        Reaction react = reactionRepository.findByUtilisateurIdUtilisateurAndFeedbacksIdFeedback(
                r.getUtilisateur().getIdUtilisateur(),
                r.getFeedbacks().getIdFeedback());
        Utilisateur user = userRepository.findById(r.getUtilisateur().getIdUtilisateur()).get();
        r.setUtilisateur(user);
        Feedbacks feedbacks = feedbacksRepository.findById(r.getFeedbacks().getIdFeedback()).get();
        r.setFeedbacks(feedbacks);
        if (react != null){

            if(react.getReactionType() == r.getReactionType() )
            {
                react.setReactionType(null);
            }
            else if ((react.getReactionType() == TypeReaction.LIKE || react.getReactionType() == null) && r.getReactionType() == TypeReaction.DISLIKE )
            {
                react.setReactionType(TypeReaction.DISLIKE);
            }
            else if ((react.getReactionType() == TypeReaction.DISLIKE || react.getReactionType() == null) && r.getReactionType() == TypeReaction.LIKE )
            {
                react.setReactionType(TypeReaction.LIKE);
            }
            return reactionRepository.save(react);
        } else {
            return reactionRepository.save(r);}
    }

    @Override
    public List<Reaction> retrieveAllReactions() {
        return reactionRepository.findAll();
    }

    @Override
    public Reaction retrieveReaction(int idReaction) {
        return reactionRepository.findById(idReaction).get();
    }

    @Override
    public void deleteReaction(int idReaction) {
        reactionRepository.deleteById(idReaction);
    }

    @Override
    public Reaction updateReaction(Reaction r) {
        return reactionRepository.save(r);
    }

    @Override
    public List<Reaction> getReactionsLikeParPublication(Long idFeedback) {
        return reactionRepository.findByReactionTypeAndFeedbacksIdFeedback(TypeReaction.LIKE, idFeedback);
    }

    @Override
    public List<Reaction> getReactionsDisLikeParPublication(Long idFeedback) {
        return reactionRepository.findByReactionTypeAndFeedbacksIdFeedback(TypeReaction.DISLIKE,  idFeedback);
    }
}
