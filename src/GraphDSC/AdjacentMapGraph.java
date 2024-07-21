/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraphDSC;

import ListDSC.LinkedPositionalList;
import ListDSC.Position;
import MapDSC.Map;
import MapDSC.UnsortedTableMap;
import ListDSC.PositionalList;
import MapDSC.ProbeHashMap;
import MapDSC.SortedTableMap;
import java.util.Iterator;

/**
 * @author nguyenminh
 * @param <V> data type for element at vertex
 * @param <E> data type for element at edge
 */
public class AdjacentMapGraph<V,E> implements Graph<V, E>{
    /*
        each vertex in graph has:
            - a variable reference ele
            - a variable reference position within vertices list (efficently to remove)
            - a variale reference map of entry pair of vertices(key) which adjacent and associated edge(value)
    */
    private class InnerVertex<V> implements Vertex<V>{
        private V ele;
        // store position within vertices list of graph -> easily remove
        private Position<Vertex<V>> pos;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;
        public InnerVertex(V ele, boolean isDirected) {
            this.ele = ele;
            this.outgoing = new ProbeHashMap<>();
            if(isDirected)
                this.incoming = new ProbeHashMap<>();
            else
                this.incoming = this.outgoing;
        }
        @Override
        public V getElement(){return this.ele;}
        public Position<Vertex<V>> getPos() {return pos;}
        public void setPos(Position<Vertex<V>> pos) {this.pos = pos;}
        public Map<Vertex<V>, Edge<E>> getOutgoing() {return outgoing;}
        public Map<Vertex<V>, Edge<E>> getIncoming() {return incoming;} 
    }
    /*
      each edge in graph has:
        - a variable reference element
        - a variable reference position within edges list (efficently to remove)
        - a variable reference array containing 2 incident vertices that edge
     */
    private class InnerEdge<E> implements Edge<E>{
        private E ele;
        // store position within edges list of graph -> easily remove
        private Position<Edge<E>> pos;
        private Vertex<V>[] endpoints;

        public InnerEdge(E ele, Vertex<V> u, Vertex<V> v) {
            this.ele = ele;
            this.endpoints = (Vertex<V>[]) new Vertex[]{u,v};
        }
        @Override
        public E getElement() {return ele;}
        public Position<Edge<E>> getPos() {return pos;}
        public void setPos(Position<Edge<E>> pos) {this.pos = pos;}
        public Vertex<V>[] getEndpoints() {return endpoints;}
    }
    
    /** determine the graph whether is directed or undirected*/
    private boolean isDirected;
    // because when adding vertex or edge to list return position -> assign for reference position of vertex, edge
    /** list containing all vertices of graph*/
    private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    /** list containing all edges of graph*/
    private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();
    
    public AdjacentMapGraph() {
        // default the graph is undirected
        this(false);
    }
    
    public AdjacentMapGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }
    
    private InnerVertex<V> validate(Vertex<V> v) throws IllegalArgumentException{
        if(!(v instanceof InnerVertex)) throw new IllegalArgumentException("Invalid vertex v");
        InnerVertex<V> tmpVertex = (InnerVertex<V>) v;
        if(tmpVertex.getPos() == null) throw new IllegalArgumentException("Vertex v is not in graph");
        return tmpVertex;
    }
    
    private InnerEdge<E> validate(Edge<E> e) throws IllegalArgumentException{
        if(!(e instanceof InnerEdge)) throw new IllegalArgumentException("Invalid edge e");
        InnerEdge<E> tmpEdge = (InnerEdge<E>) e;
        if(tmpEdge.getPos() == null) throw new IllegalArgumentException("Edge e is not in graph");
        return tmpEdge;
    }

    @Override
    public int numVertices() {return this.vertices.size();}

    @Override
    public Iterable<Vertex<V>> vertices() {return this.vertices;}

    @Override
    public int numEdges() {return this.edges.size();}

    @Override
    public Iterable<Edge<E>> edges() {return this.edges;}

    @Override
    public Edge<E> getEdge(Vertex<V> from, Vertex<V> to) throws IllegalArgumentException {
        InnerVertex<V> origin = this.validate(from);
        InnerVertex<V> des = this.validate(to);
        return origin.getOutgoing().get(des);
    }
    @Override
    public Vertex<V>[] endPoints(Edge<E> e) throws IllegalArgumentException {return this.validate(e).getEndpoints();}

    @Override
    public Vertex<V> opposite(Edge<E> e, Vertex<V> v) throws IllegalStateException, IllegalArgumentException {
        Vertex<V>[] tmp = this.endPoints(e);
        validate(v);
        if(v == tmp[0])
            return tmp[1];
        else if(v == tmp[1])
            return tmp[0];
        else
            throw new IllegalStateException("e is not incident edge of v");
    }

    @Override
    public int outDegrees(Vertex<V> v) throws IllegalArgumentException {return this.validate(v).getOutgoing().size();}

    @Override
    public int inDegrees(Vertex<V> v) throws IllegalArgumentException {return this.validate(v).getIncoming().size();}

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {return this.validate(v).getOutgoing().values();}

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {return this.validate(v).getIncoming().values();}

    @Override
    public Vertex<V> insertVertex(V ele) {
        InnerVertex<V> newVertex = new InnerVertex<>(ele,this.isDirected);
        // set position for new Vertex within the list of vertices
        newVertex.setPos(this.vertices.addLast(newVertex));
        return newVertex;   
    }
    
    @Override
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> u, E ele) throws IllegalArgumentException, IllegalStateException {
        InnerVertex<V> origin = this.validate(v);
        InnerVertex<V> des = this.validate(u);
        if(this.getEdge(origin, des) == null){
            InnerEdge<E> newEdge = new InnerEdge<>(ele,origin,des);
            // set position for new Edge
            newEdge.setPos(this.edges.addLast(newEdge));
            origin.getOutgoing().put(des, newEdge);
            des.getIncoming().put(origin, newEdge);
            return newEdge;
        }else
            throw new IllegalStateException("There was already edge between 2 that vertices");
    }

    @Override
    public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
        InnerVertex<V> tmpVertex = this.validate(v);
        // re construct iteration
        
        // remove edge whose origin is v
        while(this.outgoingEdges(v).iterator().hasNext())
            this.removeEdge(this.outgoingEdges(v).iterator().next());
        // remove edge whose destination is v
        while(this.incomingEdges(v).iterator().hasNext())
            this.removeEdge(this.incomingEdges(v).iterator().next());

       
        // remove vertex from E finally
        this.vertices.remove(tmpVertex.getPos());
        // set position of vertex null -> that vertex is not in vertices list of graph
        tmpVertex.setPos(null);
    }
    
    @Override
    public void removeEdge(Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> tmpEdge = this.validate(e);
        // get 2 endpoints
        InnerVertex<V> origin = this.validate(tmpEdge.getEndpoints()[0]);
        InnerVertex<V> des = this.validate(tmpEdge.getEndpoints()[1]);
        // remove edge from origin point and des point
        origin.getOutgoing().remove(des);
        des.getIncoming().remove(origin);
        // removed base on position
        this.edges.remove(tmpEdge.getPos());
        // set position of edge null -> that edge is not in edges list of graph
        tmpEdge.setPos(null);   
    }
}