package com.example.campapp.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class ProductStatisticsDTO {
    private String nomProduit;
    private Long totalVendu;

    public ProductStatisticsDTO(String nomProduit, Long totalVendu) {
        this.nomProduit = nomProduit;
        this.totalVendu = totalVendu;
    }


}
