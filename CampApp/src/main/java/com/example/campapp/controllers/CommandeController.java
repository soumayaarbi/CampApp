package com.example.campapp.controllers;

import com.example.campapp.entities.Commande;
import com.example.campapp.entities.Produit;
import com.example.campapp.repositories.ProduitRepo;
import com.example.campapp.services.CommandeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/commande")
public class CommandeController {
    @Autowired
    private final CommandeService commandeService;
    private final ProduitRepo produitRepository;


    @PostMapping("/add-commande")
    public Commande addCommande (@RequestBody Commande c){
       return commandeService.saveCommande(c);

    }

    @GetMapping("/AllCommandes")
    public List<Commande> getAllCommandes()
    {
        List<Commande> lists = commandeService.retrieveAllCommande();
        return  lists;
    }
    @GetMapping("/getCommandes/{commande-id}")
    public Optional<Commande> getCommandes(@PathVariable("commande-id") Long commandeId)
    {
     return commandeService.retrieveCommandeById(commandeId);

    }

    @DeleteMapping("/remove-commande/{commande-id}")
    public void removeCommande(@PathVariable("commande-id") Long commandeId) {
        commandeService.deleteCommandeById(commandeId);
    }
    @PutMapping("/update-commande")
    public Commande updateCommande (@RequestBody Commande c){
        return commandeService.updateCommande(c);

    }

    @PostMapping("/convertir/{panierId}")
    public ResponseEntity<Commande> convertirPanierEnCommande(@PathVariable Long panierId) {
        try {
            Commande commande = commandeService.convertirPanierEnCommande(panierId);
            return ResponseEntity.ok(commande);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{idCommande}")
    public ResponseEntity<Commande> obtenirCommandeParId(@PathVariable Long idCommande) {
        Commande commande = commandeService.obtenirCommandeParId(idCommande);
        if (commande == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(commande);
    }

    @Operation(summary = "Télécharger une commande en PDF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fichier PDF généré"),
            @ApiResponse(responseCode = "404", description = "Commande non trouvée")
    })
    @GetMapping("/download/{idCommande}")
    public ResponseEntity<InputStreamResource> telechargerCommandePdf(@PathVariable Long idCommande) throws IOException {
        ByteArrayInputStream bis = commandeService.genererPdfCommande(idCommande);

        if (bis == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=commande_" + idCommande + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}


