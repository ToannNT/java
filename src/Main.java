import model.Student;
import bst.StudentBST;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Chương trình chính - Thực hiện tất cả 4 yêu cầu bài thi.
 */
public class Main {

    public static void main(String[] args) {
        // Khởi tạo BST
        StudentBST bst = new StudentBST();

        // ============================================================
        // YÊU CẦU 1: Nhập 10 sinh viên vào BST, xuất theo ID
        // ============================================================
        System.out.println("=".repeat(65));
        System.out.println("  YEU CAU 1: Nhap danh sach vao BST va xuat theo ID");
        System.out.println("=".repeat(65));

        // Tạo 10 sinh viên theo đề bài
        Student std1 = new Student(104, "Le Van Minh", "nam", 6);
        Student std2 = new Student(106, "Do Phuong Mai", "nu", 8.5);
        Student std3 = new Student(102, "Le Thi Hoang Anh", "nu", 2.5);
        Student std4 = new Student(107, "Pham Tuan Hai", "nam", 4);
        Student std5 = new Student(101, "Tran Van Tra", "nam", 9);
        Student std6 = new Student(103, "Le Vu Long", "nam", 3);
        Student std7 = new Student(108, "Phan Thi Yen", "nu", 7);
        Student std8 = new Student(109, "Le Van Chau", "nam", 6.5);
        Student std9 = new Student(112, "Pham Tra My", "nu", 7.5);
        Student std10 = new Student(105, "Dong Van Trang", "nam", 5.5);

        // Chèn lần lượt vào BST
        bst.insert(std1);
        bst.insert(std2);
        bst.insert(std3);
        bst.insert(std4);
        bst.insert(std5);
        bst.insert(std6);
        bst.insert(std7);
        bst.insert(std8);
        bst.insert(std9);
        bst.insert(std10);

        // Xuất danh sách (In-Order → tự động sắp xếp theo ID tăng dần)
        System.out.println("\nDanh sach sinh vien sap xep theo ID (In-Order Traversal):");
        bst.xuatDanhSach();

        // ============================================================
        // YÊU CẦU 2: Thống kê
        // ============================================================
        System.out.println("\n" + "=".repeat(65));
        System.out.println("  YEU CAU 2: Thong ke");
        System.out.println("=".repeat(65));

        // 2a. Tổng số sinh viên
        System.out.println("\n[2a] Tong so sinh vien: " + bst.demSinhVien());

        // 2b. Điểm trung bình
        System.out.printf("[2b] Diem trung binh: %.2f%n", bst.diemTrungBinh());

        // 2c. Tỉ lệ xếp loại
        System.out.println("\n[2c] Ti le xep loai:");
        bst.inTiLeXepLoai();

        // 2d. Sinh viên nam có điểm cao nhất, thấp nhất
        Student namCaoNhat = bst.timNamDiemCaoNhat();
        Student namThapNhat = bst.timNamDiemThapNhat();

        System.out.println("\n[2d] Sinh vien NAM co diem cao nhat:");
        if (namCaoNhat != null) {
            StudentBST.inDauBang();
            System.out.println(namCaoNhat);
            StudentBST.inCuoiBang();
        }

        System.out.println("\n[2d] Sinh vien NAM co diem thap nhat:");
        if (namThapNhat != null) {
            StudentBST.inDauBang();
            System.out.println(namThapNhat);
            StudentBST.inCuoiBang();
        }

        // ============================================================
        // YÊU CẦU 3: Tìm kiếm và xóa theo ID
        // ============================================================
        System.out.println("\n" + "=".repeat(65));
        System.out.println("  YEU CAU 3: Tim kiem va xoa theo ID");
        System.out.println("=".repeat(65));

        // 3a. Tìm sinh viên theo ID
        int idCanTim = 106;
        System.out.println("\n[3a] Tim sinh vien co ID = " + idCanTim + ":");
        Student timThay = bst.timTheoId(idCanTim);
        if (timThay != null) {
            StudentBST.inDauBang();
            System.out.println(timThay);
            StudentBST.inCuoiBang();
        } else {
            System.out.println("  => Khong tim thay sinh vien co ID = " + idCanTim);
        }

        // Tìm ID không tồn tại
        int idKhongTonTai = 999;
        System.out.println("\n[3a] Tim sinh vien co ID = " + idKhongTonTai + ":");
        Student khongTimThay = bst.timTheoId(idKhongTonTai);
        if (khongTimThay != null) {
            StudentBST.inDauBang();
            System.out.println(khongTimThay);
            StudentBST.inCuoiBang();
        } else {
            System.out.println("  => Khong tim thay sinh vien co ID = " + idKhongTonTai);
        }

        // 3b. Xóa sinh viên theo ID
        int idCanXoa = 107;
        System.out.println("\n[3b] Xoa sinh vien co ID = " + idCanXoa + ":");
        boolean xoaThanhCong = bst.xoaTheoId(idCanXoa);
        if (xoaThanhCong) {
            System.out.println("  => Da xoa thanh cong sinh vien co ID = " + idCanXoa);
        } else {
            System.out.println("  => Khong tim thay sinh vien co ID = " + idCanXoa + " de xoa");
        }

        System.out.println("\nDanh sach sau khi xoa ID = " + idCanXoa + ":");
        bst.xuatDanhSach();

        // ============================================================
        // YÊU CẦU 4: Trích danh sách đạt/rớt, sắp xếp
        // ============================================================
        System.out.println("\n" + "=".repeat(65));
        System.out.println("  YEU CAU 4: Trich danh sach dat/rot va sap xep");
        System.out.println("=".repeat(65));

        // 4a. Trích danh sách đạt và rớt
        ArrayList<Student> danhSachDat = bst.laySinhVienDat();
        ArrayList<Student> danhSachRot = bst.laySinhVienRot();

        System.out.println("\n[4a] Danh sach sinh vien DAT (Result >= 5):");
        StudentBST.inDanhSach(danhSachDat);

        System.out.println("\n[4a] Danh sach sinh vien ROT (Result < 5):");
        StudentBST.inDanhSach(danhSachRot);

        // 4b. Sắp xếp theo điểm thi (tăng dần)
        danhSachDat.sort(Comparator.comparingDouble(Student::getResult));
        danhSachRot.sort(Comparator.comparingDouble(Student::getResult));

        System.out.println("\n[4b] Danh sach DAT - sap xep theo DIEM (tang dan):");
        StudentBST.inDanhSach(danhSachDat);

        System.out.println("\n[4b] Danh sach ROT - sap xep theo DIEM (tang dan):");
        StudentBST.inDanhSach(danhSachRot);

        // 4c. Sắp xếp theo tên (lấy tên cuối cùng để so sánh kiểu Việt Nam)
        Comparator<Student> soSanhTheoTen = (sinhVien1, sinhVien2) -> {
            String ten1 = layTenCuoi(sinhVien1.getName());
            String ten2 = layTenCuoi(sinhVien2.getName());
            int ketQua = ten1.compareToIgnoreCase(ten2);
            if (ketQua != 0)
                return ketQua;
            // Nếu tên cuối giống nhau thì so sánh toàn bộ họ tên
            return sinhVien1.getName().compareToIgnoreCase(sinhVien2.getName());
        };

        danhSachDat.sort(soSanhTheoTen);
        danhSachRot.sort(soSanhTheoTen);

        System.out.println("\n[4c] Danh sach DAT - sap xep theo TEN (A-Z):");
        StudentBST.inDanhSach(danhSachDat);

        System.out.println("\n[4c] Danh sach ROT - sap xep theo TEN (A-Z):");
        StudentBST.inDanhSach(danhSachRot);

        System.out.println("\n" + "=".repeat(65));
        System.out.println("  KET THUC CHUONG TRINH");
        System.out.println("=".repeat(65));
    }

    /**
     * Lấy tên cuối cùng từ họ tên đầy đủ.
     * Ví dụ: "Le Van Minh" → "Minh"
     */
    private static String layTenCuoi(String hoTen) {
        String[] cacTu = hoTen.trim().split("\\s+");
        return cacTu[cacTu.length - 1];
    }
}
