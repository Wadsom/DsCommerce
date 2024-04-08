package com.wpCorp.dsCommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_orders")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;
    @OneToOne(mappedBy = "payment_id", cascade = CascadeType.ALL)
    private PaymentEntity payment;


}
