-- Insert fake data for testing

-- Insert KhachHang (Customers)
INSERT INTO khach_hang (ho_ten, gioi_tinh, ngay_sinh, nghe_nghiep, so_dien_thoai, email, dia_chi) VALUES
('Nguyễn Văn An', 'Nam', '1990-05-15', 'Kỹ sư', '0123456789', 'nguyenvanan@email.com', '123 Đường ABC, Quận 1, TP.HCM'),
('Trần Thị Bình', 'Nữ', '1985-08-20', 'Bác sĩ', '0987654321', 'tranthibinh@email.com', '456 Đường XYZ, Quận 2, TP.HCM'),
('Lê Văn Cường', 'Nam', '1992-12-10', 'Giáo viên', '0369852147', 'levancuong@email.com', '789 Đường DEF, Quận 3, TP.HCM'),
('Phạm Thị Dung', 'Nữ', '1988-03-25', 'Nhân viên văn phòng', '0741258963', 'phamthidung@email.com', '321 Đường GHI, Quận 4, TP.HCM'),
('Hoàng Văn Em', 'Nam', '1995-07-08', 'Sinh viên', '0852369741', 'hoangvanem@email.com', '654 Đường JKL, Quận 5, TP.HCM');

-- Insert NhanVien (Staff)
INSERT INTO nhan_vien (ho_ten, gioi_tinh, so_dien_thoai, email, chuc_danh) VALUES
('Bác sĩ Nguyễn Thị Lan', 'Nữ', '0912345678', 'bacsinlan@hospital.com', 'Bác sĩ tiêm chủng'),
('Y tá Trần Văn Minh', 'Nam', '0923456789', 'ytaminh@hospital.com', 'Y tá tiêm chủng'),
('Bác sĩ Lê Thị Hoa', 'Nữ', '0934567890', 'bacsinhoa@hospital.com', 'Bác sĩ tiêm chủng'),
('Y tá Phạm Văn Nam', 'Nam', '0945678901', 'ytanam@hospital.com', 'Y tá tiêm chủng');

-- Insert Vaccine
INSERT INTO vaccine (ten_vaccine, mo_ta, hang_san_xuat, han_su_dung) VALUES
('COVID-19 Vaccine Pfizer', 'Vaccine phòng chống COVID-19 của Pfizer', 'Pfizer', '2025-12-31'),
('COVID-19 Vaccine Moderna', 'Vaccine phòng chống COVID-19 của Moderna', 'Moderna', '2025-11-30'),
('COVID-19 Vaccine AstraZeneca', 'Vaccine phòng chống COVID-19 của AstraZeneca', 'AstraZeneca', '2025-10-31'),
('Vaccine Cúm', 'Vaccine phòng chống cúm mùa', 'Sanofi', '2025-09-30'),
('Vaccine Viêm gan B', 'Vaccine phòng chống viêm gan B', 'GSK', '2025-08-31');

-- Insert CaTiem (Vaccination Sessions)
INSERT INTO ca_tiem (id_nhan_vien, thoi_gian_bat_dau, thoi_gian_ket_thuc, phong_tiem) VALUES
(1, '2024-09-03 08:00:00', '2024-09-03 12:00:00', 'Phòng 101'),
(2, '2024-09-03 13:00:00', '2024-09-03 17:00:00', 'Phòng 102'),
(3, '2024-09-04 08:00:00', '2024-09-04 12:00:00', 'Phòng 101'),
(4, '2024-09-04 13:00:00', '2024-09-04 17:00:00', 'Phòng 102'),
(1, '2024-09-05 08:00:00', '2024-09-05 12:00:00', 'Phòng 103'),
(2, '2024-09-05 13:00:00', '2024-09-05 17:00:00', 'Phòng 104');

-- Insert LichHenTiem (Appointments)
INSERT INTO lich_hen_tiem (id_khach_hang, id_vaccine, id_ca_tiem, ngay_tiem, trang_thai) VALUES
(1, 1, 1, '2024-09-03', 'DaDatLich'),
(2, 2, 2, '2024-09-03', 'DaDatLich'),
(3, 1, 3, '2024-09-04', 'DaDatLich'),
(4, 3, 4, '2024-09-04', 'DaDatLich'),
(5, 1, 5, '2024-09-05', 'DaDatLich'),
(1, 4, 6, '2024-09-05', 'DaDatLich'),
(2, 5, 1, '2024-09-06', 'DaDatLich'),
(3, 2, 2, '2024-09-06', 'DaDatLich');

-- Insert TheoDoiSauTiem (Post-vaccination monitoring)
INSERT INTO theo_doi_sau_tiem (id_lich_hen, thoi_gian_bat_dau, thoi_gian_ket_thuc, mo_ta, su_co) VALUES
(1, '2024-09-03 14:00:00', '2024-09-03 15:00:00', 'Khách hàng không có phản ứng phụ', 'Khong'),
(2, '2024-09-03 15:00:00', '2024-09-03 16:00:00', 'Khách hàng có đau nhẹ tại vị trí tiêm', 'PhanUngNhe'),
(3, '2024-09-04 14:00:00', '2024-09-04 15:00:00', 'Khách hàng không có phản ứng phụ', 'Khong'),
(4, '2024-09-04 15:00:00', '2024-09-04 16:00:00', 'Khách hàng có sốt nhẹ', 'PhanUngNhe');
