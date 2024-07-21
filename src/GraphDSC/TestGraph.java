/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraphDSC;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import GraphDSC.TraversalGraph;
import MapDSC.Map;
import MapDSC.UnsortedTableMap;

public class TestGraph {

    public static void main(String[] args) throws CloneNotSupportedException {
        Graph<String, Integer> g = new AdjacentMapGraph<>();
        Vertex<String> v1 = g.insertVertex("Tan Son Nhat");
        Vertex<String> v2 = g.insertVertex("Cam Ranh");
        Vertex<String> v3 = g.insertVertex("Nha Trang");
        Vertex<String> v4 = g.insertVertex("Da Nang");
        Vertex<String> v5 = g.insertVertex("Ha Noi");
        Vertex<String> v6 = g.insertVertex("Z Lat");
        Vertex<String> v7 = g.insertVertex("Lam Dong");
        Vertex<String> v8 = g.insertVertex("Ca Mau");
        Edge<Integer> e1 = g.insertEdge(v1, v3, 10);
        Edge<Integer> e2 = g.insertEdge(v1, v8, 9);
        Edge<Integer> e3 = g.insertEdge(v2, v4, 12);
        Edge<Integer> e4 = g.insertEdge(v3, v2, 5);
        Edge<Integer> e5 = g.insertEdge(v6, v1, 15);
        Edge<Integer> e6 = g.insertEdge(v2, v5, 29);
        Edge<Integer> e7 = g.insertEdge(v7, v8, 20);
        Edge<Integer> e8 = g.insertEdge(v1, v2, 16);
        
        g.removeVertex(v2);
        
        TraversalGraph.shortestPath(g, v1).entrySet().forEach((t) -> {
            Vertex<String> u = g.opposite(t.getValue(), t.getKey());
            System.out.println(u.getElement()+"  "+t.getValue().getElement()+"  "+t.getKey().getElement());
        });
    }
}
