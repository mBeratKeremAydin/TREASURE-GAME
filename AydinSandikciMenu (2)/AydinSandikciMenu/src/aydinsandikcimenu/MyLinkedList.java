/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aydinsandikcimenu;

/**
 *
 * @author aydin
 */
public class MyLinkedList {
    private MyNode head;
    private int size;

    public MyNode getHead() {
        return head;
    }

    public void add(int data) {
        MyNode newNode = new MyNode(data);
        if (head == null) {
            head = newNode;
        } else {
            MyNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public int get(int index) {
        MyNode temp = head;
        for (int i = 0; i < index; i++) {
            if (temp != null) {
                temp = temp.next;
            } else {
                return -1; // out of bounds
            }
        }
        return temp != null ? temp.data : -1;
    }

    public int size() {
        return size;
    }

}
