package com.example.demo.repository;

import com.example.demo.domain.entity.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntity, Long> {
    Optional<ToolEntity> findByToolCode(String toolCode);
}
