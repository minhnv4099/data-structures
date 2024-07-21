/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeDSC;

import ListDSC.Position;

/**
 *
 * @author nguyenminh
 * some operations for binary tree
 */
public interface BinaryTree<E> extends Tree<E>{
    
    /**
     * @param ele
     * @return Position of root
     * @throws IllegalStateException if tree is not empty(have already root)
     */
    Position<E> addRoot(E ele) throws IllegalStateException;
    
    /**
     * @param p
     * @param ele
     * @return position of left child
     * @throws IllegalStateException if left child already
     * @throws IllegalArgumentException if invalid p
     */
    Position<E> addLeft(Position<E> p,E ele) throws IllegalArgumentException,IllegalStateException;
    
    /**
     * @param p
     * @param ele
     * @return position of right child
     * @throws IllegalStateException if right child already
     * @throws IllegalArgumentException if invalid p
     */
    Position<E> addRight(Position<E> p,E ele) throws IllegalArgumentException,IllegalStateException;
    
    /** 
     * replace new element to position p
     * @return formerly element
     * @throws IllegalArgumentException if invalid p
     */ 
    E set(Position<E> p, E ele) throws IllegalArgumentException;
    
    /**
     * remove node at position {@code p}
     * @param p position expected to removed
     * remove node at position p, replace it with its child(only one child)
     * @return removed position
     * @throws IllegalArgumentException if invalid p
     * @throws IllegalStateException if p has 2 children
     */
    Position<E> remove(Position<E> p) throws IllegalArgumentException, IllegalStateException;
    
    /**
     * @Return position of left child of position p
     * @return null if has no left child
     * @throws IllegalArgumentException if invalid p
     */
    Position<E> leftChild(Position<E> p) throws IllegalArgumentException;
    
    /**
     * @Return position of right child of position p
     * @return null if has no right child
     * @throws IllegalArgumentException if invalid p
     */
    Position<E> rightChild(Position<E> p) throws IllegalArgumentException;
    
    /**
     * @Return position of sibling of position p
     * @return null if has no sibling
     * @throws IllegalArgumentException if invalid p
     */
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
