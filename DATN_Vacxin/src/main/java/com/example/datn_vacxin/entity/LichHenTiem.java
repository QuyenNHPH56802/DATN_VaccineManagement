package com.example.datn_vacxin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "LichHenTiem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LichHenTiem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLichHen")
    private Integer idLichHen;
    
    @NotNull(message = "Khách hàng không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKhachHang", nullable = false)
    private KhachHang khachHang;
    
    @NotNull(message = "Vaccine không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdVaccine", nullable = false)
    private Vaccine vaccine;
    
    @NotNull(message = "Ca tiêm không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCaTiem", nullable = false)
    private CaTiem caTiem;
    
    @NotNull(message = "Ngày tiêm không được để trống")
    @Column(name = "NgayTiem", nullable = false)
    private LocalDate ngayTiem;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThai trangThai = TrangThai.DaDatLich;
    
    @OneToMany(mappedBy = "lichHen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TheoDoiSauTiem> theoDoiSauTiems;
    
    // Constructors
    public LichHenTiem() {}
    
    public LichHenTiem(KhachHang khachHang, Vaccine vaccine, CaTiem caTiem, 
                       LocalDate ngayTiem, TrangThai trangThai) {
        this.khachHang = khachHang;
        this.vaccine = vaccine;
        this.caTiem = caTiem;
        this.ngayTiem = ngayTiem;
        this.trangThai = trangThai;
    }
    
    // Getters and Setters
    public Integer getIdLichHen() {
        return idLichHen;
    }
    
    public void setIdLichHen(Integer idLichHen) {
        this.idLichHen = idLichHen;
    }
    
    public KhachHang getKhachHang() {
        return khachHang;
    }
    
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
    
    public Vaccine getVaccine() {
        return vaccine;
    }
    
    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
    
    public CaTiem getCaTiem() {
        return caTiem;
    }
    
    public void setCaTiem(CaTiem caTiem) {
        this.caTiem = caTiem;
    }
    
    public LocalDate getNgayTiem() {
        return ngayTiem;
    }
    
    public void setNgayTiem(LocalDate ngayTiem) {
        this.ngayTiem = ngayTiem;
    }
    
    public TrangThai getTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }
    
    public List<TheoDoiSauTiem> getTheoDoiSauTiems() {
        return theoDoiSauTiems;
    }
    
    public void setTheoDoiSauTiems(List<TheoDoiSauTiem> theoDoiSauTiems) {
        this.theoDoiSauTiems = theoDoiSauTiems;
    }
    
    // Enum for TrangThai
    public enum TrangThai {
        DaDatLich("Đã đặt lịch"),
        DaTiem("Đã tiêm"),
        Huy("Hủy");
        
        private final String displayName;
        
        TrangThai(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}