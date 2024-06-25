package com.example.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "brand")
public class BrandEntity extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BrandEntity))
            return false;

        BrandEntity other = (BrandEntity) o;

        return getId() != null &&
                getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
