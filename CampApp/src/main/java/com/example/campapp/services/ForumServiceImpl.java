package com.example.campapp.services;

import com.example.campapp.entities.Forum;
import com.example.campapp.repositories.ForumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ForumServiceImpl implements IForumService {
    private final ForumRepository forumRepository;

    @Override
    public Forum addForum(Forum forum) {
        return forumRepository.save(forum);
    }

    @Override
    public void removeForum(Long forumId) {
        forumRepository.deleteById(forumId);
    }

    @Override
    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    @Override
    public Forum modifyForum(Forum forum) {
        return forumRepository.save(forum);
    }

    @Override
    public Optional<Forum> getForumById(Long id) {
        return forumRepository.findById(id);
    }
}
