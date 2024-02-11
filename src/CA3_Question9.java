import java.util.Arrays;
import java.util.Stack;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

/*
Direction enum used to indicate direction.
 */
enum DIRECTION {NORTH, SOUTH,EAST,WEST};

// path class stores coordinates x, y, and a direction
class Path
{
    private int x, y;
    private DIRECTION dir;
    public Path(int x, int y, DIRECTION dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public DIRECTION getDir() {
        return dir;
    }
    public void setDir(DIRECTION dir) {
        this.dir = dir;
    }
}

public class CA3_Question9
{
    // fill and 8x8 2D array with the number 0 at every point
    public static int[][] fillMaze()
    {
        int[][] maze = new int[8][8];
        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                maze[x][y] = 0;
            }
        }
        // call the create path method 4 times to add paths off 1s to the maze
        maze = createPath(maze, 1, 1, 6, 'x');
        maze = createPath(maze, 1, 4, 6, 'y');
        maze = createPath(maze, 3, 0, 7, 'x');
        maze = createPath(maze, 5, 1, 4, 'x');
        return maze;
    }

    public static int[][] createPath(int[][] maze, int x, int y, int length, char direction)
    {
        // replace 0s with 1s starting at pint "x" "y", moving along the axis "direction", going for "length" spaces
        if(direction == 'x')
        {
            for (int z = y; z < y+length; z++)
            {
                maze[x][z] = 1;
            }
        }
        else
        {
            for (int z = x; z < x+length; z++)
            {
                maze[z][y] = 1;
            }
        }
        return maze;
    }

    public static void display(int[][] image)
    {
        for (int x = 0; x < image.length; x++)
        {
            for (int y = 0; y < image[0].length; y++)
            {
                System.out.printf("%4d", image[x][y]);
            }
            System.out.println();
        }
        System.out.println("");
    }

    public static Path solve(Path path, int[][] maze)
    {
        int x = path.getX();
        int y = path.getY();
        DIRECTION dir =  path.getDir();

        // code for copying a 2D array without changing original learned here https://stackoverflow.com/questions/1686425/copy-a-2d-array-in-java
        int [][] mazeCopy = new int[maze.length][];
        for(int i = 0; i < maze.length; i++)
        {
            mazeCopy[i] = maze[i].clone();
        }
        mazeCopy[x][y] = 2;

        // while loop that only stops after break command
        while(true)
        {
            boolean junction = false;
            // move one space in the correct directing then check if there is an open space either side to find a junction
            if(dir == DIRECTION.NORTH)
            {
                x--;
                if(mazeCopy[x][y+1] != 0 || mazeCopy[x][y-1] != 0)
                {
                    junction = true;
                }
            }
            else if(dir == DIRECTION.EAST)
            {
                y++;
                if(mazeCopy[x+1][y] != 0 || mazeCopy[x-1][y] != 0)
                {
                    junction = true;
                }
            }
            else if(dir == DIRECTION.SOUTH)
            {
                x++;
                if(mazeCopy[x][y+1] != 0 || mazeCopy[x][y-1] != 0)
                {
                    junction = true;
                }
            }
            else
            {
                y--;
                if(mazeCopy[x+1][y] != 0 || mazeCopy[x-1][y] != 0)
                {
                    junction = true;
                }
            }

            if(mazeCopy[x][y] == 0)
            {
                // send altered path that signifies dead end
                display(mazeCopy);
                return new Path(x, -1, dir);
            }

            // update the current position to equal a 2
            mazeCopy[x][y] = 2;

            if(x == 0 || x == 7 || y == 0 || y == 7)
            {
                // send altered path that signifies maze has been exited
                display(mazeCopy);
                return new Path(-1, y, dir);
            }
            else if (junction)
            {
                // send unaltered path if it's a junction point
                display(mazeCopy);
                return new Path(x, y, dir);
            }
        }
    }

    public static void main(String[] args)
    {
        // call fill maze to make the maze array
        int[][] maze = fillMaze();
        int x = 3;
        int y = 4;
        Stack<Path> paths = new Stack<>();

        //push the 4 directions leading off the starting point onto the stack
        paths.push(new Path(x, y, DIRECTION.WEST));
        paths.push(new Path(x, y, DIRECTION.SOUTH));
        paths.push(new Path(x, y, DIRECTION.EAST));
        paths.push(new Path(x, y, DIRECTION.NORTH));

        System.out.println("Key: 0 = wall, 1 = open space, 2 = current path\n");

        while(!paths.isEmpty())
        {
            // while the stack isn't empty, solve the top value
            Path current = solve(paths.pop(), maze);

            // if x is -1 signifying maze escaped, end the loop and tell the user
            if(current.getX() == -1)
            {
                System.out.println("Maze Escaped!");
                break;
            }
            // else if it y isn't -1 signifying a dead end, add all connecting paths to the junction except the one you were already coming from
            else if(current.getY() != -1)
            {
                if(current.getDir() != DIRECTION.EAST)
                {
                    paths.push(new Path(current.getX(), current.getY(), DIRECTION.WEST));
                }
                if(current.getDir() != DIRECTION.NORTH)
                {
                    paths.push(new Path(current.getX(), current.getY(), DIRECTION.SOUTH));
                }
                if(current.getDir() != DIRECTION.WEST)
                {
                    paths.push(new Path(current.getX(), current.getY(),DIRECTION.EAST));
                }
                if(current.getDir() != DIRECTION.SOUTH)
                {
                    paths.push(new Path(current.getX(), current.getY(), DIRECTION.NORTH));
                }
            }
        }
    }

}
