package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.ProductDTO;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import com.wpCorp.dsCommerce.Repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepo;
    @Mock
    private PageImpl<ProductEntity> page;
    @Mock
    private PageImpl<ProductDTO> pageDTO;
    @Mock
    private ProductEntity prd;


    @BeforeEach
    void setUp() {
        prd = Factory.createProd();
        page = new PageImpl<>(List.of(prd));
        ProductService servSPY = Mockito.spy(productService);

        Mockito.when(productRepo.findAllPagead((Pageable) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(servSPY.findAllPaged((Pageable) ArgumentMatchers.any()))
                .thenReturn(pageDTO);
    }

    @Test
    void tPageProductWhenFindAllPageade() {
        //ARRANGE
        Pageable pg = PageRequest.of(1, 10);
        ProductService servSPY = Mockito.spy(productService);
        //ACT
        Page<ProductDTO> pgDTO = servSPY.findAllPaged(pg);

        //ASSERTIONS
        Assertions.assertNotNull(pgDTO);
    }
}