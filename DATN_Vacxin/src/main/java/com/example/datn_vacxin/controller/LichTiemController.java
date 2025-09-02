package com.example.datn_vacxin.controller;

import com.example.datn_vacxin.entity.LichHenTiem;
import com.example.datn_vacxin.service.LichTiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lich-hen-tiem")
@CrossOrigin(origins = "*")
public class LichTiemController {
    
    @Autowired
    private LichTiemService lichTiemService;
    
    // Lấy tất cả lịch hẹn
    @GetMapping
    public ResponseEntity<List<LichHenTiem>> getAllLichHen() {
        try {
            List<LichHenTiem> lichHens = lichTiemService.getAllLichHen();
            return ResponseEntity.ok(lichHens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy lịch hẹn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<LichHenTiem> getLichHenById(@PathVariable Integer id) {
        try {
            Optional<LichHenTiem> lichHen = lichTiemService.getLichHenById(id);
            return lichHen.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Tạo lịch hẹn mới
    @PostMapping
    public ResponseEntity<?> createLichHen(@Valid @RequestBody LichHenTiem lichHen) {
        try {
            LichHenTiem createdLichHen = lichTiemService.createLichHen(lichHen);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLichHen);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
    
    // Cập nhật lịch hẹn
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLichHen(@PathVariable Integer id, @Valid @RequestBody LichHenTiem lichHenDetails) {
        try {
            LichHenTiem updatedLichHen = lichTiemService.updateLichHen(id, lichHenDetails);
            return ResponseEntity.ok(updatedLichHen);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
    
    // Xác nhận tiêm xong
    @PutMapping("/{id}/confirm")
    public ResponseEntity<?> confirmTiemXong(@PathVariable Integer id) {
        try {
            LichHenTiem lichHen = lichTiemService.confirmTiemXong(id);
            return ResponseEntity.ok(lichHen);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
    
    // Hủy lịch hẹn
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> huyLichHen(@PathVariable Integer id) {
        try {
            LichHenTiem lichHen = lichTiemService.huyLichHen(id);
            return ResponseEntity.ok(lichHen);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
    
    // Xóa lịch hẹn
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLichHen(@PathVariable Integer id) {
        try {
            lichTiemService.deleteLichHen(id);
            return ResponseEntity.ok().body("Xóa lịch hẹn thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
    
    // Lấy lịch hẹn theo khách hàng
    @GetMapping("/filter/customer/{khachHangId}")
    public ResponseEntity<List<LichHenTiem>> getLichHenByKhachHang(@PathVariable Integer khachHangId) {
        try {
            List<LichHenTiem> lichHens = lichTiemService.getLichHenByKhachHang(khachHangId);
            return ResponseEntity.ok(lichHens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy lịch hẹn theo vaccine
    @GetMapping("/filter/vaccine/{vaccineId}")
    public ResponseEntity<List<LichHenTiem>> getLichHenByVaccine(@PathVariable Integer vaccineId) {
        try {
            List<LichHenTiem> lichHens = lichTiemService.getLichHenByVaccine(vaccineId);
            return ResponseEntity.ok(lichHens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy lịch hẹn theo ca tiêm
    @GetMapping("/filter/session/{caTiemId}")
    public ResponseEntity<List<LichHenTiem>> getLichHenByCaTiem(@PathVariable Integer caTiemId) {
        try {
            List<LichHenTiem> lichHens = lichTiemService.getLichHenByCaTiem(caTiemId);
            return ResponseEntity.ok(lichHens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy lịch hẹn theo trạng thái
    @GetMapping("/filter/status/{trangThai}")
    public ResponseEntity<List<LichHenTiem>> getLichHenByTrangThai(@PathVariable String trangThai) {
        try {
            LichHenTiem.TrangThai trangThaiEnum = LichHenTiem.TrangThai.valueOf(trangThai);
            List<LichHenTiem> lichHens = lichTiemService.getLichHenByTrangThai(trangThaiEnum);
            return ResponseEntity.ok(lichHens);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy lịch hẹn theo ngày tiêm
    @GetMapping("/filter/date")
    public ResponseEntity<List<LichHenTiem>> getLichHenByNgayTiem(@RequestParam String ngayTiem) {
        try {
            LocalDate date = LocalDate.parse(ngayTiem);
            List<LichHenTiem> lichHens = lichTiemService.getLichHenByNgayTiem(date);
            return ResponseEntity.ok(lichHens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy lịch hẹn trong khoảng thời gian
    @GetMapping("/filter/date-range")
    public ResponseEntity<List<LichHenTiem>> getLichHenTrongKhoangThoiGian(
            @RequestParam String startDate, 
            @RequestParam String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            List<LichHenTiem> lichHens = lichTiemService.getLichHenTrongKhoangThoiGian(start, end);
            return ResponseEntity.ok(lichHens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy lịch hẹn sắp tới
    @GetMapping("/filter/upcoming")
    public ResponseEntity<List<LichHenTiem>> getLichHenSapToi() {
        try {
            List<LichHenTiem> lichHens = lichTiemService.getLichHenSapToi();
            return ResponseEntity.ok(lichHens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy lịch hẹn quá hạn
    @GetMapping("/filter/overdue")
    public ResponseEntity<List<LichHenTiem>> getLichHenQuaHan() {
        try {
            List<LichHenTiem> lichHens = lichTiemService.getLichHenQuaHan();
            return ResponseEntity.ok(lichHens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Thống kê lịch hẹn theo trạng thái
    @GetMapping("/statistics/status")
    public ResponseEntity<List<Object[]>> thongKeLichHenTheoTrangThai() {
        try {
            List<Object[]> statistics = lichTiemService.thongKeLichHenTheoTrangThai();
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Thống kê lịch hẹn theo vaccine
    @GetMapping("/statistics/vaccine")
    public ResponseEntity<List<Object[]>> thongKeLichHenTheoVaccine() {
        try {
            List<Object[]> statistics = lichTiemService.thongKeLichHenTheoVaccine();
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Thống kê lịch hẹn theo tháng
    @GetMapping("/statistics/monthly")
    public ResponseEntity<List<Object[]>> thongKeLichHenTheoThang(@RequestParam int year) {
        try {
            List<Object[]> statistics = lichTiemService.thongKeLichHenTheoThang(year);
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
