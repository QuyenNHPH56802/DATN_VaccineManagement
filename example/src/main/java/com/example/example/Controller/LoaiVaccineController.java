package com.example.example.Controller;

import com.example.example.Entity.LoaiVaccine;
import com.example.example.Service.LoaiVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loaivaccine")
public class LoaiVaccineController {
    @Autowired
    private LoaiVaccineService loaiVaccineService;

    @GetMapping
    public List<LoaiVaccine> getAllLoaiVaccine() {
        return loaiVaccineService.getAllLoaiVaccine();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoaiVaccine> getLoaiVaccineById(@PathVariable Long id) {
        return loaiVaccineService.getLoaiVaccineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LoaiVaccine createLoaiVaccine(@Validated @RequestBody LoaiVaccine loaiVaccine) {
        return loaiVaccineService.createLoaiVaccine(loaiVaccine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoaiVaccine> updateLoaiVaccine(@PathVariable Long id, @Validated @RequestBody LoaiVaccine loaiVaccine) {
        LoaiVaccine updated = loaiVaccineService.updateLoaiVaccine(id, loaiVaccine);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiVaccine(@PathVariable Long id) {
        loaiVaccineService.deleteLoaiVaccine(id);
        return ResponseEntity.noContent().build();
    }
}
