package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.CategoryDTO;
import com.wpCorp.dsCommerce.DTO.ProductDTO;
import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import com.wpCorp.dsCommerce.Repository.CategoryRepository;
import com.wpCorp.dsCommerce.Repository.ProductRepository;
import com.wpCorp.dsCommerce.Service.Exceptions.ProductNotFoundException;
import org.hibernate.boot.beanvalidation.IntegrationException;
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
    @Autowired
    private CategoryRepository categoryRepo;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable page) {
        Page<ProductEntity> rsl = productRepo.findAll(page);
        return rsl.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findByOne(Long id) {
        Optional<ProductEntity> prd = productRepo.findById(id);
        ProductEntity prdEnt = prd.orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return new ProductDTO(prdEnt);
    }

    @Transactional
    public ProductDTO insertProduct(ProductDTO dto) {
        try {
            ProductEntity prd = new ProductEntity();
            prd.setName(dto.getName());
            prd.setDescription(dto.getDescription());
            prd.setPrice(dto.getPrice());
            prd.setImgUrl(dto.getImgUrl());
            for (CategoryDTO cate : dto.getCategories()) {
                CategoryEntity cat = categoryRepo.getReferenceById(cate.getId());
                prd.getCategories().add(cat);
            }
            prd = productRepo.save(prd);
            return new ProductDTO(prd);
        } catch (IntegrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        if (!productRepo.existsById(id)) throw new ProductNotFoundException("Product not found");
        ProductEntity prd = productRepo.getReferenceById(id);
        copyDtoToProduct(dto, prd);
        prd = productRepo.save(prd);
        return new ProductDTO(prd);
    }





    @Transactional
    protected void copyDtoToProduct(ProductDTO dto, ProductEntity prd) {
        prd.setName(dto.getName());
        prd.setDescription(dto.getDescription());
        prd.setPrice(dto.getPrice());
        prd.setImgUrl(dto.getImgUrl());
        for (CategoryDTO cate : dto.getCategories()) {
            CategoryEntity cat = categoryRepo.getReferenceById(cate.getId());
            prd.getCategories().add(cat);
        }
    }


}
