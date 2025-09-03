package com.example.example.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

@Entity
public class VaccineChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Loại vaccine không được để trống")
    private String loaiVaccine;

    @NotBlank(message = "Mã vaccine chi tiết không được để trống")
    @Size(max = 20)
    private String maVaccineChiTiet;

    @Size(max = 100)
    private String xuatXu;

    @Size(max = 50)
    private String loSanXuat;

    @NotBlank(message = "Ngày sản xuất không được để trống")
    private String ngaySanXuat;

    @NotBlank(message = "Ngày hết hạn không được để trống")
    private String ngayHetHan;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải >= 0")
    private Integer soLuong;

    @NotNull(message = "Giá tiền không được để trống")
    @Min(value = 0, message = "Giá tiền phải >= 0")
    private Integer giaTien;

    @NotNull(message = "Giá nhập không được để trống")
    @Min(value = 0, message = "Giá nhập phải >= 0")
    private Integer giaNhap;

    @Size(max = 255)
    private String moTa;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    @NotNull(message = "Vaccine không được để trống")
    private Vaccine vaccine;
        public VaccineChiTiet() {
        }

        public VaccineChiTiet(String loaiVaccine, String maVaccineChiTiet, String xuatXu, String loSanXuat, String ngaySanXuat, String ngayHetHan, Integer soLuong, Integer giaTien, Integer giaNhap, String moTa, Boolean trangThai, Vaccine vaccine) {
            this.loaiVaccine = loaiVaccine;
            this.maVaccineChiTiet = maVaccineChiTiet;
            this.xuatXu = xuatXu;
            this.loSanXuat = loSanXuat;
            this.ngaySanXuat = ngaySanXuat;
            this.ngayHetHan = ngayHetHan;
            this.soLuong = soLuong;
            this.giaTien = giaTien;
            this.giaNhap = giaNhap;
            this.moTa = moTa;
            this.trangThai = trangThai;
            this.vaccine = vaccine;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLoaiVaccine() {
            return loaiVaccine;
        }

        public void setLoaiVaccine(String loaiVaccine) {
            this.loaiVaccine = loaiVaccine;
        }

        public String getMaVaccineChiTiet() {
            return maVaccineChiTiet;
        }

        public void setMaVaccineChiTiet(String maVaccineChiTiet) {
            this.maVaccineChiTiet = maVaccineChiTiet;
        }

        public String getXuatXu() {
            return xuatXu;
        }

        public void setXuatXu(String xuatXu) {
            this.xuatXu = xuatXu;
        }

        public String getLoSanXuat() {
            return loSanXuat;
        }

        public void setLoSanXuat(String loSanXuat) {
            this.loSanXuat = loSanXuat;
        }

        public String getNgaySanXuat() {
            return ngaySanXuat;
        }

        public void setNgaySanXuat(String ngaySanXuat) {
            this.ngaySanXuat = ngaySanXuat;
        }

        public String getNgayHetHan() {
            return ngayHetHan;
        }

        public void setNgayHetHan(String ngayHetHan) {
            this.ngayHetHan = ngayHetHan;
        }

        public Integer getSoLuong() {
            return soLuong;
        }

        public void setSoLuong(Integer soLuong) {
            this.soLuong = soLuong;
        }

        public Integer getGiaTien() {
            return giaTien;
        }

        public void setGiaTien(Integer giaTien) {
            this.giaTien = giaTien;
        }

        public Integer getGiaNhap() {
            return giaNhap;
        }

        public void setGiaNhap(Integer giaNhap) {
            this.giaNhap = giaNhap;
        }

        public String getMoTa() {
            return moTa;
        }

        public void setMoTa(String moTa) {
            this.moTa = moTa;
        }

        public Boolean getTrangThai() {
            return trangThai;
        }

        public void setTrangThai(Boolean trangThai) {
            this.trangThai = trangThai;
        }

        public Vaccine getVaccine() {
            return vaccine;
        }

        public void setVaccine(Vaccine vaccine) {
            this.vaccine = vaccine;
        }
}
