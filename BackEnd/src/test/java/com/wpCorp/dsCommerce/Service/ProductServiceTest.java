package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.ProductDTO;
import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import com.wpCorp.dsCommerce.Repository.CategoryRepository;
import com.wpCorp.dsCommerce.Repository.ProductRepository;
import com.wpCorp.dsCommerce.Service.Exceptions.ProductNotFoundException;
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
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepo;
    @Mock
    private CategoryRepository categoryRepo;
    @Mock
    private PageImpl<ProductEntity> page;
    @Mock
    private PageImpl<ProductDTO> pageDTO;
    @Mock
    private ProductEntity prd;
    @Mock
    private ProductDTO prdDTO;
    @Mock
    private CategoryEntity category;
    private Long idNotExists;
    private Long idExists;
    private Long IdDepend;


    @BeforeEach
    void setUp() {
        idNotExists = 100L;
        prd = Factory.createProd();
        page = new PageImpl<>(List.of(prd));
        ProductService servSPY = Mockito.spy(productService);

        Mockito.when(productRepo.findAllPagead((Pageable) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(servSPY.findAllPagead((Pageable) ArgumentMatchers.any()))
                .thenReturn(pageDTO);
        Mockito.doThrow(ProductNotFoundException.class).when(productRepo).findById(idNotExists);
        Mockito.doThrow(ProductNotFoundException.class).when(servSPY).findByOne(idNotExists);
        Mockito.when(productRepo.findById(idExists)).thenReturn(Optional.of(prd));
        Mockito.when(servSPY.findByOne(idExists)).thenReturn(prdDTO);

        Mockito.when(productRepo.save(ArgumentMatchers.any())).thenReturn(prd);
        Mockito.when(categoryRepo.getReferenceById((Long) ArgumentMatchers.any())).thenReturn(category);
    }

    @Test
    void doNotThrowExceptionWhenInsertProductDtoValid() {
        //ARRANGE
        ProductDTO dto = Factory.createProductDTO();

        //ACT
        ProductService servSPY = Mockito.spy(productService);
        ProductDTO result = servSPY.insertProduct(dto);
        //ASSERTIONS
        Assertions.assertNotNull(result);
        Assertions.assertEquals(dto.getName(), result.getName());
        Assertions.assertEquals(dto.getId(), result.getId());
        Assertions.assertEquals(dto.getPrice(), result.getPrice());

    }

    @Test
    void tPageProductWhenFindAllPageade() {
        //ARRANGE
        Pageable pg = PageRequest.of(1, 10);
        ProductService servSPY = Mockito.spy(productService);
        //ACT
        Page<ProductDTO> pgDTO = servSPY.findAllPagead(pg);

        //ASSERTIONS
        Assertions.assertNotNull(pgDTO);
    }

    @Test
    void tDoThrowExceptionWhenIdNotExistsPassedInFindById() {
        //ARRANGE
        ProductService servSPY = Mockito.spy(productService);
        //ACT
        //ASSERTIONS
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productRepo.findById(idNotExists);
        });
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            servSPY.findByOne(idNotExists);
        });
    }

    @Test
    void tFindByOne() {
        //ARRANGE
        ProductService servSPY = Mockito.spy(productService);
        //ACT
        //ASSERTIONS
        Assertions.assertNotNull(servSPY.findByOne(idExists));
        Assertions.assertDoesNotThrow(() -> {
            servSPY.findByOne(idExists);
        });

    }
}