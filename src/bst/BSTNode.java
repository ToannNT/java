package bst;

import model.Student;

/**
 * Node của cây BST.
 * Mỗi node chứa một đối tượng Student và 2 con trỏ trái/phải.
 */
public class BSTNode {
    Student data; // Dữ liệu sinh viên
    BSTNode left; // Con trỏ trái (ID nhỏ hơn)
    BSTNode right; // Con trỏ phải (ID lớn hơn)

    public BSTNode(Student data) {
        this.data = data;
        this.left = null;
		this.right = null;
	}
}
