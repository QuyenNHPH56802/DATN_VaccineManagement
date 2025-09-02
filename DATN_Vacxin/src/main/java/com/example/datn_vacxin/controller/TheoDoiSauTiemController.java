package com.example.datn_vacxin.controller;

import com.example.datn_vacxin.entity.TheoDoiSauTiem;
import com.example.datn_vacxin.service.TheoDoiSauTiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theo-doi-sau-tiem")
@CrossOrigin(origins = "*")
public class TheoDoiSauTiemController {
    
    @Autowired
    private TheoDoiSauTiemService theoDoiSauTiemService;
    
    // Lấy tất cả theo dõi sau tiêm
    @GetMapping
    public ResponseEntity<List<TheoDoiSauTiem>> getAllTheoDoiSauTiem() {
        try {
            List<TheoDoiSauTiem> theoDois = theoDoiSauTiemService.getAllTheoDoiSauTiem();
            return ResponseEntity.ok(theoDois);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy theo dõi theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TheoDoiSauTiem> getTheoDoiSauTiemById(@PathVariable Integer id) {
        try {
            Optional<TheoDoiSauTiem> theoDoi = theoDoiSauTiemService.getTheoDoiSauTiemById(id);
            return theoDoi.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Tạo theo dõi sau tiêm mới
    @PostMapping
    public ResponseEntity<?> createTheoDoiSauTiem(@Valid @RequestBody TheoDoiSauTiem theoDoi) {
        try {
            TheoDoiSauTiem createdTheoDoi = theoDoiSauTiemService.createTheoDoiSauTiem(theoDoi);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTheoDoi);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
    
    // Cập nhật theo dõi sau tiêm
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTheoDoiSauTiem(@PathVariable Integer id, @Valid @RequestBody TheoDoiSauTiem theoDoiDetails) {
        try {
            TheoDoiSauTiem updatedTheoDoi = theoDoiSauTiemService.updateTheoDoiSauTiem(id, theoDoiDetails);
            return ResponseEntity.ok(updatedTheoDoi);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
    
    // Kết thúc theo dõi
    @PutMapping("/{id}/finish")
    public ResponseEntity<?> ketThucTheoDoi(@PathVariable Integer id) {
        try {
            TheoDoiSauTiem theoDoi = theoDoiSauTiemService.ketThucTheoDoi(id);
            return ResponseEntity.ok(theoDoi);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
    
    // Xóa theo dõi sau tiêm
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheoDoiSauTiem(@PathVariable Integer id) {
        try {
            theoDoiSauTiemService.deleteTheoDoiSauTiem(id);
            return ResponseEntity.ok().body("Xóa theo dõi thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
        }
    }
    
    // Lấy theo dõi theo lịch hẹn
    @GetMapping("/filter/lich-hen/{lichHenId}")
    public ResponseEntity<List<TheoDoiSauTiem>> getTheoDoiByLichHen(@PathVariable Integer lichHenId) {
        try {
            List<TheoDoiSauTiem> theoDois = theoDoiSauTiemService.getTheoDoiByLichHen(lichHenId);
            return ResponseEntity.ok(theoDois);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy theo dõi theo sự cố
    @GetMapping("/filter/incident/{suCo}")
    public ResponseEntity<List<TheoDoiSauTiem>> getTheoDoiBySuCo(@PathVariable String suCo) {
        try {
            TheoDoiSauTiem.SuCo suCoEnum = TheoDoiSauTiem.SuCo.valueOf(suCo);
            List<TheoDoiSauTiem> theoDois = theoDoiSauTiemService.getTheoDoiBySuCo(suCoEnum);
            return ResponseEntity.ok(theoDois);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy theo dõi trong khoảng thời gian
    @GetMapping("/filter/time-range")
    public ResponseEntity<List<TheoDoiSauTiem>> getTheoDoiTrongKhoangThoiGian(
            @RequestParam String startTime, 
            @RequestParam String endTime) {
        try {
            LocalDateTime start = LocalDateTime.parse(startTime);
            LocalDateTime end = LocalDateTime.parse(endTime);
            List<TheoDoiSauTiem> theoDois = theoDoiSauTiemService.getTheoDoiTrongKhoangThoiGian(start, end);
            return ResponseEntity.ok(theoDois);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy theo dõi theo ngày
    @GetMapping("/filter/date")
    public ResponseEntity<List<TheoDoiSauTiem>> getTheoDoiTheoNgay(@RequestParam String date) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(date);
            List<TheoDoiSauTiem> theoDois = theoDoiSauTiemService.getTheoDoiTheoNgay(dateTime);
            return ResponseEntity.ok(theoDois);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy theo dõi có sự cố nặng
    @GetMapping("/filter/severe-incidents")
    public ResponseEntity<List<TheoDoiSauTiem>> getTheoDoiCoSuCoNang() {
        try {
            List<TheoDoiSauTiem> theoDois = theoDoiSauTiemService.getTheoDoiCoSuCoNang();
            return ResponseEntity.ok(theoDois);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy theo dõi có sự cố nhẹ
    @GetMapping("/filter/mild-incidents")
    public ResponseEntity<List<TheoDoiSauTiem>> getTheoDoiCoSuCoNhe() {
        try {
            List<TheoDoiSauTiem> theoDois = theoDoiSauTiemService.getTheoDoiCoSuCoNhe();
            return ResponseEntity.ok(theoDois);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy theo dõi không có sự cố
    @GetMapping("/filter/no-incidents")
    public ResponseEntity<List<TheoDoiSauTiem>> getTheoDoiKhongCoSuCo() {
        try {
            List<TheoDoiSauTiem> theoDois = theoDoiSauTiemService.getTheoDoiKhongCoSuCo();
            return ResponseEntity.ok(theoDois);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Lấy theo dõi chưa kết thúc
    @GetMapping("/filter/ongoing")
    public ResponseEntity<List<TheoDoiSauTiem>> getTheoDoiChuaKetThuc() {
        try {
            List<TheoDoiSauTiem> theoDois = theoDoiSauTiemService.getTheoDoiChuaKetThuc();
            return ResponseEntity.ok(theoDois);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Thống kê theo dõi theo sự cố
    @GetMapping("/statistics/incidents")
    public ResponseEntity<List<Object[]>> thongKeTheoDoiTheoSuCo() {
        try {
            List<Object[]> statistics = theoDoiSauTiemService.thongKeTheoDoiTheoSuCo();
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Thống kê sự cố theo vaccine
    @GetMapping("/statistics/vaccine")
    public ResponseEntity<List<Object[]>> thongKeSuCoTheoVaccine() {
        try {
            List<Object[]> statistics = theoDoiSauTiemService.thongKeSuCoTheoVaccine();
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Thống kê sự cố theo tháng
    @GetMapping("/statistics/monthly")
    public ResponseEntity<List<Object[]>> thongKeSuCoTheoThang(@RequestParam int year) {
        try {
            List<Object[]> statistics = theoDoiSauTiemService.thongKeSuCoTheoThang(year);
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Báo cáo sự cố nặng
    @GetMapping("/reports/severe-incidents")
    public ResponseEntity<List<TheoDoiSauTiem>> baoCaoSuCoNang() {
        try {
            List<TheoDoiSauTiem> suCoNang = theoDoiSauTiemService.baoCaoSuCoNang();
            return ResponseEntity.ok(suCoNang);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
