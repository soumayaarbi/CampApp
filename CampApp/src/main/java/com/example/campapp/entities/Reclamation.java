package com.example.campapp.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamation; // Clé primaire
    private String sujet;
    private Date date;
    private String description ;
    @ManyToOne
//  @JsonManagedReference
    @JsonIgnore
  //@JsonBackReference
    private User user;

    @OneToMany(mappedBy = "reclamation")
    private List<Reponse> reponses;

}