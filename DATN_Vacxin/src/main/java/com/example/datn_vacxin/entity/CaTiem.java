package com.example.datn_vacxin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CaTiem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CaTiem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCaTiem")
    private Integer idCaTiem;
    
    @NotNull(message = "Nhân viên không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNhanVien", nullable = false)
    private NhanVien nhanVien;
    
    @NotNull(message = "Thời gian bắt đầu không được để trống")
    @Column(name = "ThoiGianBatDau")
    private LocalDateTime thoiGianBatDau;
    
    @NotNull(message = "Thời gian kết thúc không được để trống")
    @Column(name = "ThoiGianKetThuc")
    private LocalDateTime thoiGianKetThuc;
    
    @Column(name = "PhongTiem", length = 50)
    private String phongTiem;
    
    @OneToMany(mappedBy = "caTiem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LichHenTiem> lichHenTiems;
    
    // Constructors
    public CaTiem() {}
    
    public CaTiem(NhanVien nhanVien, LocalDateTime thoiGianBatDau, 
                  LocalDateTime thoiGianKetThuc, String phongTiem) {
        this.nhanVien = nhanVien;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.phongTiem = phongTiem;
    }
    
    // Getters and Setters
    public Integer getIdCaTiem() {
        return idCaTiem;
    }
    
    public void setIdCaTiem(Integer idCaTiem) {
        this.idCaTiem = idCaTiem;
    }
    
    public NhanVien getNhanVien() {
        return nhanVien;
    }
    
    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
    
    public LocalDateTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }
    
    public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }
    
    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }
    
    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }
    
    public String getPhongTiem() {
        return phongTiem;
    }
    
    public void setPhongTiem(String phongTiem) {
        this.phongTiem = phongTiem;
    }
    
    public List<LichHenTiem> getLichHenTiems() {
        return lichHenTiems;
    }
    
    public void setLichHenTiems(List<LichHenTiem> lichHenTiems) {
        this.lichHenTiems = lichHenTiems;
    }
}

