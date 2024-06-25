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
@Table(name = "holiday")
public class HolidayEntity extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "is_fixed", nullable = false)
    private Boolean isFixed;

    @Column(name = "day_of_month")
    private Integer dayOfMonth;

    @Column(name = "month_of_year")
    private Integer monthOfYear;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column(name = "earliest_day")
    private Integer earliestDay;

    @Column(name = "latest_day")
    private Integer latestDay;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof HolidayEntity))
            return false;

        HolidayEntity other = (HolidayEntity) o;

        return getId() != null &&
                getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
