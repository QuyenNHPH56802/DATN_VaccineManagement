package com.example.datn_vacxin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "KhachHang")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KhachHang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKhachHang")
    private Integer idKhachHang;
    
    @NotBlank(message = "Họ tên không được để trống")
    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;
    
    @Column(name = "GioiTinh", length = 10)
    private String gioiTinh;
    
    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;
    
    @Column(name = "NgheNghiep", length = 100)
    private String ngheNghiep;
    
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Số điện thoại không hợp lệ")
    @Column(name = "SoDienThoai", length = 15)
    private String soDienThoai;
    
    @Email(message = "Email không hợp lệ")
    @Column(name = "Email", length = 100)
    private String email;
    
    @Column(name = "DiaChi", length = 255)
    private String diaChi;
    
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LichHenTiem> lichHenTiems;
    
    // Constructors
    public KhachHang() {}
    
    public KhachHang(String hoTen, String gioiTinh, LocalDate ngaySinh, 
                     String ngheNghiep, String soDienThoai, String email, String diaChi) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.ngheNghiep = ngheNghiep;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
    }
    
    // Getters and Setters
    public Integer getIdKhachHang() {
        return idKhachHang;
    }
    
    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getGioiTinh() {
        return gioiTinh;
    }
    
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public LocalDate getNgaySinh() {
        return ngaySinh;
    }
    
    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    public String getNgheNghiep() {
        return ngheNghiep;
    }
    
    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public List<LichHenTiem> getLichHenTiems() {
        return lichHenTiems;
    }
    
    public void setLichHenTiems(List<LichHenTiem> lichHenTiems) {
        this.lichHenTiems = lichHenTiems;
    }
    

}

