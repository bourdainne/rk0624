package com.example.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tool_details")
public class ToolDetailsEntity extends BaseEntity {

    @Column(name = "tool_type", length = 50, nullable = false, unique = true)
    private String toolType;

    @Column(name = "daily_charge",precision=10, scale=2)
    private BigDecimal dailyCharge = BigDecimal.ZERO;

    @Column(name = "weekday_charge", nullable = false)
    private Boolean weekdayCharge;

    @Column(name = "weekend_charge", nullable = false)
    private Boolean weekendCharge;

    @Column(name = "holiday_charge", nullable = false)
    private Boolean holidayCharge;

    @Column(name = "currency")
    private String currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ToolDetailsEntity))
            return false;

        ToolDetailsEntity other = (ToolDetailsEntity) o;

        return getId() != null &&
                getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}

