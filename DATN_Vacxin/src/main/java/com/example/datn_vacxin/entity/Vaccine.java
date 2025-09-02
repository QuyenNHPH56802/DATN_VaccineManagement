package com.example.datn_vacxin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Vaccine")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vaccine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVaccine")
    private Integer idVaccine;
    
    @NotBlank(message = "Tên vaccine không được để trống")
    @Column(name = "TenVaccine", nullable = false, length = 100)
    private String tenVaccine;
    
    @Column(name = "MoTa", length = 255)
    private String moTa;
    
    @Column(name = "HangSanXuat", length = 100)
    private String hangSanXuat;
    
    @NotNull(message = "Hạn sử dụng không được để trống")
    @Column(name = "HanSuDung")
    private LocalDate hanSuDung;
    
    @OneToMany(mappedBy = "vaccine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LichHenTiem> lichHenTiems;
    
    // Constructors
    public Vaccine() {}
    
    public Vaccine(String tenVaccine, String moTa, String hangSanXuat, LocalDate hanSuDung) {
        this.tenVaccine = tenVaccine;
        this.moTa = moTa;
        this.hangSanXuat = hangSanXuat;
        this.hanSuDung = hanSuDung;
    }
    
    // Getters and Setters
    public Integer getIdVaccine() {
        return idVaccine;
    }
    
    public void setIdVaccine(Integer idVaccine) {
        this.idVaccine = idVaccine;
    }
    
    public String getTenVaccine() {
        return tenVaccine;
    }
    
    public void setTenVaccine(String tenVaccine) {
        this.tenVaccine = tenVaccine;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public String getHangSanXuat() {
        return hangSanXuat;
    }
    
    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }
    
    public LocalDate getHanSuDung() {
        return hanSuDung;
    }
    
    public void setHanSuDung(LocalDate hanSuDung) {
        this.hanSuDung = hanSuDung;
    }
    
    public List<LichHenTiem> getLichHenTiems() {
        return lichHenTiems;
    }
    
    public void setLichHenTiems(List<LichHenTiem> lichHenTiems) {
        this.lichHenTiems = lichHenTiems;
    }
}

