/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraphDSC;
import ListDSC.PositionalList;
import MapDSC.Map;
import java.util.Set;
import QueuDSC.LinkedQueue;
import ListDSC.LinkedPositionalList;
import MapDSC.ProbeHashMap;
import MapDSC.Entry;
import MapDSC.UnsortedTableMap;
import java.util.HashSet;
import PriorityQueueDSC.HeapAdaptablePriorityQueue;
import TreeDSC.BasicTree;
/**
 *
 * @author nguyenminh
*/

public class TraversalGraph{
    /**
     * traverse graph from v follow DEPTH FIRST SEARCH
     * @param <V> vertex's element
     * @param <E> edge's element
     * @param g given graph
     * @param v beginning vertex
     * @param visited set of vertices visited
     * @param forest map keep track during traversal pair of (vertex, edge reach vertex) -> DFS tree
     */
    public static <V,E> void DepthFirstSearch(Graph<V,E> g, Vertex<V> v, Set<Vertex<V>> visited, Map<Vertex<V>, Edge<E>> forest){
        // visit current vertex
        visited.add(v);
        // walk one to one edges that are incident to v
        for(Edge<E> e : g.outgoingEdges(v)){
            // find adjacent vertice of v
            Vertex<V> u = g.opposite(e, v);
            if(!visited.contains(u)){
                // additional to store discoery edge
                // v -> u throug edge e
                forest.put(u, e);
                DepthFirstSearch(g, u, visited, forest);
            }
        }
    }
    /**
     * traverse graph from v follow BREATH FIRST SEARCH, implement with the same technique in tree
     * @param <V> vertex's element
     * @param <E> edge's element
     * @param g given graph
     * @param v beginning vertex
     * @param visited set of vertices visited
     * @param forest map keep tracking during traversal pair of (vertex, edge reach vertex)
     */
    public static <V,E> void BreathFirstSearch(Graph<V,E> g, Vertex<V> v, Set<Vertex<V>> visited, Map<Vertex<V>, Edge<E>> forest){
        LinkedQueue<Vertex<V>> buffer = new LinkedQueue<>();
        buffer.enqueu(v);
        while(!buffer.isEmpty()){
            Vertex<V> tmp = buffer.dequeu();
            visited.add(tmp);
            for(Edge<E> e : g.outgoingEdges(tmp)){
                Vertex<V> u = g.opposite(e,tmp);
                if(!visited.contains(u)){
                    forest.put(u, e);
                    buffer.enqueu(u);
                }
            }
        }
    }
    /**
     * construct path of vertices/edges from vertex u to vertex v
     * @param <V> parameter type for vertex's element
     * @param <E> parameter type for edge's element
     * @param g graph in which we find path
     * @param u beginning vertex on path
     * @param v end vertex on path
     * @param forest given map of discovery edges was computed by DFS method
     * @return Path from u to v
     */
    public static <V,E> PositionalList<Vertex<V>> findPath(Graph<V,E> g, Vertex<V> u, Vertex<V> v, Map<Vertex<V>, Edge<E>> forest){
        LinkedPositionalList<Vertex<V>> path = new LinkedPositionalList<>();
        // check v whether is in graph
        if(forest.get(v) != null){
            // start with destination vertex
            Vertex<V> walk = v;
            // when v != u
            while(walk != u){
                // determine edge used to reach v
                Edge<E> edge = forest.get(walk);
                // add walk to path
                path.addFirst(walk);
                // determine vertex reach to v
                walk = g.opposite(edge, walk);
                // repeate while loop with v as walk
            }
        }
        // add beginning vertex
        path.addFirst(u);
        return path;
    }
    // depth first search all connected-components
    public static <V,E> Map<Vertex<V>,Edge<E>> DFScomplete(Graph<V,E> g, Vertex<V> v){
        Map<Vertex<V>, Edge<E>> forest = new UnsortedTableMap<>();
        Set<Vertex<V>> visited = new HashSet<>();
        for(Vertex<V> u: g.vertices()){
            if(!visited.contains(u))
                DepthFirstSearch(g, u, visited, forest);
        }
        return forest;
    }
    /** Compute shortest-path length from vertex s to all reachable vertex of g*/
    public static <V,E> Map<Vertex<V>,Edge<Integer>> shortestPath(Graph<V,Integer> g, Vertex<V> s){
        
        // map of distance from source to every vertex v with weight
        Map<Vertex<V>,Integer> d = new UnsortedTableMap<>();
        // map of every reachable vertex v by vertex source with value as length of best path from s to v
        Map<Vertex<V>,Integer> cloud = new UnsortedTableMap<>();
        // map length of distance from v to reachable vertex to that vertex
        HeapAdaptablePriorityQueue<Integer,Vertex<V>> listPath = new HeapAdaptablePriorityQueue<>();
        
        // containing vertices outside the cloud
        // map from each vertex to entry in lishPath
        Map<Vertex<V>, Entry<Integer,Vertex<V>>> tokens = new UnsortedTableMap<>();
        
        // initial length of distance for every veritces 
        for(Vertex<V> v : g.vertices()){
            if(v != s)
                d.put(v, Integer.MAX_VALUE);
            else
                d.put(v, 0);
            // also insert to list path
            // also to map token
            tokens.put(v, listPath.insert(d.get(v),v));
        } 
        
        while(!listPath.isEmpty()){
            // get vertex has minimal weight
            Entry<Integer,Vertex<V>> current = listPath.removeMin();
            Vertex<V> v = current.getValue();
            // each vertex removed from listPath is reached by source -> added to cloud
            cloud.put(v, current.getKey());
            // as well as token has not contained that vertex yet
            tokens.remove(v);
            for(Edge<Integer> e: g.outgoingEdges(v)){
                // u is reached directly by v
                Vertex<V> u = g.opposite(e, v);
                // u is not in cloud
                if(cloud.get(u)==null){
                    // w(v,u)
                    int weight = e.getElement();
                    // compare new length and previous length
                    if(d.get(v)+weight < d.get(u)){
                        // update distance from v to u  in distance map
                        d.put(u, weight+d.get(v));
                        // update key entry in adapta heap for next selecting the minimal entry 
                        listPath.replaceKey(tokens.get(u),d.get(u));
                    }
                }
            }
        }
        // at the end, d map vertex u is reached from s with length of shortest distance
        // determine actually path
        // every edge reach vertex u is shortest path
        return shortestPathTree(g, s, d);
    }
    
    public static <V> Map<Vertex<V>,Edge<Integer>> shortestPathTree(Graph<V,Integer> g,Vertex<V> s, Map<Vertex<V>, Integer> d){
        Map<Vertex<V>, Edge<Integer>> tree = new UnsortedTableMap<>();
        for(Vertex<V> v : d.keySet()){
            if(v != s){
                // edge reachs to v
                for(Edge<Integer> e: g.incomingEdges(v)){
                    // vertex reaches to v
                    // mean u is before v in the path
                    Vertex<V> u = g.opposite(e, v);
                    int weight = e.getElement();
                    if(d.get(u) + weight == d.get(v)){
                        // shortest edge reach v
                        tree.put(v, e);
                    }
                }
            }
        }
        return tree;
    }
    
   /**
    * Transitive closure of directed graph G is also directed graph G'.  
    * G' has the same vertices as G and edge(u,v) if in G has directed path from u to v 
    * number vertices in graph by v[k], for k = 1,2,3,4,..n
    * construct: for G[k] = G[k-1], add to G[k] edge(u,v) if G[k-1] contains both of edge(u,k) and edge(k,v).
    * We can easily test there is path between 2 vertices
    * @param <V> type for element of vertex
    * @param <E> type for element of edge
    * @param g given graph
    * @return void, pair of any 2 vertices has an edge (it seem like complete graph)
    */
    public static <V,E> void transitiveClosure(Graph<V,E> g){
        for(Vertex<V> k: g.vertices()){
            for(Vertex<V> i: g.vertices()){
                if(k!=i && g.getEdge(i, k) != null){
                    for(Vertex<V> j: g.vertices()){
                        if(j!=k && j!=i && g.getEdge(k, j) != null && (g.getEdge(i, j)==null))
                            g.insertEdge(i, j, null);
                    }
                }
            }
        }
    }
    
    public static <V> Map<Vertex<V>, Edge<Integer>> findPrimJarnikTree(Graph<V,Integer> g, Vertex<V> s){
        Map<Vertex<V>, Integer> d = new ProbeHashMap<>();
        Map<Vertex<V>, Edge<Integer>> tree = new ProbeHashMap<>();
        Map<Vertex<V>, Edge<Integer>> forest = new ProbeHashMap<>();
        HeapAdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Entry<Integer,Vertex<V>>> tokens = new ProbeHashMap<>();
        
        for(Vertex v : g.vertices()){
            if(v == s){
                d.put(v, 0);
            }else{
                d.put(v, Integer.MAX_VALUE);
            }
            tokens.put(v, pq.insert(d.get(v), v));
            forest.put(v, null);
        }
        while(!pq.isEmpty()){
            
            Entry<Integer, Vertex<V>> current = pq.removeMin();
            Vertex<V> u = current.getValue();
            // 
            tree.put(u, forest.remove(u));
            tokens.remove(u);
            for(Edge<Integer> e : g.outgoingEdges(u)){
                Vertex<V> v = g.opposite(e, u);
                if(tree.get(v) == null){
                    int weight = e.getElement();
                    if(d.get(v) > weight){
                        // update edge that reach v
                        forest.put(v,e);
                        d.put(v, weight);
                        pq.replaceKey(tokens.get(v),d.get(v));
                    }
                }
            }   
        }
        tree.remove(s);
        return tree;   
    }
}
