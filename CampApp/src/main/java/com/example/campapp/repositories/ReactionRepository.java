package com.example.campapp.repositories;

import com.example.campapp.entities.Reaction;
import com.example.campapp.entities.TypeReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
    public Reaction findByUtilisateurIdUtilisateurAndFeedbacksIdFeedback(Long idUtilisateur,Long idFeedback) ;
    public List<Reaction> findByReactionTypeAndFeedbacksIdFeedback(TypeReaction reactionType, Long idFeedback);
}
