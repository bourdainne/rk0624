package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tool")
public class ToolEntity extends BaseEntity {

    @Column(name = "tool_code", length = 10, nullable = false, unique = true)
    private String toolCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_details_id", insertable = true, updatable = true, nullable = false)
    private ToolDetailsEntity toolDetails;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", insertable = true, updatable = true, nullable = false)
    private BrandEntity brand;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ToolEntity))
            return false;

        ToolEntity other = (ToolEntity) o;

        return getId() != null &&
                getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
