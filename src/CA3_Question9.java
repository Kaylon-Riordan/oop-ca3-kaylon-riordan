import java.util.Arrays;
import java.util.Stack;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

/*
Direction enum used to indicate direction.
 */
enum DIRECTION {NORTH, SOUTH,EAST,WEST,NULL};

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
        maze = createPath(maze, 1, 1, 6, 'x');
        maze = createPath(maze, 1, 4, 6, 'y');
        maze = createPath(maze, 3, 0, 7, 'x');
        maze = createPath(maze, 5, 1, 4, 'x');
        return maze;
    }

    public static int[][] createPath(int[][] maze, int x, int y, int length, char direction)
    {
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
        System.out.println("\n");
    }
    public static Path solve(Path path, int[][] maze)
    {
        int[][] mazeCopy = Arrays.copyOf(maze, maze.length);
        int x = path.getX();
        int y = path.getY();
        DIRECTION dir = path.getDir();
        int count = 0;

        while(mazeCopy[x][y] != 0){
            if(dir == DIRECTION.valueOf("NORTH"))
            {
                y--;
            }
            else if(dir == DIRECTION.valueOf("EAST"))
            {
                x++;
            }
            else if(dir == DIRECTION.valueOf("SOUTH"))
            {
                y++;
            }
            else if(dir == DIRECTION.valueOf("WEST"))
            {
                x--;
            }

            if(mazeCopy[x][y] == 1)
            {
                mazeCopy[x][y] = 2;
            }
            else
            {
                break;
            }

            if(mazeCopy[x-1][y] == 1)
            {
                count++;
            }
            else if(mazeCopy[x][y+1] == 1)
            {
                count++;
            }
            else if(mazeCopy[x+1][y] == 1)
            {
                count++;
            }
            else if(mazeCopy[x][y-1] == 1)
            {
                count++;
            }
            if(count > 2)
            {
                return(new Path(x, y, dir));
            }
        }
        display(mazeCopy);
        return(new Path(x, y, DIRECTION.valueOf("NULL")));
    }

    public static void main(String[] args) {
        int[][] maze = fillMaze();
        int x = 3;
        int y = 4;
        DIRECTION dir = DIRECTION.valueOf("NULL");
        Path current;
        Stack<Path> paths = new Stack<>();

        while(x > 0 && x < 7 && y > 0 && y < 7)
        {
            do
            {
                if(maze[x-1][y] == 1 && dir != DIRECTION.valueOf("EAST"))
                {
                    paths.push(new Path(x, y, DIRECTION.valueOf("WEST")));
                }
                else if(maze[x][y+1] == 1 && dir != DIRECTION.valueOf("NORTH"))
                {
                    paths.push(new Path(x, y, DIRECTION.valueOf("SOUTH")));
                }
                else if(maze[x+1][y] == 1 && dir != DIRECTION.valueOf("WEST"))
                {
                    paths.push(new Path(x, y, DIRECTION.valueOf("EAST")));
                }
                else if(maze[x][y-1] == 1 && dir != DIRECTION.valueOf("SOUTH"))
                {
                    paths.push(new Path(x, y, DIRECTION.valueOf("NORTH")));
                }

                current = solve(paths.pop(), maze);
                if(current.getDir() != DIRECTION.valueOf("NULL")) {
                    x = current.getX();
                    y = current.getY();
                    dir = current.getDir();
                }
            } while(!paths.isEmpty());
        }
    }
}


//    public static Path solve(Path path, int[][] maze)
//    {
//        int x = path.getX();
//        int y = path.getY();
//        DIRECTION dir = path.getDir();
//        int count = 0;
//
//        do
//        {
//            count = 0;
//            if(dir == DIRECTION.valueOf("NORTH"))
//            {
//                y--;
//            }
//            else if(dir == DIRECTION.valueOf("EAST"))
//            {
//                x++;
//            }
//            else if(dir == DIRECTION.valueOf("SOUTH"))
//            {
//                y++;
//            }
//            else if(dir == DIRECTION.valueOf("WEST"))
//            {
//                x--;
//            }
//
//            if(maze[x][y] == 1)
//            {
//                maze[x][y] = 2;
//            }
//            else
//            {
//                break;
//            }
//
//            if(maze[x-1][y] == 1)
//            {
//                count++;
//            }
//            else if(maze[x][y+1] == 1)
//            {
//                count++;
//            }
//            else if(maze[x+1][y] == 1)
//            {
//                count++;
//            }
//            else if(maze[x][y-1] == 1)
//            {
//                count++;
//            }
//        } while(count > 1);
//
//        display(maze);
//
//        return(new Path(x, y, dir));
//    }
