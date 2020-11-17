/* Author: github.com/mcswe
 * Date: November 12, 2020
 * Purpose: count and print the number of connected components in a graph using an adjacency matrix and breadth first search.
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.LinkedList; 
import java.util.Queue;

public class Components {

    /* update element and it's counterpart in 2D array as 'true' 
     * precondition: i and j are not negative or null */
    private static void edge(boolean[][] matrix, int i, int j) {
        matrix[i][j] = true;
        matrix[j][i] = true;
    }
    
    /* insert 'false' for all elements in 2D array */
    private static void insertFalse(boolean[][] matrix, int numNodes) {
        for(int i = 0; i < numNodes; i++) {
            for(int j = 0; j < numNodes; j++) {
                matrix[i][j] = false;
            }
        }
    }

    /* return 2d array of type boolean whose elements symbolize edges in a graph */
    private static boolean[][] adjMatrix(Scanner sc) {
        int numNodes = sc.nextInt();
        boolean[][] matrix = new boolean[numNodes][numNodes];
        insertFalse(matrix, numNodes); // initialize all elements in matrix as 'false'

        while(sc.hasNextLine()) {
            int i = sc.nextInt(); 
            int j = sc.nextInt();
            edge(matrix, i, j);
        }

        return matrix;
    }

    /* return number of connected components in a graph using adjacency matrix */
    private static int search(boolean[][] matrix) {

        int size = matrix.length;
        boolean[] visited = new boolean[size]; // 1d array to track whether a node has been visited or not
        for(int i = 0; i < visited.length; i++) {
            visited[i] = false; // initally set all nodes to unvisited
        }
        
        int count = 0; // number of connected components
        for(int i = 0; i < size; i++) {
            if(visited[i] == false) {
                bfs(i, visited, matrix);
                count++;
            }
        }
        return count;
    }

    /* visit all nodes explorable from nodeID
     * precondition: nodeID is unvisitied and non-null */
    private static void bfs(int nodeID, boolean[] visited, boolean[][] matrix) {

        Queue<Integer> q = new LinkedList<Integer>();
        q.add(nodeID);
        while(! q.isEmpty()) {
            int u = q.remove();
            if(visited[u] == false) {
                visited[u] = true;
                for(int v = 0; v < visited.length; v++) {
                    if(matrix[u][v] == true) {
                        q.add(v); 
                    }
                    
                }
            }
        }
    }

    public static void main(String[] args) {

        try {
            File f = new File(args[0]);
            Scanner sc = new Scanner(f);
            boolean[][] matrix = adjMatrix(sc);
            System.out.println(search(matrix));
        }
        catch(FileNotFoundException exc) {
            System.out.println("File not found.");
        }

    }
}
