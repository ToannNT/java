package bst;

import model.Student;
import java.util.ArrayList;

/**
 * Cây Binary Search Tree (BST) với nhãn node theo ID sinh viên.
 *
 * Quy tắc BST: - Node con trái có ID < ID node cha - Node con phải có ID > ID
 * node cha
 */
public class StudentBST {
	private BSTNode root; // Gốc của cây

	public StudentBST() {
		root = null;
	}

	// ======================== YÊU CẦU 1 ========================

	/**
	 * Thêm sinh viên vào BST (chèn theo ID).
	 */
	public void insert(Student student) {
		root = insertNode(root, student);
	}

	private BSTNode insertNode(BSTNode node, Student student) {
		// Nếu node rỗng, tạo node mới
		if (node == null) {
			return new BSTNode(student);
		}
		// So sánh ID để quyết định chèn trái hay phải
		if (student.getId() < node.data.getId()) {
			node.left = insertNode(node.left, student);
		} else if (student.getId() > node.data.getId()) {
			node.right = insertNode(node.right, student);
		}
		return node;
	}

	/**
	 * Xuất danh sách sinh viên theo ID (duyệt In-Order = tăng dần).
	 */
	public void xuatDanhSach() {
		inDauBang();
		duyetInOrder(root);
		inCuoiBang();
	}

	private void duyetInOrder(BSTNode node) {
		if (node != null) {
			duyetInOrder(node.left); // Duyệt trái
			System.out.println(node.data); // In node hiện tại
			duyetInOrder(node.right); // Duyệt phải
		}
	}

	// ======================== YÊU CẦU 2 ========================

	/**
	 * Đếm tổng số sinh viên trong BST.
	 */
	public int demSinhVien() {
		return demNode(root);
	}

	private int demNode(BSTNode node) {
		if (node == null)
			return 0;
		return 1 + demNode(node.left) + demNode(node.right);
	}

	/**
	 * Tính điểm trung bình của tất cả sinh viên.
	 */
	public double diemTrungBinh() {
		int tongSo = demSinhVien();
		if (tongSo == 0)
			return 0;
		return tongDiem(root) / tongSo;
	}

	private double tongDiem(BSTNode node) {
		if (node == null)
			return 0;
		return node.data.getResult() + tongDiem(node.left) + tongDiem(node.right);
	}

	/**
	 * In tỉ lệ xếp loại: Giỏi, Khá, Trung bình, Yếu.
	 */
	public void inTiLeXepLoai() {
		int soGioi = 0, soKha = 0, soTrungBinh = 0, soYeu = 0;

		// Lấy tất cả sinh viên ra list rồi đếm
		ArrayList<Student> tatCa = layTatCaSinhVien();
		for (Student sinhVien : tatCa) {
			double diem = sinhVien.getResult();
			if (diem >= 8)
				soGioi++;
			else if (diem >= 7)
				soKha++;
			else if (diem >= 5)
				soTrungBinh++;
			else
				soYeu++;
		}

		int tongSo = tatCa.size();
		System.out.println("  +------------+----------+---------+");
		System.out.println("  | Xep loai   | So luong | Ti le   |");
		System.out.println("  +------------+----------+---------+");
		System.out.printf("  | %-10s | %-8d | %5.1f%% |%n", "Gioi (>=8)", soGioi, soGioi * 100.0 / tongSo);
		System.out.printf("  | %-10s | %-8d | %5.1f%% |%n", "Kha (>=7)", soKha, soKha * 100.0 / tongSo);
		System.out.printf("  | %-10s | %-8d | %5.1f%% |%n", "TB (>=5)", soTrungBinh, soTrungBinh * 100.0 / tongSo);
		System.out.printf("  | %-10s | %-8d | %5.1f%% |%n", "Yeu (<5)", soYeu, soYeu * 100.0 / tongSo);
		System.out.println("  +------------+----------+---------+");
	}

	/**
	 * Tìm sinh viên NAM có điểm CAO nhất.
	 */
	public Student timNamDiemCaoNhat() {
		ArrayList<Student> tatCa = layTatCaSinhVien();
		Student namCaoNhat = null;

		for (Student sinhVien : tatCa) {
			if (sinhVien.getGender().equalsIgnoreCase("nam")) {
				if (namCaoNhat == null || sinhVien.getResult() > namCaoNhat.getResult()) {
					namCaoNhat = sinhVien;
				}
			}
		}
		return namCaoNhat;
	}

	/**
	 * Tìm sinh viên NAM có điểm THẤP nhất.
	 */
	public Student timNamDiemThapNhat() {
		ArrayList<Student> tatCa = layTatCaSinhVien();
		Student namThapNhat = null;

		for (Student sinhVien : tatCa) {
			if (sinhVien.getGender().equalsIgnoreCase("nam")) {
				if (namThapNhat == null || sinhVien.getResult() < namThapNhat.getResult()) {
					namThapNhat = sinhVien;
				}
			}
		}
		return namThapNhat;
	}

	// ======================== YÊU CẦU 3 ========================

	/**
	 * Tìm sinh viên theo ID.
	 */
	public Student timTheoId(int id) {
		return timNode(root, id);
	}

	private Student timNode(BSTNode node, int id) {
		if (node == null)
			return null;
		if (id == node.data.getId())
			return node.data;
		if (id < node.data.getId())
			return timNode(node.left, id);
		return timNode(node.right, id);
	}

	/**
	 * Xóa sinh viên theo ID. 3 trường hợp: 1. Node lá (không có con) → xóa trực
	 * tiếp 2. Node có 1 con → thay bằng con duy nhất 3. Node có 2 con → thay bằng
	 * node nhỏ nhất bên phải (successor)
	 */
	public boolean xoaTheoId(int id) {
		if (timTheoId(id) == null)
			return false;
		root = xoaNode(root, id);
		return true;
	}

	private BSTNode xoaNode(BSTNode node, int id) {
		if (node == null)
			return null;

		if (id < node.data.getId()) {
			node.left = xoaNode(node.left, id);
		} else if (id > node.data.getId()) {
			node.right = xoaNode(node.right, id);
		} else {
			// Tìm thấy node cần xóa

			// TH1 & TH2: không có con trái → trả về con phải (hoặc null)
			if (node.left == null)
				return node.right;
			// TH1 & TH2: không có con phải → trả về con trái
			if (node.right == null)
				return node.left;

			// TH3: có 2 con → tìm node nhỏ nhất bên phải (successor)
			BSTNode nodeNhoNhat = timNodeNhoNhat(node.right);
			node.data = nodeNhoNhat.data;
			node.right = xoaNode(node.right, nodeNhoNhat.data.getId());
		}
		return node;
	}

	/**
	 * Tìm node có ID nhỏ nhất (đi trái liên tục).
	 */
	private BSTNode timNodeNhoNhat(BSTNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	// ======================== YÊU CẦU 4 ========================

	/**
	 * Lấy danh sách sinh viên ĐẠT (result >= 5).
	 */
	public ArrayList<Student> laySinhVienDat() {
		ArrayList<Student> danhSachDat = new ArrayList<>();
		ArrayList<Student> tatCa = layTatCaSinhVien();

		for (Student sinhVien : tatCa) {
			if (sinhVien.isDat()) {
				danhSachDat.add(sinhVien);
			}
		}
		return danhSachDat;
	}

	/**
	 * Lấy danh sách sinh viên RỚT (result < 5).
	 */
	public ArrayList<Student> laySinhVienRot() {
		ArrayList<Student> danhSachRot = new ArrayList<>();
		ArrayList<Student> tatCa = layTatCaSinhVien();

		for (Student sinhVien : tatCa) {
			if (!sinhVien.isDat()) {
				danhSachRot.add(sinhVien);
			}
		}
		return danhSachRot;
	}

	// ======================== HỖ TRỢ ========================

	/**
	 * Lấy tất cả sinh viên vào ArrayList (duyệt In-Order).
	 */
	public ArrayList<Student> layTatCaSinhVien() {
		ArrayList<Student> danhSach = new ArrayList<>();
		themVaoList(root, danhSach);
		return danhSach;
	}

	private void themVaoList(BSTNode node, ArrayList<Student> danhSach) {
		if (node != null) {
			themVaoList(node.left, danhSach);
			danhSach.add(node.data);
			themVaoList(node.right, danhSach);
		}
	}

	/**
	 * In header bảng.
	 */
	public static void inDauBang() {
		System.out.println("+-------+------------------------+--------+--------+------------+");
		System.out.println("| ID    | Name                   | Gender | Result | Xep loai   |");
		System.out.println("+-------+------------------------+--------+--------+------------+");
	}

	/**
	 * In footer bảng.
	 */
	public static void inCuoiBang() {
		System.out.println("+-------+------------------------+--------+--------+------------+");
	}

	/**
	 * In danh sách sinh viên từ ArrayList theo dạng bảng.
	 */
	public static void inDanhSach(ArrayList<Student> danhSach) {
		inDauBang();
		for (Student sinhVien : danhSach) {
			System.out.println(sinhVien);
		}
		inCuoiBang();
	}
}
