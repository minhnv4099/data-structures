/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TreeDSC;

import ListDSC.Position;
/**
 *
 * @author nguyenminh
 */
public abstract class AbstractBasicTree<E> implements Tree<E>{
    protected int size;
    
    /**
     * Initially root for tree
     * @param ele at root
     * @return position of added node(root)
     * @throws IllegalStateException if there have been root already
     */
    public abstract Position<E> addRoot(E ele) throws IllegalStateException;
    /**
     * add new element to p's children list
     * @param p parent's position
     * @param ele element of p's child
     * @return position of added node
     * @throws IllegalArgumentException if invalid p
     */
    public abstract Position<E> addChild(Position<E> p,E ele) throws IllegalArgumentException;
    
    @Override
    public int size() {return size;}
    @Override
    public boolean isEmpty() {return this.size == 0;}
    
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {return countChildren(p) == 0;}

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {return countChildren(p) > 0;}

    
}
