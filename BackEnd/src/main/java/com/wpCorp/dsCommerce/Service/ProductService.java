package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.ProductDTO;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import com.wpCorp.dsCommerce.Repository.ProductRepository;
import com.wpCorp.dsCommerce.Service.Exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Transactional(readOnly = true)
    protected Page<ProductDTO> findAllPagead(Pageable page) {
        Page<ProductEntity> rsl = productRepo.findAllPagead(page);
        return rsl.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findByOne(Long id) {
        Optional<ProductEntity> prd = productRepo.findById(id);
        ProductEntity prIitem = prd.orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return new ProductDTO(prIitem);
    }


}
