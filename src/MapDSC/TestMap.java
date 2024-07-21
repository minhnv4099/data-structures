/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;

import java.util.Iterator;
import ListDSC.ArrayList;
import java.lang.Comparable;

/**
 *
 * @author nguyenminh
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String,Integer> map = new SortedTableMap<>() ;
        ArrayList<String> d = new ArrayList<>();
        map.put("se66", 1);
        map.put("se23", 2);
        map.put("se31", 3);
        map.put("se43", 4);
        map.put("se20", 5);
        map.put("se11", 5);
        map.put("se40", 5);
        map.put("se36", 5);
        map.put("se99", 5);
        for(Entry<String,Integer> e : map.entrySet()){
            d.add(e.getKey());
        }
        Iterator<String> iter = d.iterator();
        while(iter.hasNext()){
            if(iter.next().equals("se66")){
                iter.remove();
            }
        }
        
        System.out.println(d.size());
        //System.out.println(map.get("se123"));
        
        
    }
}
