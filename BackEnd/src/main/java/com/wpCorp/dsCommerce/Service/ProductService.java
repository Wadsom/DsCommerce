package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.ProductDTO;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import com.wpCorp.dsCommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Transactional(readOnly = true)
    protected Page<ProductDTO> findAllPagead(Pageable page) {
        Page<ProductEntity> rsl = productRepo.findAllPagead(page);
        return rsl.map(ProductDTO::new);
    }
}
