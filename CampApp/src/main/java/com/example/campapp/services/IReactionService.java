package com.example.campapp.services;

import com.example.campapp.entities.Reaction;

import java.util.List;

public interface IReactionService {
    public Reaction addReaction(Reaction r);
    public List<Reaction> retrieveAllReactions();
    public Reaction retrieveReaction(int idReaction);
    public void deleteReaction(int idReaction);
    public Reaction updateReaction(Reaction r);
    public List<Reaction> getReactionsLikeParPublication(Long idFeedback);
    public List<Reaction> getReactionsDisLikeParPublication(Long idFeedback);
}
