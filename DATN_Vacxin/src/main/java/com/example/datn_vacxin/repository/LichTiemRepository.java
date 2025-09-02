package com.example.datn_vacxin.repository;

import com.example.datn_vacxin.entity.LichHenTiem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LichTiemRepository extends JpaRepository<LichHenTiem, Integer> {
    
    // Tìm lịch hẹn theo khách hàng
    List<LichHenTiem> findByKhachHangIdKhachHang(Integer khachHangId);
    
    // Tìm lịch hẹn theo vaccine
    List<LichHenTiem> findByVaccineIdVaccine(Integer vaccineId);
    
    // Tìm lịch hẹn theo ca tiêm
    List<LichHenTiem> findByCaTiemIdCaTiem(Integer caTiemId);
    
    // Tìm lịch hẹn theo trạng thái
    List<LichHenTiem> findByTrangThai(LichHenTiem.TrangThai trangThai);
    
    // Tìm lịch hẹn theo ngày tiêm
    List<LichHenTiem> findByNgayTiem(LocalDate ngayTiem);
    
    // Tìm lịch hẹn trong khoảng thời gian
    @Query("SELECT l FROM LichHenTiem l WHERE l.ngayTiem BETWEEN :startDate AND :endDate")
    List<LichHenTiem> findLichHenTrongKhoangThoiGian(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
    
    // Tìm lịch hẹn sắp tới (trong vòng 7 ngày)
    @Query("SELECT l FROM LichHenTiem l WHERE l.ngayTiem BETWEEN :today AND :nextWeek " +
           "AND l.trangThai = 'DaDatLich'")
    List<LichHenTiem> findLichHenSapToi(
            @Param("today") LocalDate today,
            @Param("nextWeek") LocalDate nextWeek);
    
    // Đếm số lượng lịch hẹn theo trạng thái
    @Query("SELECT l.trangThai, COUNT(l) FROM LichHenTiem l GROUP BY l.trangThai")
    List<Object[]> countByTrangThai();
    
    // Đếm số lượng lịch hẹn theo vaccine
    @Query("SELECT l.vaccine.tenVaccine, COUNT(l) FROM LichHenTiem l GROUP BY l.vaccine.tenVaccine")
    List<Object[]> countByVaccine();
    
    // Tìm lịch hẹn đã quá hạn (ngày tiêm đã qua nhưng chưa tiêm)
    @Query("SELECT l FROM LichHenTiem l WHERE l.ngayTiem < :today " +
           "AND l.trangThai = 'DaDatLich'")
    List<LichHenTiem> findLichHenQuaHan(@Param("today") LocalDate today);
    
    // Tìm lịch hẹn theo khách hàng và trạng thái
    List<LichHenTiem> findByKhachHangIdKhachHangAndTrangThai(Integer khachHangId, LichHenTiem.TrangThai trangThai);
    
    // Tìm lịch hẹn theo ca tiêm và trạng thái
    List<LichHenTiem> findByCaTiemIdCaTiemAndTrangThai(Integer caTiemId, LichHenTiem.TrangThai trangThai);
    
    // Tìm lịch hẹn theo khách hàng, ca tiêm và trạng thái
    List<LichHenTiem> findByKhachHangIdKhachHangAndCaTiemIdCaTiemAndTrangThai(Integer khachHangId, Integer caTiemId, LichHenTiem.TrangThai trangThai);
    
    // Thống kê lịch hẹn theo tháng
    @Query("SELECT MONTH(l.ngayTiem), COUNT(l) FROM LichHenTiem l " +
           "WHERE YEAR(l.ngayTiem) = :year GROUP BY MONTH(l.ngayTiem)")
    List<Object[]> thongKeLichHenTheoThang(@Param("year") int year);
}
