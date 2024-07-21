/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinarySearchTreeDSC;

import ListDSC.Position;
import MapDSC.Entry;
import MapDSC.RedBlackTreeMap;
import java.util.Comparator;
import MapDSC.TreeMap;

/**
 *
 * @author nguyenminh
 */
class Student implements Comparable<Student>{
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", id=" + id + '}';
    }

    @Override
    public int compareTo(Student o) {
        int result = this.getId() - o.getId();
        if(result==0) return 0;
        return result>0? 1:-1;
    }
}
public class TestBST {
    public static void main(String[] args) {
        RedBlackTreeMap<Integer,String> tree = new RedBlackTreeMap<>();
        tree.put(9, "five");
        tree.put(20, "five");
        tree.put(30, "five");
        //tree.put(1, "five");
        //tree.put(16, "five");
        //tree.put(12, "five");
        
        System.out.println(tree.color(tree.root()));
        System.out.println(tree.color(tree.rightChild(tree.root())));
        System.out.println(tree.color(tree.rightChild(tree.rightChild(tree.root()))));
        
        
        
    }
}
