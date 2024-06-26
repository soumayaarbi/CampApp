package com.example.campapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReact;

    @Enumerated(EnumType.STRING)
    private TypeReaction reactionType;

    @ManyToOne
    private Feedbacks feedbacks;


    @ManyToOne
    private Utilisateur utilisateur ;
}
