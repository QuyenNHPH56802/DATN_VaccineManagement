package com.example.example.Entity;

import lombok.Getter; 
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(max = 100, message = "Tên khách hàng tối đa 100 ký tự")
    private String tenKhachHang;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;

    @Size(max = 50)
    private String ngheNghiep;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 15)
    private String soDienThoai;

    @Size(max = 12)
    private String cccd;

    @Email(message = "Email không hợp lệ")
    private String email;

    private String lichSuTiemChung;

    @NotNull(message = "Giới tính không được để trống")
    private Boolean gioiTinh;

    @Size(max = 255)
    private String Mota;
}
