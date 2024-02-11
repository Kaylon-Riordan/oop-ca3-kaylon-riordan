import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

public class CA3_Question2
{
    /*
        Starter function to create the 2D array and populate it with 0
     */
    public static int[][]  floodFillStart() {
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                arr[x][y] = 0;
            }
        }
       return arr;
    }
    /*
        Helper function to display the image
     */
    public static void display(int[][] arr)
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }
    private static void fill(int r, int c, int[][] arr)
    {
        //push the starting position onto the stack of coordinates
        Stack<Pair> coordinates = new Stack<>();
        coordinates.push(new Pair(r, c));
        int count = 1;


        while(!coordinates.isEmpty())
        {
            // get the coordinate on top of the stack
            Pair point = coordinates.pop();

            // if the coordinate is currently empty, set it to count and increase count
            if(arr[point.getRow()][point.getColumn()] == 0)
            {
                arr[point.getRow()][point.getColumn()] = count;
                count++;
            }

            // if North, South, East, West points are in bounds and empty, push them onto the stack
            if(point.getRow()-1 >= 0 && arr[point.getRow()-1][point.getColumn()] == 0)
            {
                coordinates.push(new Pair(point.getRow()-1,point.getColumn()));
            }
            if(point.getColumn()+1 < 10 && arr[point.getRow()][point.getColumn()+1] == 0)
            {
                coordinates.push(new Pair(point.getRow(),point.getColumn()+1));
            }
            if(point.getRow()+1 < 10 && arr[point.getRow()+1][point.getColumn()] == 0)
            {
                coordinates.push(new Pair(point.getRow()+1,point.getColumn()));
            }
            if(point.getColumn()-1 >= 0 && arr[point.getRow()][point.getColumn()-1] == 0)
            {
                coordinates.push(new Pair(point.getRow(),point.getColumn()-1));
            }
        }
        // display final array after while loop has ended
        display(arr);
    }

    public static void start()
    {
        //use starter functions to create empty 2D array, then take in the starting position using a scanner
        int[][] arr = floodFillStart();

        Scanner kb = new Scanner(System.in);

        System.out.print("\nStarting row: ");
        int r = kb.nextInt();
        System.out.print("\nStarting column: ");
        int c = kb.nextInt();

        fill(r, c, arr);
    }
    public static void main(String[] args) {
        start();
    }

}
