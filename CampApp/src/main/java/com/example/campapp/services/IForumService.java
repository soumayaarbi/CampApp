package com.example.campapp.services;

import com.example.campapp.entities.Forum;

import java.util.List;

public interface IForumService {


    public Forum ajouterForum(Forum f);
    public List<Forum> getAllForums();
    public void supprimerForum(int id);
    public Forum modifierForum(Forum f) ;
}
