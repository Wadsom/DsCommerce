package com.wpCorp.dsCommerce.DTO;

import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO  {

    private Long id;
    private String name;

    public CategoryDTO(CategoryEntity categoryEntity) {
        this.id = categoryEntity.getId();
        this.name = categoryEntity.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
