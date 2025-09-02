package com.example.datn_vacxin.service;

import com.example.datn_vacxin.entity.TheoDoiSauTiem;
import com.example.datn_vacxin.entity.LichHenTiem;
import com.example.datn_vacxin.repository.TheoDoiSauTiemRepository;
import com.example.datn_vacxin.repository.LichTiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TheoDoiSauTiemService {
    
    @Autowired
    private TheoDoiSauTiemRepository theoDoiSauTiemRepository;
    
    @Autowired
    private LichTiemRepository lichTiemRepository;
    
    // Lấy tất cả theo dõi sau tiêm
    public List<TheoDoiSauTiem> getAllTheoDoiSauTiem() {
        return theoDoiSauTiemRepository.findAll();
    }
    
    // Lấy theo dõi theo ID
    public Optional<TheoDoiSauTiem> getTheoDoiSauTiemById(Integer id) {
        return theoDoiSauTiemRepository.findById(id);
    }
    
    // Tạo theo dõi sau tiêm mới
    public TheoDoiSauTiem createTheoDoiSauTiem(TheoDoiSauTiem theoDoi) {
        // Kiểm tra lịch hẹn có tồn tại không
        if (theoDoi.getLichHen() == null || theoDoi.getLichHen().getIdLichHen() == null) {
            throw new RuntimeException("Lịch hẹn không được để trống");
        }
        
        LichHenTiem lichHen = lichTiemRepository.findById(theoDoi.getLichHen().getIdLichHen())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch hẹn với ID: " + theoDoi.getLichHen().getIdLichHen()));
        
        // Kiểm tra lịch hẹn đã tiêm chưa
        if (lichHen.getTrangThai() != LichHenTiem.TrangThai.DaTiem) {
            throw new RuntimeException("Chỉ có thể theo dõi sau tiêm cho lịch hẹn đã tiêm");
        }
        
        // Kiểm tra thời gian bắt đầu
        if (theoDoi.getThoiGianBatDau() == null) {
            throw new RuntimeException("Thời gian bắt đầu không được để trống");
        }
        
        if (theoDoi.getThoiGianBatDau().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Không thể tạo theo dõi trong quá khứ");
        }
        
        theoDoi.setLichHen(lichHen);
        
        // Nếu chưa có sự cố, mặc định là "Không"
        if (theoDoi.getSuCo() == null) {
            theoDoi.setSuCo(TheoDoiSauTiem.SuCo.Khong);
        }
        
        return theoDoiSauTiemRepository.save(theoDoi);
    }
    
    // Cập nhật theo dõi sau tiêm
    public TheoDoiSauTiem updateTheoDoiSauTiem(Integer id, TheoDoiSauTiem theoDoiDetails) {
        TheoDoiSauTiem theoDoi = theoDoiSauTiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy theo dõi với ID: " + id));
        
        // Cập nhật thông tin
        if (theoDoiDetails.getThoiGianKetThuc() != null) {
            theoDoi.setThoiGianKetThuc(theoDoiDetails.getThoiGianKetThuc());
        }
        
        if (theoDoiDetails.getMoTa() != null) {
            theoDoi.setMoTa(theoDoiDetails.getMoTa());
        }
        
        if (theoDoiDetails.getSuCo() != null) {
            theoDoi.setSuCo(theoDoiDetails.getSuCo());
        }
        
        return theoDoiSauTiemRepository.save(theoDoi);
    }
    
    // Kết thúc theo dõi
    public TheoDoiSauTiem ketThucTheoDoi(Integer id) {
        TheoDoiSauTiem theoDoi = theoDoiSauTiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy theo dõi với ID: " + id));
        
        if (theoDoi.getThoiGianKetThuc() != null) {
            throw new RuntimeException("Theo dõi đã được kết thúc");
        }
        
        theoDoi.setThoiGianKetThuc(LocalDateTime.now());
        return theoDoiSauTiemRepository.save(theoDoi);
    }
    
    // Xóa theo dõi sau tiêm
    public void deleteTheoDoiSauTiem(Integer id) {
        TheoDoiSauTiem theoDoi = theoDoiSauTiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy theo dõi với ID: " + id));
        
        theoDoiSauTiemRepository.delete(theoDoi);
    }
    
    // Tìm theo dõi theo lịch hẹn
    public List<TheoDoiSauTiem> getTheoDoiByLichHen(Integer lichHenId) {
        return theoDoiSauTiemRepository.findByLichHenIdLichHen(lichHenId);
    }
    
    // Tìm theo dõi theo sự cố
    public List<TheoDoiSauTiem> getTheoDoiBySuCo(TheoDoiSauTiem.SuCo suCo) {
        return theoDoiSauTiemRepository.findBySuCo(suCo);
    }
    
    // Tìm theo dõi trong khoảng thời gian
    public List<TheoDoiSauTiem> getTheoDoiTrongKhoangThoiGian(LocalDateTime startTime, LocalDateTime endTime) {
        return theoDoiSauTiemRepository.findTheoDoiTrongKhoangThoiGian(startTime, endTime);
    }
    
    // Tìm theo dõi theo ngày
    public List<TheoDoiSauTiem> getTheoDoiTheoNgay(LocalDateTime date) {
        return theoDoiSauTiemRepository.findTheoDoiTheoNgay(date);
    }
    
    // Tìm theo dõi có sự cố nặng
    public List<TheoDoiSauTiem> getTheoDoiCoSuCoNang() {
        return theoDoiSauTiemRepository.findTheoDoiCoSuCoNang();
    }
    
    // Tìm theo dõi có sự cố nhẹ
    public List<TheoDoiSauTiem> getTheoDoiCoSuCoNhe() {
        return theoDoiSauTiemRepository.findTheoDoiCoSuCoNhe();
    }
    
    // Tìm theo dõi không có sự cố
    public List<TheoDoiSauTiem> getTheoDoiKhongCoSuCo() {
        return theoDoiSauTiemRepository.findTheoDoiKhongCoSuCo();
    }
    
    // Tìm theo dõi chưa kết thúc
    public List<TheoDoiSauTiem> getTheoDoiChuaKetThuc() {
        return theoDoiSauTiemRepository.findTheoDoiChuaKetThuc();
    }
    
    // Thống kê theo dõi theo sự cố
    public List<Object[]> thongKeTheoDoiTheoSuCo() {
        return theoDoiSauTiemRepository.countBySuCo();
    }
    
    // Thống kê sự cố theo vaccine
    public List<Object[]> thongKeSuCoTheoVaccine() {
        return theoDoiSauTiemRepository.thongKeSuCoTheoVaccine();
    }
    
    // Thống kê sự cố theo tháng
    public List<Object[]> thongKeSuCoTheoThang(int year) {
        return theoDoiSauTiemRepository.thongKeSuCoTheoThang(year);
    }
    
    // Báo cáo sự cố nặng
    public List<TheoDoiSauTiem> baoCaoSuCoNang() {
        List<TheoDoiSauTiem> suCoNang = theoDoiSauTiemRepository.findTheoDoiCoSuCoNang();
        
        // Có thể thêm logic gửi thông báo cho quản lý ở đây
        // notificationService.sendAlertToManager(suCoNang);
        
        return suCoNang;
    }
}
