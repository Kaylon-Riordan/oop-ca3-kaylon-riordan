import java.util.*;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

public class CA3_Question5
{
    public static void runSimulation()
    {
        Scanner keyboard = new Scanner(System.in);

        Queue<String> takingOff = new LinkedList<>();
        Queue<String> landing = new LinkedList<>();

        String command = "";
        String flightSymbol;

        System.out.println("Please use commands 'takeoff flightSymbol' to queue a take off, 'land flightSymbol' to queue a landing, 'next' to perform the next queued action, and 'quit' to end simulation");

        // run loop while user hasn't entered the quit command
        while(!command.equalsIgnoreCase("quit"))
        {
            // take in suers command
            System.out.print("\nCommand: ");
            command = keyboard.next();

            // if takeoff or land then get the next input as the flight symbol and add it to the appropriate stack
            if(command.equalsIgnoreCase("takeoff"))
            {
                flightSymbol = keyboard.next();
                takingOff.add(flightSymbol);
                System.out.println("\n" + flightSymbol + " is queued to takeoff");
            }
            else if(command.equalsIgnoreCase("land"))
            {
                flightSymbol = keyboard.next();
                landing.add(flightSymbol);
                System.out.println("\n" + flightSymbol + " is queued to land");
            }
            else if(command.equalsIgnoreCase("next"))
            {
                // if next is inputted, land if a plane is waiting, else take off if a plane is waiting, else display an error message
                if(!landing.isEmpty())
                {
                    System.out.println("\n" + landing.peek() + " lands.");
                    landing.remove();
                }
                else if(!takingOff.isEmpty())
                {
                    System.out.println("\n" + takingOff.peek() + " takes off.");
                    takingOff.remove();
                }
                else
                {
                    System.out.println("\nNo actions are queued.");
                }
            }
            else if(!command.equalsIgnoreCase("quit"))
            {
                // if user gives an invalid input, print an error message
                System.out.println("\nCommand not Recognised.");
            }
            System.out.println("\nCurrent queues:\nLanding"+landing+"\nTakingOff"+takingOff);
        }
    }

    public static void main(String[] args)
    {
        runSimulation();
    }
}
