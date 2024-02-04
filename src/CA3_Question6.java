import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

public class CA3_Question6
{
    /*
    Will repeatedly ask the user to enter the commands in the format
    buy qty price
    or
    sell qty price
    or
    quit
     */

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String command = "";
        Queue<Block> stock = new LinkedList<>();
        int owned = 0;

        do {
            System.out.print("\n\n>");
            command = in.next();

            if(command.equalsIgnoreCase("buy"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();

                stock.add(new Block(qty, price));
                System.out.printf("\n%d shares bought at $%.2f each.\n", qty, price);
            }
            else if(command.equalsIgnoreCase("sell"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();

                if(qty <= owned)
                {
                    System.out.printf("\n%d shares sold at $%.2f each.\n", qty, price);

                    double cost = 0;
                    double gain = qty * price;

                    while(qty > 0)
                    {
                        if(stock.peek().getQuantity() > qty)
                        {
                            cost += qty * stock.peek().getPrice();
                            stock.peek().setQuantity(stock.peek().getQuantity() - qty);
                            qty = 0;
                        }
                        else
                        {
                            cost += stock.peek().getQuantity() * stock.peek().getPrice();
                            qty = qty - stock.peek().getQuantity();
                            stock.remove();
                        }
                    }

                    System.out.printf("Your profit is $%.2f.\n", gain - cost);
                }
                else
                {
                    System.out.println("\nYou don't own that much stock.");
                }
            }
            owned = 0;
            for(Block b : stock)
            {
                owned += b.getQuantity();
            }
            System.out.println("\nYou now own " + owned + " shares.");
        } while(!command.equalsIgnoreCase("quit"));
    }
}