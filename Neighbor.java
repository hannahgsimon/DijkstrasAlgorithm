package project_dijkstrasalgorithmapp;

/**
 *
 * @author hannahgsimon
 */

public class Neighbor
{
    private int vertex;
    private int cost;
    
    public Neighbor(int vertex, int[][] graph, int startingVertex)
    {
        this.vertex = vertex;
        this.cost = graph[startingVertex][vertex];
    }
    
    public int getVertex()
    {
        return(vertex);
    }
    
    public int getCost()
    {
        return(cost);
    }
    
}

