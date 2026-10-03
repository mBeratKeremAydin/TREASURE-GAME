/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aydinsandikcimenu;

/**
 *
 * @author aydin
 */
public class BinarySearchTree {

    BSTNode root;

    public void insert(String isim, String level, int puan) {
        root = insertRec(root, isim, level, puan);
    }

    private BSTNode insertRec(BSTNode root, String isim, String level, int puan) {
        if (root == null) {
            return new BSTNode(isim, level, puan);
        }

        if (puan < root.puan) {
            root.left = insertRec(root.left, isim, level, puan);
        } else {
            root.right = insertRec(root.right, isim, level, puan);
        }

        return root;
    }

    public String inorder() {
         return inorderRec(root);
    }

    private String inorderRec(BSTNode root) {
        String leveller = "";
        if (root != null) {
            leveller += inorderRec(root.left);
            leveller += root.toString()+',';
            leveller += inorderRec(root.right);
        }
        return leveller;
    }

    public BSTNode bestScore() {
        if (root == null) {
            return null;
        }

        BSTNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public BSTNode worstScore() {
        if (root == null) {
            return null;
        }

        BSTNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

}
