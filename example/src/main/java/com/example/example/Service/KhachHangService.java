package com.example.example.Service;

import com.example.example.Entity.KhachHang;
import com.example.example.Repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    public Optional<KhachHang> getKhachHangById(Long id) {
        return khachHangRepository.findById(id);
    }

    public KhachHang createKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    public KhachHang updateKhachHang(Long id, KhachHang khachHang) {
        khachHang.setId(id);
        return khachHangRepository.save(khachHang);
    }

    public void deleteKhachHang(Long id) {
        khachHangRepository.deleteById(id);
    }
}
