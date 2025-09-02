package com.example.datn_vacxin.repository;

import com.example.datn_vacxin.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
}

