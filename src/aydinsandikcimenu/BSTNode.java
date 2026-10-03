/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aydinsandikcimenu;

/**
 *
 * @author aydin
 */
public class BSTNode {
    int puan;
    String level;
    String isim;
    BSTNode left, right;

    public BSTNode(String isim, String level, int puan) {
        this.isim = isim;
        this.level = level;
        this.puan = puan;
        left = right = null;
    }

    @Override
    public String toString() {
        return   puan +"("+level+")";
    }
}
