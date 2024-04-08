package com.wpCorp.dsCommerce.DTO;

import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Campo Requerido")
    @Size(min = 3, max = 80, message = "Nome precisa ter entre 3 e 80 caracteres")
    private String name;
    @NotBlank(message = "Campo Requerido")
    @Size(min = 3, max = 80, message = "Descrição precisa ter no minimo 10 caracteres")
    private String description;
    @Positive(message = "O preço precisa ser positivo")
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
