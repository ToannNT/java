# 📚 Tài Liệu Giải Thích Kiến Thức - Bài Tập BST Student

## Mục Lục
1. [Cấu Trúc Dự Án](#1-cấu-trúc-dự-án)
2. [Kiến Thức Nền Tảng: Binary Search Tree (BST)](#2-kiến-thức-nền-tảng-binary-search-tree-bst)
3. [Giải Thích Chi Tiết Từng Yêu Cầu](#3-giải-thích-chi-tiết-từng-yêu-cầu)
4. [Các Khái Niệm Java Sử Dụng](#4-các-khái-niệm-java-sử-dụng)
5. [Độ Phức Tạp Thuật Toán](#5-độ-phức-tạp-thuật-toán)

---

## 1. Cấu Trúc Dự Án

```
sample_midtern/
├── src/
│   ├── model/
│   │   └── Student.java          ← Lớp sinh viên
│   ├── bst/
│   │   ├── BSTNode.java          ← Node của cây BST
│   │   └── StudentBST.java       ← Cây BST + tất cả thuật toán
│   └── Main.java                 ← Chương trình chính
└── bin/                           ← File .class sau compile
```

---

## 2. Kiến Thức Nền Tảng: Binary Search Tree (BST)

### 2.1. BST là gì?

**Binary Search Tree** là cây nhị phân, mỗi node có tối đa 2 con, và theo quy tắc:

```
          Node cha (ID = X)
         /                \
   Con trái               Con phải
  (ID < X)                (ID > X)
```

- Tất cả node bên **trái** có giá trị **nhỏ hơn** node cha
- Tất cả node bên **phải** có giá trị **lớn hơn** node cha

### 2.2. Minh họa BST trong bài

Chèn lần lượt: 104, 106, 102, 107, 101, 103, 108, 109, 112, 105

```
                    104
                   /    \
                102      106
               /   \    /    \
             101  103  105   107
                                \
                               108
                                 \
                                109
                                  \
                                 112
```

### 2.3. Các phép duyệt cây

| Phép duyệt | Thứ tự | Ý nghĩa |
|------------|--------|---------|
| **In-Order (LNR)** | Trái → Node → Phải | Cho kết quả **sắp xếp tăng dần** |
| Pre-Order (NLR) | Node → Trái → Phải | Dùng để sao chép cây |
| Post-Order (LRN) | Trái → Phải → Node | Dùng để xóa cây |

> **Quan trọng**: In-Order trên BST **luôn** cho kết quả đã sắp xếp tăng dần → đây là lý do ta dùng In-Order để xuất danh sách theo ID.

### 2.4. Các thao tác cơ bản

#### Chèn (Insert)
```
Bắt đầu từ root:
1. Node rỗng → tạo node mới
2. ID mới < ID node → đi trái
3. ID mới > ID node → đi phải
4. Lặp lại cho đến khi tìm vị trí rỗng
```

#### Tìm kiếm (Search)
```
Bắt đầu từ root:
1. Node rỗng → không tìm thấy
2. ID cần tìm == ID node → tìm thấy!
3. ID cần tìm < ID node → tìm bên trái
4. ID cần tìm > ID node → tìm bên phải
```

#### Xóa (Delete) — 3 trường hợp

| Trường hợp | Mô tả | Cách xử lý |
|------------|--------|-------------|
| **Node lá** | Không có con | Xóa trực tiếp |
| **1 con** | Có 1 con trái hoặc phải | Thay node bằng con duy nhất |
| **2 con** | Có cả trái và phải | Tìm node nhỏ nhất bên phải (successor), copy dữ liệu lên, xóa successor |

---

## 3. Giải Thích Chi Tiết Từng Yêu Cầu

### Yêu cầu 1 (2đ): Nhập và Xuất BST

**Nhập**: Tạo 10 đối tượng `Student`, gọi `bst.insert()` cho mỗi sinh viên.

**Xuất**: Gọi `bst.xuatDanhSach()` — duyệt In-Order sẽ tự in theo ID tăng dần.

```java
bst.insert(new Student(104, "Le Van Minh", "nam", 6));
bst.xuatDanhSach();  // In-Order → tự sắp xếp theo ID
```

### Yêu cầu 2 (3đ): Thống kê

| Thống kê | Hàm gọi | Cách làm |
|----------|---------|----------|
| Tổng số SV | `demSinhVien()` | Đệ quy: `1 + đếm(trái) + đếm(phải)` |
| Điểm TB | `diemTrungBinh()` | `tổng điểm / tổng số SV` |
| Tỉ lệ xếp loại | `inTiLeXepLoai()` | Lấy tất cả SV, duyệt for-each đếm từng loại |
| Nam max/min | `timNamDiemCaoNhat()` | Lọc SV nam, so sánh tìm max/min |

### Yêu cầu 3 (2đ): Tìm kiếm và Xóa

```java
Student timThay = bst.timTheoId(106);    // Tìm theo ID
boolean xoaOk = bst.xoaTheoId(107);     // Xóa theo ID
```

### Yêu cầu 4 (3đ): Trích và Sắp xếp

```java
// Trích danh sách
ArrayList<Student> danhSachDat = bst.laySinhVienDat();
ArrayList<Student> danhSachRot = bst.laySinhVienRot();

// Sắp xếp theo điểm
danhSachDat.sort(Comparator.comparingDouble(Student::getResult));

// Sắp xếp theo tên cuối (kiểu VN)
danhSachDat.sort((sinhVien1, sinhVien2) -> {
    String ten1 = layTenCuoi(sinhVien1.getName()); // "Le Van Minh" → "Minh"
    String ten2 = layTenCuoi(sinhVien2.getName());
    return ten1.compareToIgnoreCase(ten2);
});
```

---

## 4. Các Khái Niệm Java Sử Dụng

### 4.1. Đệ quy (Recursion)
Hàm gọi chính nó với dữ liệu nhỏ hơn. BST dùng đệ quy rất nhiều:

```java
private void duyetInOrder(BSTNode node) {
    if (node != null) {              // Điều kiện dừng
        duyetInOrder(node.left);     // Gọi lại với cây trái
        System.out.println(node.data);
        duyetInOrder(node.right);    // Gọi lại với cây phải
    }
}
```

### 4.2. ArrayList
Mảng động, tự mở rộng kích thước:

```java
ArrayList<Student> danhSach = new ArrayList<>();
danhSach.add(sinhVien);          // Thêm phần tử
danhSach.sort(comparator);      // Sắp xếp
danhSach.size();                 // Đếm phần tử
```

### 4.3. Comparator & Lambda
Định nghĩa cách so sánh khi sắp xếp:

```java
// Sắp xếp theo điểm (dùng Method Reference)
danhSach.sort(Comparator.comparingDouble(Student::getResult));

// Sắp xếp theo tên (dùng Lambda)
danhSach.sort((sinhVien1, sinhVien2) ->
    sinhVien1.getName().compareToIgnoreCase(sinhVien2.getName())
);
```

### 4.4. Encapsulation (Đóng gói)
Thuộc tính `private` + getter/setter để bảo vệ dữ liệu:

```java
private int id;                    // Không truy cập trực tiếp từ ngoài
public int getId() { return id; }  // Đọc qua getter
public void setId(int id) { this.id = id; } // Ghi qua setter
```

### 4.5. String.format()
Định dạng chuỗi để in bảng:

```java
// %-5d  : số nguyên, canh trái, rộng 5 ký tự
// %-22s : chuỗi, canh trái, rộng 22 ký tự
// %-6.1f: số thực, 1 chữ số thập phân, rộng 6 ký tự
String.format("| %-5d | %-22s | %-6.1f |", id, name, result);
```

---

## 5. Độ Phức Tạp Thuật Toán

| Thao tác | Trung bình | Xấu nhất |
|----------|-----------|----------|
| Insert | O(log n) | O(n) |
| Search | O(log n) | O(n) |
| Delete | O(log n) | O(n) |
| In-Order / Count / Sum | O(n) | O(n) |

> **Lưu ý**: Trường hợp xấu nhất O(n) xảy ra khi chèn dữ liệu theo thứ tự tăng/giảm dần, BST thành linked list. Giải pháp: dùng cây cân bằng (AVL, Red-Black Tree).

