package com.example.campapp.services;

import com.example.campapp.entities.Forum;
import com.example.campapp.repositories.ForumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ForumService implements IForumService {
    ForumRepository forumRepository ;
    public Forum ajouterForum(Forum f) {
        return forumRepository.save(f);
    }

    public List<Forum> getAllForums() {
        return (List<Forum>) forumRepository.findAll();
    }
    public void supprimerForum(int id) {
        forumRepository.deleteById(id);
    }

    public Forum modifierForum(Forum f) {
        return forumRepository.save(f);
    }
}
