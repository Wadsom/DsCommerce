package com.wpCorp.dsCommerce.Entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "tb_order_product")
@NoArgsConstructor
@Getter
@Setter
public class OrderItemEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @EmbeddedId
    private OrderItemPK id;
    private Integer quantity;
    private Double price;

    public OrderItemEntity(OrderEntity orderItem, ProductEntity prd, Integer quantity, Double price) {
        id.setOrder(orderItem);
        id.setProduct(prd);
        this.quantity = quantity;
        this.price = price;
    }

    public ProductEntity getProducts() {
        return id.getProduct();
    }

    public OrderEntity getOrders() {
        return id.getOrder();
    }

    public void setProduct(ProductEntity entity) {
        id.setProduct(entity);
    }

    public void setOrder(OrderEntity entity) {
        id.setOrder(entity);
    }


}
