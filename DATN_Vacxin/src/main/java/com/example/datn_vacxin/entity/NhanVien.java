package com.example.datn_vacxin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Entity
@Table(name = "NhanVien")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NhanVien {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdNhanVien")
    private Integer idNhanVien;
    
    @NotBlank(message = "Họ tên không được để trống")
    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;
    
    @Column(name = "GioiTinh", length = 10)
    private String gioiTinh;
    
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Số điện thoại không hợp lệ")
    @Column(name = "SoDienThoai", length = 15)
    private String soDienThoai;
    
    @Email(message = "Email không hợp lệ")
    @Column(name = "Email", length = 100)
    private String email;
    
    @Column(name = "ChucDanh", length = 100)
    private String chucDanh;
    
    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CaTiem> caTiems;
    
    // Constructors
    public NhanVien() {}
    
    public NhanVien(String hoTen, String gioiTinh, String soDienThoai, 
                    String email, String chucDanh) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.chucDanh = chucDanh;
    }
    
    // Getters and Setters
    public Integer getIdNhanVien() {
        return idNhanVien;
    }
    
    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien = idNhanVien;
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
    
    public String getChucDanh() {
        return chucDanh;
    }
    
    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }
    
    public List<CaTiem> getCaTiems() {
        return caTiems;
    }
    
    public void setCaTiems(List<CaTiem> caTiems) {
        this.caTiems = caTiems;
    }
    

}

