package com.example.example.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên vaccine không được để trống")
    @Size(max = 100)
    private String tenVaccine;

    @NotBlank(message = "Nhà sản xuất không được để trống")
    @Size(max = 100)
    private String nhaSanXuat;

    @ManyToOne
    @JoinColumn(name = "loai_vaccine_id")
    @NotNull(message = "Loại vaccine không được để trống")
    private LoaiVaccine loaiVaccine;
}
