package com.example.campapp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private LocalDate date;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="feedbacks")
    private List<Reaction> reactions;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    private CentreDeCamping centre;
}