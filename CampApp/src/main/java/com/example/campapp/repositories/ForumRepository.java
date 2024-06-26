package com.example.campapp.repositories;

import com.example.campapp.entities.Forum;
import org.springframework.data.repository.CrudRepository;

public interface ForumRepository extends CrudRepository<Forum, Integer> {
}
