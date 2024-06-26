package com.example.demo.repository;

import com.example.demo.domain.entity.HolidayEntity;
import com.example.demo.domain.entity.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayEntity, Long> {
    List<HolidayEntity> findAll();
}
