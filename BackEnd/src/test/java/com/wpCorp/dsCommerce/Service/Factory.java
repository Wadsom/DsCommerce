package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.ProductDTO;
import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Entity.ProductEntity;

import java.util.HashSet;
import java.util.Set;

public class Factory {

    public static ProductEntity createProd() {
        Set<CategoryEntity> categoryEntities = new HashSet<>();
        categoryEntities.add(new CategoryEntity(1L, "Eletronicos"));
        ProductEntity prd;
        prd = new ProductEntity(1L, "GameStation", "YUDI e PRICILLA",
                200.0, "http://okokk.com", categoryEntities);
        return prd;
    }

    public static ProductDTO createProductDTO() {
        ProductEntity product = createProd();
        return new ProductDTO(product);
    }


}
