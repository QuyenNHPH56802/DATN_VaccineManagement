package com.example.example.Service;

import com.example.example.Entity.Vaccine;
import com.example.example.Repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    @Autowired
    private VaccineRepository vaccineRepository;

    public List<Vaccine> getAllVaccine() {
        return vaccineRepository.findAll();
    }

    public Optional<Vaccine> getVaccineById(Long id) {
        return vaccineRepository.findById(id);
    }

    public Vaccine createVaccine(Vaccine vaccine) {
        return vaccineRepository.save(vaccine);
    }

    public Vaccine updateVaccine(Long id, Vaccine vaccine) {
        vaccine.setId(id);
        return vaccineRepository.save(vaccine);
    }

    public void deleteVaccine(Long id) {
        vaccineRepository.deleteById(id);
    }
}
