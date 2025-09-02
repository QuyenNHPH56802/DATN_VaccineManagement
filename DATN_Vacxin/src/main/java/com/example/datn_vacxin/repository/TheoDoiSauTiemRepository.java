package com.example.datn_vacxin.repository;

import com.example.datn_vacxin.entity.TheoDoiSauTiem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TheoDoiSauTiemRepository extends JpaRepository<TheoDoiSauTiem, Integer> {
    
    // Tìm theo dõi theo lịch hẹn
    List<TheoDoiSauTiem> findByLichHenIdLichHen(Integer lichHenId);
    
    // Tìm theo dõi theo sự cố
    List<TheoDoiSauTiem> findBySuCo(TheoDoiSauTiem.SuCo suCo);
    
    // Tìm theo dõi trong khoảng thời gian
    @Query("SELECT t FROM TheoDoiSauTiem t WHERE t.thoiGianBatDau BETWEEN :startTime AND :endTime")
    List<TheoDoiSauTiem> findTheoDoiTrongKhoangThoiGian(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
    
    // Tìm theo dõi theo ngày
    @Query("SELECT t FROM TheoDoiSauTiem t WHERE DATE(t.thoiGianBatDau) = DATE(:date)")
    List<TheoDoiSauTiem> findTheoDoiTheoNgay(@Param("date") LocalDateTime date);
    
    // Tìm theo dõi có sự cố nặng
    @Query("SELECT t FROM TheoDoiSauTiem t WHERE t.suCo = 'PhanUngNang'")
    List<TheoDoiSauTiem> findTheoDoiCoSuCoNang();
    
    // Tìm theo dõi có sự cố nhẹ
    @Query("SELECT t FROM TheoDoiSauTiem t WHERE t.suCo = 'PhanUngNhe'")
    List<TheoDoiSauTiem> findTheoDoiCoSuCoNhe();
    
    // Tìm theo dõi không có sự cố
    @Query("SELECT t FROM TheoDoiSauTiem t WHERE t.suCo = 'Khong'")
    List<TheoDoiSauTiem> findTheoDoiKhongCoSuCo();
    
    // Đếm số lượng theo dõi theo sự cố
    @Query("SELECT t.suCo, COUNT(t) FROM TheoDoiSauTiem t GROUP BY t.suCo")
    List<Object[]> countBySuCo();
    
    // Thống kê sự cố theo vaccine
    @Query("SELECT t.lichHen.vaccine.tenVaccine, t.suCo, COUNT(t) " +
           "FROM TheoDoiSauTiem t GROUP BY t.lichHen.vaccine.tenVaccine, t.suCo")
    List<Object[]> thongKeSuCoTheoVaccine();
    
    // Tìm theo dõi chưa kết thúc (chưa có thời gian kết thúc)
    @Query("SELECT t FROM TheoDoiSauTiem t WHERE t.thoiGianKetThuc IS NULL")
    List<TheoDoiSauTiem> findTheoDoiChuaKetThuc();
    
    // Tìm theo dõi theo lịch hẹn và sự cố
    List<TheoDoiSauTiem> findByLichHenIdLichHenAndSuCo(Integer lichHenId, TheoDoiSauTiem.SuCo suCo);
    
    // Thống kê sự cố theo tháng
    @Query("SELECT MONTH(t.thoiGianBatDau), t.suCo, COUNT(t) " +
           "FROM TheoDoiSauTiem t WHERE YEAR(t.thoiGianBatDau) = :year " +
           "GROUP BY MONTH(t.thoiGianBatDau), t.suCo")
    List<Object[]> thongKeSuCoTheoThang(@Param("year") int year);
}