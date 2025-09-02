package com.example.datn_vacxin.service;

import com.example.datn_vacxin.entity.LichHenTiem;
import com.example.datn_vacxin.entity.KhachHang;
import com.example.datn_vacxin.entity.Vaccine;
import com.example.datn_vacxin.entity.CaTiem;
import com.example.datn_vacxin.repository.LichTiemRepository;
import com.example.datn_vacxin.repository.KhachHangRepository;
import com.example.datn_vacxin.repository.VaccineRepository;
import com.example.datn_vacxin.repository.CaTiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LichTiemService {
    
    @Autowired
    private LichTiemRepository lichTiemRepository;
    
    @Autowired
    private KhachHangRepository khachHangRepository;
    
    @Autowired
    private VaccineRepository vaccineRepository;
    
    @Autowired
    private CaTiemRepository caTiemRepository;
    
    // Lấy tất cả lịch hẹn
    public List<LichHenTiem> getAllLichHen() {
        return lichTiemRepository.findAll();
    }
    
    // Lấy lịch hẹn theo ID
    public Optional<LichHenTiem> getLichHenById(Integer id) {
        return lichTiemRepository.findById(id);
    }
    
    // Tạo lịch hẹn mới
    public LichHenTiem createLichHen(LichHenTiem lichHen) {
        // Kiểm tra khách hàng có tồn tại không
        if (lichHen.getKhachHang() == null || lichHen.getKhachHang().getIdKhachHang() == null) {
            throw new RuntimeException("Khách hàng không được để trống");
        }
        
        KhachHang khachHang = khachHangRepository.findById(lichHen.getKhachHang().getIdKhachHang())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + lichHen.getKhachHang().getIdKhachHang()));
        
        // Kiểm tra vaccine có tồn tại và còn hạn không
        if (lichHen.getVaccine() == null || lichHen.getVaccine().getIdVaccine() == null) {
            throw new RuntimeException("Vaccine không được để trống");
        }
        
        Vaccine vaccine = vaccineRepository.findById(lichHen.getVaccine().getIdVaccine())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vaccine với ID: " + lichHen.getVaccine().getIdVaccine()));
        
        if (vaccine.getHanSuDung() != null && vaccine.getHanSuDung().isBefore(LocalDate.now())) {
            throw new RuntimeException("Vaccine đã hết hạn sử dụng");
        }
        
        // Kiểm tra ca tiêm có tồn tại không
        if (lichHen.getCaTiem() == null || lichHen.getCaTiem().getIdCaTiem() == null) {
            throw new RuntimeException("Ca tiêm không được để trống");
        }
        
        CaTiem caTiem = caTiemRepository.findById(lichHen.getCaTiem().getIdCaTiem())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ca tiêm với ID: " + lichHen.getCaTiem().getIdCaTiem()));
        
        // Kiểm tra ngày tiêm hợp lệ
        if (lichHen.getNgayTiem() == null) {
            throw new RuntimeException("Ngày tiêm không được để trống");
        }
        
        if (lichHen.getNgayTiem().isBefore(LocalDate.now())) {
            throw new RuntimeException("Không thể đặt lịch tiêm trong quá khứ");
        }
        
        // Kiểm tra xem khách hàng đã có lịch hẹn trong ca tiêm này chưa
        List<LichHenTiem> existingLichHen = lichTiemRepository.findByKhachHangIdKhachHangAndCaTiemIdCaTiemAndTrangThai(
                khachHang.getIdKhachHang(), 
                caTiem.getIdCaTiem(), 
                LichHenTiem.TrangThai.DaDatLich
        );
        
        if (!existingLichHen.isEmpty()) {
            throw new RuntimeException("Khách hàng đã có lịch hẹn trong ca tiêm này");
        }
        
        lichHen.setKhachHang(khachHang);
        lichHen.setVaccine(vaccine);
        lichHen.setCaTiem(caTiem);
        lichHen.setTrangThai(LichHenTiem.TrangThai.DaDatLich);
        
        return lichTiemRepository.save(lichHen);
    }
    
    // Cập nhật lịch hẹn
    public LichHenTiem updateLichHen(Integer id, LichHenTiem lichHenDetails) {
        LichHenTiem lichHen = lichTiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch hẹn với ID: " + id));
        
        // Chỉ cho phép cập nhật nếu chưa tiêm
        if (lichHen.getTrangThai() == LichHenTiem.TrangThai.DaTiem) {
            throw new RuntimeException("Không thể cập nhật lịch hẹn đã tiêm");
        }
        
        // Cập nhật thông tin
        if (lichHenDetails.getNgayTiem() != null) {
            if (lichHenDetails.getNgayTiem().isBefore(LocalDate.now())) {
                throw new RuntimeException("Không thể đặt lịch tiêm trong quá khứ");
            }
            lichHen.setNgayTiem(lichHenDetails.getNgayTiem());
        }
        
        if (lichHenDetails.getTrangThai() != null) {
            lichHen.setTrangThai(lichHenDetails.getTrangThai());
        }
        
        return lichTiemRepository.save(lichHen);
    }
    
    // Xác nhận tiêm xong
    public LichHenTiem confirmTiemXong(Integer id) {
        LichHenTiem lichHen = lichTiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch hẹn với ID: " + id));
        
        if (lichHen.getTrangThai() != LichHenTiem.TrangThai.DaDatLich) {
            throw new RuntimeException("Chỉ có thể xác nhận tiêm cho lịch hẹn đã đặt");
        }
        
        lichHen.setTrangThai(LichHenTiem.TrangThai.DaTiem);
        return lichTiemRepository.save(lichHen);
    }
    
    // Hủy lịch hẹn
    public LichHenTiem huyLichHen(Integer id) {
        LichHenTiem lichHen = lichTiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch hẹn với ID: " + id));
        
        if (lichHen.getTrangThai() == LichHenTiem.TrangThai.DaTiem) {
            throw new RuntimeException("Không thể hủy lịch hẹn đã tiêm");
        }
        
        lichHen.setTrangThai(LichHenTiem.TrangThai.Huy);
        return lichTiemRepository.save(lichHen);
    }
    
    // Xóa lịch hẹn
    public void deleteLichHen(Integer id) {
        LichHenTiem lichHen = lichTiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch hẹn với ID: " + id));
        
        // Chỉ cho phép xóa nếu chưa tiêm
        if (lichHen.getTrangThai() == LichHenTiem.TrangThai.DaTiem) {
            throw new RuntimeException("Không thể xóa lịch hẹn đã tiêm");
        }
        
        lichTiemRepository.delete(lichHen);
    }
    
    // Tìm lịch hẹn theo khách hàng
    public List<LichHenTiem> getLichHenByKhachHang(Integer khachHangId) {
        return lichTiemRepository.findByKhachHangIdKhachHang(khachHangId);
    }
    
    // Tìm lịch hẹn theo vaccine
    public List<LichHenTiem> getLichHenByVaccine(Integer vaccineId) {
        return lichTiemRepository.findByVaccineIdVaccine(vaccineId);
    }
    
    // Tìm lịch hẹn theo ca tiêm
    public List<LichHenTiem> getLichHenByCaTiem(Integer caTiemId) {
        return lichTiemRepository.findByCaTiemIdCaTiem(caTiemId);
    }
    
    // Tìm lịch hẹn theo trạng thái
    public List<LichHenTiem> getLichHenByTrangThai(LichHenTiem.TrangThai trangThai) {
        return lichTiemRepository.findByTrangThai(trangThai);
    }
    
    // Tìm lịch hẹn theo ngày tiêm
    public List<LichHenTiem> getLichHenByNgayTiem(LocalDate ngayTiem) {
        return lichTiemRepository.findByNgayTiem(ngayTiem);
    }
    
    // Tìm lịch hẹn trong khoảng thời gian
    public List<LichHenTiem> getLichHenTrongKhoangThoiGian(LocalDate startDate, LocalDate endDate) {
        return lichTiemRepository.findLichHenTrongKhoangThoiGian(startDate, endDate);
    }
    
    // Tìm lịch hẹn sắp tới
    public List<LichHenTiem> getLichHenSapToi() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);
        return lichTiemRepository.findLichHenSapToi(today, nextWeek);
    }
    
    // Tìm lịch hẹn quá hạn
    public List<LichHenTiem> getLichHenQuaHan() {
        return lichTiemRepository.findLichHenQuaHan(LocalDate.now());
    }
    
    // Thống kê lịch hẹn theo trạng thái
    public List<Object[]> thongKeLichHenTheoTrangThai() {
        return lichTiemRepository.countByTrangThai();
    }
    
    // Thống kê lịch hẹn theo vaccine
    public List<Object[]> thongKeLichHenTheoVaccine() {
        return lichTiemRepository.countByVaccine();
    }
    
    // Thống kê lịch hẹn theo tháng
    public List<Object[]> thongKeLichHenTheoThang(int year) {
        return lichTiemRepository.thongKeLichHenTheoThang(year);
    }
}
