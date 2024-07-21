/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;

import ListDSC.Position;
import java.util.Comparator;

/**
 *
 * @author nguyenminh
 */
public class SplayTreeMap<K,V> extends TreeMap<K, V> {

    public SplayTreeMap() {super();}
    public SplayTreeMap(Comparator<K> comp) {super(comp);}
    // move p to root of tree
    protected void splay(Position<Entry<K,V>> p){
        while(!this.isRoot(p)){
            Position<Entry<K,V>> parent = this.parent(p);
            Position<Entry<K,V>> grand = this.parent(parent);
            if(grand == null){                      // zig
                this.tree.rotate(p);
            }else if((p == leftChild(parent)) == (parent == leftChild(grand))){     // zig-zig
                this.tree.rotate(parent);
                this.tree.rotate(p);
            }else{                                                                      // zig-zag
                this.tree.rotate(p);
                this.tree.rotate(p);
            }
        }
    }
    @Override
    protected void rebalanceRemove(Position<Entry<K, V>> p) {
        if(!isRoot(p)) splay(parent(p));
    }

    @Override
    protected void rebalanceAccess(Position<Entry<K, V>> p) {
        splay(p);
    }

    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        if(isExternal(p) && parent(p) != null) splay(parent(p));
        
    }
}
