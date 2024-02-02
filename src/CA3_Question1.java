import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

public class CA3_Question1
{
    public static void runSimulation()
    {
        Scanner keyboard = new Scanner(System.in);

        Stack<Integer> driveway = new Stack<>();
        Stack<Integer> street = new Stack<>();

        int car = 1;
        boolean fin = false;

        System.out.println("\nPlease enter positive numbers e.g. 1 to add car 1, or negative numbers e.g. -1 to remove car 1. Enter 0 to end the program.");

        while(car != 0)
        {
            System.out.print("\nNext Car: ");
            car = keyboard.nextInt();

            if(car > 0)
            {
                if(driveway.search(car) == -1)
                {
                    driveway.push(car);
                    display(driveway, street);
                }
                else
                {
                    System.out.println("\nSorry, car " + car + " is already in the driveway.");
                }
            }
            else if(car < 0)
            {
                fin = false;
                while(!fin)
                {
                    if(driveway.search(car * -1) == -1)
                    {
                        System.out.println("\nSorry, no car " + car * -1 + " was found in the driveway.");
                        fin = true;
                    }
                    else
                    {
                        if(driveway.peek() == car * -1)
                        {
                            driveway.pop();
                            display(driveway, street);

                            while(!street.isEmpty())
                            {
                                driveway.push(street.pop());
                                display(driveway, street);
                            }
                            fin = true;
                        }
                        else
                        {
                            street.push(driveway.pop());
                            display(driveway, street);
                        }
                    }
                }
            }
        }
    }

    public static void display(Stack<Integer> driveway, Stack<Integer> street) {
        System.out.println("\ndriveway: " + driveway + "\nstreet: " + street);
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
