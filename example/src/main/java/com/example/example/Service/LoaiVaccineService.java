package com.example.example.Service;

import com.example.example.Entity.LoaiVaccine;
import com.example.example.Repository.LoaiVaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiVaccineService {
    @Autowired
    private LoaiVaccineRepository loaiVaccineRepository;

    public List<LoaiVaccine> getAllLoaiVaccine() {
        return loaiVaccineRepository.findAll();
    }

    public Optional<LoaiVaccine> getLoaiVaccineById(Long id) {
        return loaiVaccineRepository.findById(id);
    }

    public LoaiVaccine createLoaiVaccine(LoaiVaccine loaiVaccine) {
        return loaiVaccineRepository.save(loaiVaccine);
    }

    public LoaiVaccine updateLoaiVaccine(Long id, LoaiVaccine loaiVaccine) {
        loaiVaccine.setId(id);
        return loaiVaccineRepository.save(loaiVaccine);
    }

    public void deleteLoaiVaccine(Long id) {
        loaiVaccineRepository.deleteById(id);
    }
}
