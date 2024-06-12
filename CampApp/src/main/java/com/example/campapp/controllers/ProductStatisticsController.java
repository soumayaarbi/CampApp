package com.example.campapp.controllers;

import com.example.campapp.dto.ProductStatisticsDTO;
import com.example.campapp.services.ProductStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class ProductStatisticsController {

    private final ProductStatisticsService productStatisticsService;

    @Autowired
    public ProductStatisticsController(ProductStatisticsService productStatisticsService) {
        this.productStatisticsService = productStatisticsService;
    }

    @GetMapping("/top-selling-products")
    public List<ProductStatisticsDTO> getTopSellingProducts() {
        return productStatisticsService.getTopSellingProducts();
    }
}
