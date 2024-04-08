package com.wpCorp.dsCommerce.Entity;

import jakarta.persistence.EmbeddedId;

public class OrderItemEntity {
    @EmbeddedId
    private OrderItemPK id;

    private Integer quantity;
    private Double price;


}
