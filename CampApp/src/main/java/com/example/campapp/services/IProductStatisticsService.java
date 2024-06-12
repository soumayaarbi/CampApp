package com.example.campapp.services;

import com.example.campapp.dto.ProductStatisticsDTO;

import java.util.List;

public interface IProductStatisticsService {

    List<ProductStatisticsDTO> getTopSellingProducts();
}
