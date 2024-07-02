package com.example.campapp.services;

import com.example.campapp.entities.Forum;

import java.util.List;
import java.util.Optional;

public interface IForumService {
    Forum addForum(Forum forum);
    void removeForum(Long forumId);
    List<Forum> getAllForums();
    Forum modifyForum(Forum forum);
    Optional<Forum> getForumById(Long id);
}
