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
        // Scanner to read user inputs
        Scanner keyboard = new Scanner(System.in);

        // stacks to store cars parked in different areas
        Stack<Integer> driveway = new Stack<>();
        Stack<Integer> street = new Stack<>();

        int car = 1;
        boolean fin = false;

        System.out.println("\nPlease enter positive numbers e.g. 1 to add car 1, or negative numbers e.g. -1 to remove car 1. Enter 0 to end the program.");

        // while the exit term 0 hasn't been entered, perform the main loop
        while(car != 0)
        {
            // take in next car from user
            System.out.print("\nNext Car: ");
            car = keyboard.nextInt();

            if(car > 0)
            {
                // if the user enters a positive number, check if it's a duplicate then either add it to the driveway, or tell the user its already there
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
                // if the user enters a negative number set up a finished variable which will control when the while loop ends
                fin = false;
                while(!fin)
                {
                    // If a matching car isn't in the driveway, tell the user there's nothing to remove
                    if(driveway.search(car * -1) == -1)
                    {
                        System.out.println("\nSorry, no car " + car * -1 + " was found in the driveway.");
                        fin = true;
                    }
                    else
                    {
                        // If a matching car is in the list, keep moving cars to the street until that car can be popped, then put all cars back into the driveway and finish the loop
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

    // method to display all cars in the driveway, then street
    public static void display(Stack<Integer> driveway, Stack<Integer> street) {
        System.out.println("\ndriveway: " + driveway + "\nstreet: " + street);
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
