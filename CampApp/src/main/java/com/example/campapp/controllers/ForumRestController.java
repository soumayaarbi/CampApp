package com.example.campapp.controllers;

import com.example.campapp.services.IForumService;
import com.example.campapp.entities.Forum;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Forum")
public class ForumRestController {
    IForumService forumService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/retrieve-all-forums")
    public List<Forum> getForums() {
        return forumService.getAllForums();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add-forum")
    public Forum addForum(@RequestBody Forum forum) {
        return forumService.ajouterForum(forum);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/remove-forum/{id}")
    public void removeForum(@PathVariable("id") int id) {
        forumService.supprimerForum(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/modify-forum")
    public Forum modifyForum(@RequestBody Forum forum) {
        return forumService.modifierForum(forum);
    }
}
