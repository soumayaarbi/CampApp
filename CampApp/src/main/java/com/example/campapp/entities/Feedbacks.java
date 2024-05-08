package com.example.campapp.entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Feedbacks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFeedback; // Clé primaire
    private String commentaire;
    private int note;
    private Date date;


}