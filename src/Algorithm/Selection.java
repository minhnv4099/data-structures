/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Algorithm;

import QueuDSC.LinkedQueue;
import QueuDSC.Queue;
import java.util.Comparator;

/**
 *
 * @author nguyenminh
 */
public class Selection {
    public static <K> K quickSelect(K[] s, int k){
        Queue<K> q = new LinkedQueue<>();
        for(K e: s){
            q.enqueu(e);
        }
        return quickSelect(q, k);
    }
    
    public static <K> K quickSelect(Queue<K> q, int k){
        return quickSelect(q, k, (k1,k2) -> ((Comparable)k1).compareTo((Comparable)k2));
    }
    
    public static <K> K quickSelect(Queue<K> q, int k,Comparator<K> comp){
        if(q.size() == 1) return q.first();
        if(k < 0 ||k > q.size()) return null;
        Queue<K> L = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();
        K pivot = q.first();
        while(!q.isEmpty()){
            K e = q.dequeu();
            int c = comp.compare(e, pivot);
            if(c < 0)
                L.enqueu(e);
            else if(c == 0)
                E.enqueu(e);
            else
                G.enqueu(e);
        }
        
        // kth element in L
        if(k <= L.size()){
            return quickSelect(L, k, comp);
        // kth element in E
        }else if(k <= (L.size() + E.size())){
            return pivot;
        // kth element in G
        }else{
            return quickSelect(G, k - L.size() - E.size(), comp);
        }
    }
}
