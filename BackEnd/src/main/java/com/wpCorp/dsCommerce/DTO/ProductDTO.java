package com.wpCorp.dsCommerce.DTO;

import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        for (CategoryEntity cat : entity.getCategories()) {
            addCategories(new CategoryDTO(cat));
        }
    }

    public void addCategories(CategoryDTO cate) {
        categories.add(cate);
    }


}
