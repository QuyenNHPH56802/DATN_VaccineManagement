package com.example.datn_vacxin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@Entity
@Table(name = "TheoDoiSauTiem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TheoDoiSauTiem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTheoDoi")
    private Integer idTheoDoi;
    
    @NotNull(message = "Lịch hẹn không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdLichHen", nullable = false)
    private LichHenTiem lichHen;
    
    @NotNull(message = "Thời gian bắt đầu không được để trống")
    @Column(name = "ThoiGianBatDau")
    private LocalDateTime thoiGianBatDau;
    
    @Column(name = "ThoiGianKetThuc")
    private LocalDateTime thoiGianKetThuc;
    
    @Column(name = "MoTa", length = 255)
    private String moTa;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "SuCo")
    private SuCo suCo = SuCo.Khong;
    
    // Constructors
    public TheoDoiSauTiem() {}
    
    public TheoDoiSauTiem(LichHenTiem lichHen, LocalDateTime thoiGianBatDau, 
                          LocalDateTime thoiGianKetThuc, String moTa, SuCo suCo) {
        this.lichHen = lichHen;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.moTa = moTa;
        this.suCo = suCo;
    }
    
    // Getters and Setters
    public Integer getIdTheoDoi() {
        return idTheoDoi;
    }
    
    public void setIdTheoDoi(Integer idTheoDoi) {
        this.idTheoDoi = idTheoDoi;
    }
    
    public LichHenTiem getLichHen() {
        return lichHen;
    }
    
    public void setLichHen(LichHenTiem lichHen) {
        this.lichHen = lichHen;
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
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public SuCo getSuCo() {
        return suCo;
    }
    
    public void setSuCo(SuCo suCo) {
        this.suCo = suCo;
    }
    
    // Enum for SuCo
    public enum SuCo {
        Khong("Không"),
        PhanUngNhe("Phản ứng nhẹ"),
        PhanUngNang("Phản ứng nặng");
        
        private final String displayName;
        
        SuCo(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}

