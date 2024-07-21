 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TreeDSC;
import ListDSC.LinkedPositionalList;
import ListDSC.Position;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import DequeDSC.LinkedDeque;

/**
 *
 * @author nguyenminh
 */
public class BasicTree<E> extends AbstractBasicTree<E>{
    private class Node<E> implements Position<E>{
        private E ele;
        private Node<E> parent;
        private LinkedPositionalList<Node<E>> listChildren;

        public Node(E ele, Node<E> parent) {
            this.ele = ele;
            this.parent = parent;
            this.listChildren = new LinkedPositionalList<>();
        }
        
        public void setEle(E ele) {this.ele = ele;}
        public Node<E> getParent() {return parent;}
        public void setPrarent(Node<E> preNode) {this.parent = preNode;}
        public int numberOfChildren(){return this.listChildren.size();}
        public LinkedPositionalList<Node<E>> getChildren(){return this.listChildren;}
        @Override
        public E getElement() {return this.ele;}
    }
    
    private Node<E> root;
    private List<Position<E>> traversal;
    
    public BasicTree() {
        this.root = null;
        this.size = 0;
        this.traversal = new ArrayList<>();
    }
    
    private Node<E> validate(Position<E> p){
        if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid position p");
        Node<E> tmpNode = (Node<E>)p;
        if(tmpNode.getParent()== null && this.root != tmpNode) throw new IllegalArgumentException("The position is not in tree");
        return tmpNode;
    }
    @Override
    public Position<E> addRoot(E ele) throws IllegalStateException{
        if(!this.isEmpty()) throw new IllegalStateException("Tree contains only one root");
        this.root = new Node(ele, null);
        this.size++;
        return root;
    }
    @Override
    public Position<E> addChild(Position<E> p,E ele) throws IllegalArgumentException{
        Node<E> tmpParent = this.validate(p);
        this.size++;
        return tmpParent.getChildren().addLast(new Node<>(ele,tmpParent)).getElement();
    }
    
    @Override
    public Position<E> root() {return this.root;}
    
    public boolean isRoot(Position<E> p){return this.validate(p) == this.root;}    
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        Node<E> tmpNode = this.validate(p);
        List<Position<E>> childList = new ArrayList();
        Iterable<Node<E>> tmpResult = tmpNode.getChildren().EleIterable();
        for(Position<E> c : tmpResult) childList.add(c);
        return childList;
    }
    
    public Iterable<Position<E>> ancestor(Position<E> p){
        List<Position<E>> ancestorList = new ArrayList<>();
        for(;!isRoot(p);ancestorList.add(p=this.parent(p)));
        return ancestorList;
    }
    
    public Iterable<Position<E>> descendants(Position<E> p){
        return this.positions(p,3);
    }
    
    @Override
    public Position<E> parent(Position p) throws IllegalArgumentException {
        Node<E> tmpNode = this.validate(p);
        return tmpNode.getParent();
    }

    @Override
    public int countChildren(Position<E> p) throws IllegalArgumentException {
        Node<E> tmpNode = this.validate(p);
        return tmpNode.getChildren().size();
    }
    
    // depth is number of ancestor of p (include p)
    // depth root = 1
    public int depth(Position<E> p){
        if(this.isRoot(p))return 1;
        else return 1 + depth(parent(p));
    }
    
    // max depth of p in its position
    // height of leaves = 1
    public int height1(Position<E> p){
        if(this.isExternal(p)) return 1;
        else{
            Node<E> parent = this.validate(p);
            Iterator<Node<E>> tmpResult = parent.getChildren().EleIterator();
            int maxHeight = height1(tmpResult.next());
            while(tmpResult.hasNext()){
                maxHeight = Math.max(maxHeight , height1(tmpResult.next()));
            }
            return 1 + maxHeight;
        }
    }
    
    public int height(Position<E> p){
        int h = 1;
        for(Position<E> c : this.children(p)){
            h = Math.max(h,1+height(c));
        }
        return h;
    }
    
    private void resetTraverse(){this.traversal = new ArrayList<>(this.size());}
    private void visit(Position<E> vertice){this.traversal.add(vertice);}
    
    private void breathTraverse(Position<E> p){
        List<Position<E>> list = new ArrayList<>();
        list.add(p);
        while(!list.isEmpty()){
            Position<E> visited = list.remove(0);
            this.visit(visited);
            for(Position<E> c : this.children(visited)){
                list.add(c);
            }
        }
    }
    
    private void postoderTraverse(Position<E> p){
        for(Position<E> c: this.children(p)) postoderTraverse(c);
        this.visit(p);
    }
    
    private void preoderTraverse(Position<E> p){
        this.visit(p);
        for(Position<E> child: this.children(p)) preoderTraverse(child);
    } 
    
    private void inoderTraverse(Position<E> p){
        try{
            Node<E> tmpNode = this.validate(p);
            Position<E> leftMost = tmpNode.getChildren().first().getElement();
            inoderTraverse(leftMost);
            this.visit(p);
            for(Position<E> item : children(p)) if(item != leftMost) inoderTraverse(item);
        }catch(NullPointerException e){
            // when left most child is null
            this.visit(p);
        }   
    }
    
    /**
     * default is breath first
     * @return iterable of position upon prefix order
     */
    @Override
    public Iterable<Position<E>> positions() {
        return positions(3);
    }
    
    /**
     * search tree rooted by root
     * 0-prefix order,
     * 1-in order,
     * 2-post order,
     * 3-breath first
     * @return iterable of position upon type
     */
    public Iterable<Position<E>> positions(int type) {
       return positions(this.root(),type);
    }
    
    /**
     * search tree rooted by p
     * 0-prefix order,
     * 1-in order,
     * 2-post order,
     * 3-breath first
     * @return iterable of position upon type
     */
    public Iterable<Position<E>> positions(Position<E> p,int type) {
        this.resetTraverse();
        this.validate(p);
        switch(type){
            case 0 -> this.preoderTraverse(p);
            case 1 -> this.inoderTraverse(p);
            case 2 -> this.postoderTraverse(p);
            case 3 -> this.breathTraverse(p);
        }
        return this.traversal;
    }
    

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
