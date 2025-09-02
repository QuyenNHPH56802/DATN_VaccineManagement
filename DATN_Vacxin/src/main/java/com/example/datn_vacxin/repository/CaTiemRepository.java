package com.example.datn_vacxin.repository;

import com.example.datn_vacxin.entity.CaTiem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CaTiemRepository extends JpaRepository<CaTiem, Integer> {
    

}

