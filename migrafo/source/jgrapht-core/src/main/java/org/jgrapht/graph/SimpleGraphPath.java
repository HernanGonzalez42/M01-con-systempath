/*
 * (C) Copyright 2003-2016, by Barak Naveh and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
package org.jgrapht.graph;

import java.util.*;

import org.jgrapht.*;

/**
 * A vertex-based representation of a simple path. The graph must be simple for the vertices to
 * uniquely determine a path. See {@link SimpleGraph}
 *
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 *
 * @deprecated This class is ambiguous. Unlike the name or the description suggests, this class does
 *             NOT implement a Simple Path (a path in a graph without vertex repetition). Instead it
 *             implements a walk in a SimpleGraph. This functionality is now implemented by the
 *             class {@link GraphWalk}.
 */
@Deprecated
public class SimpleGraphPath<V, E>
    implements GraphPath<V, E>
{
    private SimpleGraph<V, E> graph;
    private List<V> vertices;
    private List<E> edges;
    private double weight;

    /**
     * @param simpleGraph The simple graph where the path is.
     * @param vertices A list of vertices that make up the path.
     * @param weight The weight of the path.
     *
     * @throws IllegalArgumentException if the vertices are not in the path or if they do not define
     *         a path in the graph.
     */
    public SimpleGraphPath(SimpleGraph<V, E> simpleGraph, List<V> vertices, double weight)
    {
        this.graph = simpleGraph;
        this.vertices = vertices;
        this.edges = new ArrayList<>();
        this.weight = weight;

        if (vertices.size() < 2) {
            throw new IllegalArgumentException("At least two vertices are required to form a path");
        }

        for (int i = 0; i < (getVertexList().size() - 1); i++) {
            E currentEdge = getGraph().getEdge(getVertexList().get(i), getVertexList().get(i + 1));
            if (currentEdge != null) {
                edges.add(currentEdge);
            } else {
                throw new IllegalArgumentException("The specified vertices do not form a path");
            }
        }
    }

    @Override
    public SimpleGraph<V, E> getGraph()
    {
        return this.graph;
    }

    @Override
    public V getStartVertex()
    {
        return this.getVertexList().get(0);
    }

    @Override
    public V getEndVertex()
    {
        return this.getVertexList().get(getVertexList().size() - 1);
    }

    @Override
    public List<E> getEdgeList()
    {
        return this.edges;
    }

    /**
     * @return A list of the vertices that define the path.
     */
    public List<V> getVertexList()
    {
        return this.vertices;
    }

    @Override
    public double getWeight()
    {
        return weight;
    }
}

// End SimpleGraphPath.java
