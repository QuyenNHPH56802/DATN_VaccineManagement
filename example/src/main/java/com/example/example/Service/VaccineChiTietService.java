package com.example.example.Service;

import com.example.example.Entity.VaccineChiTiet;
import com.example.example.Repository.VaccineChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineChiTietService {
    @Autowired
    private VaccineChiTietRepository vaccineChiTietRepository;

    public List<VaccineChiTiet> getAllVaccineChiTiet() {
        return vaccineChiTietRepository.findAll();
    }

    public Optional<VaccineChiTiet> getVaccineChiTietById(Long id) {
        return vaccineChiTietRepository.findById(id);
    }

    public VaccineChiTiet createVaccineChiTiet(VaccineChiTiet vaccineChiTiet) {
        return vaccineChiTietRepository.save(vaccineChiTiet);
    }

    public VaccineChiTiet updateVaccineChiTiet(Long id, VaccineChiTiet vaccineChiTiet) {
        vaccineChiTiet.setId(id);
        return vaccineChiTietRepository.save(vaccineChiTiet);
    }

    public void deleteVaccineChiTiet(Long id) {
        vaccineChiTietRepository.deleteById(id);
    }
}
