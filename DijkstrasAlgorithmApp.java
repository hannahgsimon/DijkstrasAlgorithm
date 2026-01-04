package dijkstrasalgorithmapp;
import java.util.*;
import java.io.*;

/**
 *
 * @author hannahgsimon
 */

public class DijkstrasAlgorithmApp
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(System.in);
        int[][] graph = new int[0][0];
        int size = 0;
        int weight;
        int neighbor;
        int totalCost = 0;
        
        System.out.println("Welcome to CIS265 Graph Assignment!");
        
        int selection = 4; //arbitrary        
        while (selection != 0)
        {
            selection = 4; //arbitrary
            System.out.println("""
                               1) Author Info
                               2) Load Graph
                               3) Traverse Graph
                               0) Exit Program""");          
            try
            {
                selection = input.nextInt();
                if ((selection < 0) || (selection > 3))
                {
                    System.err.println("Error; Please enter an integer 0-3.");
                }
            }
            catch (InputMismatchException e)
            {
                System.err.println("Error; Please enter an integer 0-3.");
                input.next();
            }
            input.skip(".*");
            
            switch (selection)
            {
                case 1:
                    System.out.printf("\nAuthor Name: Hannah G. Simon\n\n");
                    break;
                    
                case 2:
                    System.err.println("Note to the person running this program: From what I can tell, file directory is different on different devices.\n"
                            + "What works on my device to load in the file won't necessary work on other devices.\n"
                            + "To solve this, the commented-out parts of the code can be replicated for your device to load the files in.\n");
                    try
                    {
                        System.out.println("Working Directory = " + System.getProperty("user.dir"));
                        System.out.printf("\nEnter the file name of graph you want to traverse: ");
                        String filename = input.next();
                        System.out.println(filename + " has successfully been added as the file.");
                        input.skip(".*");
                        File file = new File(System.getProperty("user.dir") + "\\src\\" + filename + ".txt");
                        Scanner fIn = new Scanner(file);
                        
                        size = fIn.nextInt();
                        graph = new int[size][size];
                        for (int i = 0; i < graph.length; i++)
                        {
                            for (int j = 0; j < graph.length; j++)
                            {
                                graph[i][j] = -1;
                            }
                        }
                        System.out.println();
                        for (int i = 0; i < size; i++)
                        {
                            int numNeighbors = fIn.nextInt();
                            for (int j = 0; j < 2 * numNeighbors; j += 2)
                            {
                                weight = fIn.nextInt();
                                System.out.print("Weight: " + weight + " ");

                                neighbor = fIn.nextInt();
                                System.out.print("Vertex" + i + " Neighbor: " + neighbor + " ");

                                graph[i][neighbor] = weight;
                            }
                            System.out.println();
                            fIn.nextLine();
                        }

                        for (int i = 0; i < graph.length; i++)
                        {
                            for (int j = 0; j < graph[i].length; j++)
                            {
                                System.out.print(graph[i][j] + " ");
                            }
                            System.out.println();
                        }
            //            while (fIn.hasNextLine())
            //            {
            //                int i = fIn.nextInt();
            //                System.out.println(i);
            //            }
                        System.out.println();
                        fIn.close();
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }                                
                    break;
                    
                case 3:
                    if (size == 0)
                    {
                        System.out.println("Graph has not been loaded yet; please load graph to traverse.\n");
                        break;
                    }
                    int startingVertex = 0;
                    while (startingVertex != -1)
                    {
                        System.out.printf("\nEnter a starting vertex, -2 to find the shortest path to all other vertices, or -1 to exit the graph traversal menu: ");
                        boolean gotCorrect = false;
                        while (!gotCorrect)
                        {
                            try
                            {
                                startingVertex = input.nextInt();
                                if (startingVertex == -2)
                                {
                                    dijkstraAlgorithmStartingVertex(graph);
                                    gotCorrect = true;
                                }
                                else if (startingVertex == -1)
                                {
                                    System.out.println("Exiting graph traversal menu.");
                                    gotCorrect = true;
                                }
                                else if ((startingVertex < 0) || (startingVertex > size - 1))
                                {
                                    System.err.println("Error; Please enter an integer between 0 and " + (size - 1));
                                }
                                else
                                {
                                   System.out.println(startingVertex + " has successfully been added as the starting vertex.\n");
                                   traverse(startingVertex, graph, totalCost);
                                   gotCorrect = true;
                                }  
                            }
                            catch (InputMismatchException e)
                            {
                                System.err.print("Error; Please enter an integer number.\n\n");
                                input.next();
                            }
                            input.skip(".*"); 
                        }
                    }                    
                    break;
                    
                case 0:
                    System.out.println("Program exited by user.");
                    break;
            }
        } 
    }
    
    public static void traverse (int startingVertex, int[][] graph, int totalCost)
    {
        Scanner input = new Scanner(System.in);
        boolean hasNeighbor = false;
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < graph.length; i++)
        {
            if (graph[startingVertex][i] != -1)
            {
                if (graph[startingVertex][i] == graph[i][startingVertex])
                {
                    System.out.println("Neighbor " + i + " has a cost of " + graph[startingVertex][i] + ", link is bidirectional.");
                }
                else
                {
                   System.out.println("Neighbor " + i + " has a cost of " + graph[startingVertex][i] + ", link is unidirectional."); 
                }
                neighbors.add(i);
                hasNeighbor = true;
            }
        }
        if (hasNeighbor == false)
        {
            System.out.println(startingVertex + " has no neighbors, cannot travel to another neighbor.");
        }
        if (hasNeighbor == true)
        {
            System.out.printf("\nPick a neighbor to travel to, or press -1 to exit this traversal: ");
            boolean gotCorrect = false;
            while (!gotCorrect)
            try
            {
                int neighborSelection = input.nextInt();
                if (neighborSelection == -1)
                {
                    System.out.println("Exiting current traversal.");
                    gotCorrect = true;
                }
                else if (!neighbors.contains(neighborSelection))
                {
                    System.err.println("Error; Please enter a valid neighbor. Options are: " + Arrays.deepToString(neighbors.toArray()));
                }
                else
                {
                    System.out.println("Successfully traveled to vertex " + neighborSelection + ".");
                    totalCost += graph[startingVertex][neighborSelection];
                    System.out.printf("Total cost incurred so far is " + totalCost + "\n\n");
                    gotCorrect = true;
                    traverse(neighborSelection, graph, totalCost);
                }
            }
            catch (InputMismatchException e)
            {
                System.err.println("Error; Please enter an integer number.");
                input.next();
            }
        }   
    }
    
    public static void dijkstraAlgorithmStartingVertex (int[][] graph)
    {
         Scanner input = new Scanner(System.in);
         int startingVertex = 0;
         while (startingVertex != -1)
         {
            System.out.printf("\nEnter a starting vertex, or -1 to exit Dijkstra's Algorithm: ");
            boolean gotCorrect = false;
            while (!gotCorrect)
            {
                try
                {
                    startingVertex = input.nextInt();
                    if (startingVertex == -1)
                    {
                        System.out.println("Exiting Dijkstra's Algorithm.");
                        gotCorrect = true;
                    }
                    else if ((startingVertex < 0) || (startingVertex > graph.length - 1))
                    {
                        System.err.println("Error; Please enter an integer between 0 and " + (graph.length - 1));
                    }
                    else
                    {
                       System.out.println(startingVertex + " has successfully been added as the starting vertex.\n");
                       dijkstraAlgorithmSetup(graph, startingVertex);
                       gotCorrect = true;
                    }  
                }
                catch (InputMismatchException e)
                {
                    System.err.print("Error; Please enter an integer number.\n\n");
                    input.next();
                }
                input.skip(".*"); 
            }
        }        
    }
     
    public static void dijkstraAlgorithmSetup (int[][] graph, int startingVertex)
    {
        ArrayList<Boolean> known = new ArrayList<>(graph.length);
        ArrayList<Integer> cost = new ArrayList<>(graph.length);
        ArrayList<Integer> path = new ArrayList<>(graph.length);
        //Set<Integer> path = new HashSet<Integer>(graph.length);
        
        for (int i = 0; i < graph.length; i++)
        {
            known.add(i, false);
            cost.add(i, Integer.MAX_VALUE);
            path.add(i, -1);
        }
        
        known.set(startingVertex, true);
        cost.set(startingVertex, 0);
        path.set(startingVertex, -1);
        
       boolean hasNeighbor = false;
       ArrayList<Neighbor> neighbors = new ArrayList<>();
       for (int i = 0; i < graph.length; i++)
       {
           if (graph[startingVertex][i] != -1)
           {
               neighbors.add(new Neighbor(i, graph, startingVertex));
               hasNeighbor = true;
           }
       }

       if (hasNeighbor == true)
       {
           new Sort().selectionSortAscendingCost(neighbors);
           for (int i = 0; i < neighbors.size(); i ++)
           {
               cost.set(neighbors.get(i).getVertex(), neighbors.get(i).getCost());
               path.set(neighbors.get(i).getVertex(), startingVertex);
           }
           int nextVertex = neighbors.get(0).getVertex();
           dijkstraAlgorithm(graph, nextVertex, known, cost, path);
       }
       else
       {
           printPath(path, cost);
       }
        
    }
    
    public static void dijkstraAlgorithm (int[][] graph, int nextVertex, ArrayList<Boolean> known, ArrayList<Integer> cost, ArrayList<Integer> path)
    {
        known.set(nextVertex, true);
        boolean hasNeighbor = false;
        
       ArrayList<Neighbor> neighbors = new ArrayList<>();
       for (int i = 0; i < graph.length; i++)
       {
           if (graph[nextVertex][i] != -1)
           {
               neighbors.add(new Neighbor(i, graph, nextVertex));
               hasNeighbor = true;
           }
       }

       if (hasNeighbor == true)
       {
           new Sort().selectionSortAscendingCost(neighbors); //unncessary step
           for (int i = 0; i < neighbors.size(); i ++)
           {
               if (known.get(neighbors.get(i).getVertex()) == false)
               {
                   if (neighbors.get(i).getCost() + cost.get(nextVertex) < cost.get(neighbors.get(i).getVertex()))
                   {
                       cost.set(neighbors.get(i).getVertex(), neighbors.get(i).getCost() + cost.get(nextVertex));
                       path.set(neighbors.get(i).getVertex(), nextVertex);                        
                   }
               }
           }
            
           Boolean nextVertexExists = false;
           for (int i = 0; i < known.size(); i ++) //find the false known vertex with lowest cost
           {
               if (known.get(i) == false && cost.get(i) != Integer.MAX_VALUE)
               {
                   nextVertex = i;
                   i = known.size(); //exit for loop
                   nextVertexExists = true;
               }
           }

           if (nextVertexExists == true)
           {
               for (int i = 0; i < known.size(); i ++) //find the false known vertex with lowest cost
               {
                   if (known.get(i) == false && cost.get(i) != Integer.MAX_VALUE)
                   {
                       if (cost.get(i) < cost.get(nextVertex))
                       {
                           nextVertex = i;
                       }
                   }
               }
               dijkstraAlgorithm(graph, nextVertex, known, cost, path);
           }
           else
           {
               printPath(path, cost);
           } 
       }
    }
     
    public static void printPath(ArrayList<Integer> path, ArrayList<Integer> cost)
    {
       for (int i = 0; i < path.size(); i++)
       {
           String pathway = String.valueOf(i) + " " + String.valueOf(path.get(i));
           System.out.println("DESTINATION_NODE_" + i + ": " + getPath(path, i, pathway, cost));
       }
    }
     
    public static String getPath(ArrayList<Integer> path, int vertex, String pathway, ArrayList<Integer> cost)
    { 
        if (cost.get(vertex) == Integer.MAX_VALUE)
        {
            return "No Path";
        }
        if (path.get(vertex) == -1)
        {
            return String.valueOf(vertex);
        }
        if (path.get(path.get(vertex)) != -1)
        {
            pathway += " " + String.valueOf(path.get(path.get(vertex)));
            return(getPath(path, path.get(vertex), pathway, cost));
        }
       StringBuilder pathwayReversed = new StringBuilder();
       pathwayReversed.append(pathway);
       pathwayReversed.reverse();
       return pathwayReversed.toString();             
    }
    
}

