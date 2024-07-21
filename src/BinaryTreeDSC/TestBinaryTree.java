/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeDSC;

import ListDSC.Position;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author nguyenminh
 */
public class TestBinaryTree {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> tree1 = new LinkedBinaryTree<>();
        Position<Integer> root = tree.addRoot(1);
        Position<Integer> child1 = tree.addLeft(root,5);
        Position<Integer> child2 = tree.addRight(root,3);
        Position<Integer> child3 = tree.addRight(child1,4);
        Position<Integer> child4 = tree.addLeft(child1,2);
        Position<Integer> child5 = tree.addRight(child3,6);
        
        tree.ancestor(child3).forEach((k)-> System.out.println(k.getElement()));
        //tree.positions(0).forEach((t) -> System.err.println(t.getElement()));
        //tree.ancestor(child4).forEach((e) -> System.out.println(e.getElement()));

    }
}
