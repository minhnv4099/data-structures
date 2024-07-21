/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GraphDSC;

import java.util.Iterator;

/**
 *
 * @author nguyenminh
 * interface graph is generic
 * @param <V> as data type of element at Vertex
 * @param <E> as data type of element at Edge
 */
public interface Graph<V,E> {
    /**@return number of vertices in graph*/
    int numVertices();
    /**@return iteration of all vertices in graph */
    Iterable<Vertex<V>> vertices();
    /**@return number of edges in graph**/
    int numEdges();
    /**@return iteration of all edges in graph */
    Iterable<Edge<E>> edges();
    /**
     * @param e edge
     * @return array containing 2 points adjacent that edge
     * @throws IllegalArgumentException if invalid e
     */
    Vertex<V>[] endPoints(Edge<E> e) throws IllegalArgumentException;
    
    /**
     * 
     * @param v one vertex(origin if directed)
     * @param u another vertex(destination if directed)
     * @return edge between 2 vertices, null no edge exist
     * @throws IllegalArgumentException if one of 2 vertices is invalid
     */
    Edge<E> getEdge(Vertex<V> v, Vertex<V> u) throws IllegalArgumentException;
    /**
     *
     * @param e edge 
     * @param v vertex
     * @return "sibling" vertex of v which incident e
     * @throws IllegalStateException if v is not incident e
     * @throws IllegalArgumentException if invalid e or invalid v or both
     */
    Vertex<V> opposite(Edge<E> e, Vertex<V> v) throws IllegalStateException,IllegalArgumentException;
    
    /**
     * 
     * @param v vertex
     * @return out degree of v
     * @throws IllegalArgumentException if invalid v
     */
    int outDegrees(Vertex<V> v) throws IllegalArgumentException;
    
    /**
     * 
     * @param v
     * @return int degree of v, if undirected graph same outDegree method
     * @throws IllegalArgumentException if invalid v
     */
    int inDegrees(Vertex<V> v) throws IllegalArgumentException;
    
    /**
     * 
     * @param v vertex
     * @return iteration of edges which from v
     * @throws IllegalArgumentException if invalid v
     */
    Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException;
    
    /**
     * 
     * @param v vertex
     * @return iteration of all edges which come to v
     * @throws IllegalArgumentException if invalid v
     */
    Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException;
    
    /**
     * 
     * @param ele element at new vertex
     * @return added vertex
     */
    Vertex<V> insertVertex(V ele);
    
    /**
     * add new edge between 2 vertices
     * @param ele element of new edge
     * @return added edge
     * @throws IllegalArgumentException if invalid vertex v or u or both
     * @throws IllegalStateException if edge between 2 that vertices already existed
     */
    Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E ele) throws IllegalArgumentException,IllegalStateException;
    
    /**
     * remove vertex v from the graph
     * @param v 
     * @throws IllegalArgumentException if invalid v
     */
    void removeVertex(Vertex<V> v) throws IllegalArgumentException;
    
    /**
     * remove edge from the graph
     * @param e 
     * @throws IllegalArgumentException if invalid e
     */
    void removeEdge(Edge<E> e) throws IllegalArgumentException;   
}
