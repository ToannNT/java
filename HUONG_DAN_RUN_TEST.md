# 🚀 Hướng Dẫn Compile & Run - Bài Tập BST Student

## Cấu Trúc File

```
sample_midtern/
├── src/
│   ├── model/Student.java       ← Class sinh viên
│   ├── bst/BSTNode.java         ← Node BST
│   ├── bst/StudentBST.java      ← Cây BST + thuật toán
│   └── Main.java                ← Chương trình chính
└── bin/                          ← Output compiled
```

---

## Cách 1: Chạy bằng Command Line (CMD / PowerShell)

### Bước 1: Mở Terminal
Mở CMD hoặc PowerShell, di chuyển đến thư mục project:
```bash
cd d:\iuh\hk2\java\practical\sample_midtern
```

### Bước 2: Compile tất cả file Java
```bash
javac -d bin src/model/Student.java src/bst/BSTNode.java src/bst/StudentBST.java src/Main.java
```

> **Ghi chú:**
> - `-d bin`: xuất file `.class` vào thư mục `bin/`
> - Phải compile theo thứ tự dependency: `Student.java` → `BSTNode.java` → `StudentBST.java` → `Main.java`

### Bước 3: Chạy chương trình
```bash
java -cp bin Main
```

> **Ghi chú:** `-cp bin` chỉ định classpath là thư mục `bin/`

### Kết quả mong đợi
Chương trình sẽ in ra 4 phần tương ứng 4 yêu cầu:
- **YEU CAU 1**: Bảng 10 sinh viên sắp xếp theo ID (101 → 112)
- **YEU CAU 2**: Tổng SV=10, ĐTB=5.95, tỉ lệ xếp loại, SV nam max/min
- **YEU CAU 3**: Tìm ID=106 (thành công), ID=999 (không thấy), xóa ID=107
- **YEU CAU 4**: Danh sách đạt/rớt, sắp xếp theo điểm và tên

---

## Cách 2: Chạy bằng Eclipse IDE

### Bước 1: Mở project
- `File` → `Open Projects from File System...`
- Chọn thư mục `sample_midtern`

### Bước 2: Chạy
- Click phải vào `Main.java` → `Run As` → `Java Application`
- Kết quả hiển thị trong tab `Console`

> **Mẹo:** Nếu Eclipse báo lỗi module, xóa hoặc comment nội dung file `module-info.java`.

---

## Cách 3: Chạy bằng VS Code

### Bước 1: Mở thư mục
- `File` → `Open Folder...` → chọn `sample_midtern`

### Bước 2: Cài Extension
- Cài **Extension Pack for Java** từ Microsoft

### Bước 3: Chạy
- Mở `Main.java`
- Click nút ▶ **Run** phía trên hàm `main()`
- Hoặc nhấn `Ctrl + F5`

---

## Kiểm Tra Kết Quả Từng Yêu Cầu

### ✅ Yêu cầu 1 - Nhập BST & Xuất theo ID
Bảng phải hiển thị 10 sinh viên theo thứ tự ID tăng dần:
```
| 101 | Tran Van Tra      | nam | 9.0 | Gioi       |
| 102 | Le Thi Hoang Anh  | nu  | 2.5 | Yeu        |
...
| 112 | Pham Tra My       | nu  | 7.5 | Kha        |
```

### ✅ Yêu cầu 2 - Thống kê
- Tổng SV: **10**
- ĐTB: **5.95**
- Giỏi: 2 (20%), Khá: 2 (20%), TB: 3 (30%), Yếu: 3 (30%)
- Nam cao nhất: **Tran Van Tra** (9.0)
- Nam thấp nhất: **Le Vu Long** (3.0)

### ✅ Yêu cầu 3 - Tìm & Xóa
- Tìm ID=106 → **Do Phuong Mai** ✓
- Tìm ID=999 → Không tìm thấy ✓
- Xóa ID=107 → Danh sách còn 9 SV (không còn Pham Tuan Hai) ✓

### ✅ Yêu cầu 4 - Trích & Sắp xếp
- **Đạt** (≥5): 7 sinh viên
- **Rớt** (<5): 2 sinh viên (Le Thi Hoang Anh, Le Vu Long)
- Sắp xếp theo điểm: từ thấp → cao
- Sắp xếp theo tên: A → Z (theo tên cuối cùng, kiểu VN)

> **Lưu ý:** Sau khi xóa ID=107 (Pham Tuan Hai) ở Yêu cầu 3, danh sách ở Yêu cầu 4 chỉ còn 9 sinh viên. Trong đó Rớt chỉ còn 2 SV (không còn Pham Tuan Hai vì đã bị xóa).

---

## Xử Lý Lỗi Thường Gặp

| Lỗi | Nguyên nhân | Cách sửa |
|------|-------------|----------|
| `'javac' is not recognized` | Chưa cài JDK hoặc chưa set PATH | Cài JDK 17+ và thêm `JAVA_HOME/bin` vào PATH |
| `error: package model does not exist` | Compile sai thứ tự | Compile `Student.java` trước |
| `Could not find or load main class` | Sai classpath | Dùng `java -cp bin Main` |
| `module-info.java error` | Xung đột module | Xóa nội dung `module-info.java` hoặc xóa file |

