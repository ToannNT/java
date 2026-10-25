package model;

/**
 * Lớp Student đại diện cho một sinh viên.
 * Mỗi sinh viên có: ID, Name, Gender, Result (điểm).
 */
public class Student {
    private int id;
    private String name;
    private String gender;
    private double result;

    // Constructor đầy đủ tham số
    public Student(int id, String name, String gender, double result) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.result = result;
    }

    // Constructor mặc định
    public Student() {
    }

    // ===== Getter & Setter =====
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Xếp loại học lực dựa trên điểm.
     */
    public String xepLoai() {
        if (result >= 8)
            return "Gioi";
        if (result >= 7)
            return "Kha";
        if (result >= 5)
            return "Trung binh";
        return "Yeu";
    }

    /**
     * Kiểm tra sinh viên đạt hay rớt.
     */
    public boolean isDat() {
        return result >= 5;
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-22s | %-6s | %-6.1f | %-10s |",
				id, name, gender, result, xepLoai());
	}
}
