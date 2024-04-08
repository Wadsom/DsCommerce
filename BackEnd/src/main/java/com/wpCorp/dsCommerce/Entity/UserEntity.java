package com.wpCorp.dsCommerce.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate bithDate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "client")
    private Set<OrderEntity> orders = new HashSet<>();


    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
