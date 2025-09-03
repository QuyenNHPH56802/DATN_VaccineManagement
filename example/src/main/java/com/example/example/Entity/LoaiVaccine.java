package com.example.example.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoaiVaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Mã loại vaccine không được để trống")
    private String maLoaiVaccine;

    @NotBlank(message = "Mã loại vaccine không được để trống")
    @Size(max = 20)
    private String maLoaiVaccineValidate;

    @NotBlank(message = "Tên loại vaccine không được để trống")
    @Size(max = 100)
    private String tenLoaiVaccine;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean trangThai;}