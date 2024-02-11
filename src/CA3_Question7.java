import java.util.*;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

public class CA3_Question7
{
    /*
   Will repeatedly ask the user to enter the commands in the format
   buy company qty price
   or
   sell company qty price
   or
   quit
    */

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String command = "";
        // create a map which uses a company as the key and a queue of blocks as the value
        Map<String, Queue<Block>> stock = new TreeMap<>();
        int owned = 0;

        do {
            System.out.print("\n\n>");
            command = in.next();

            if(command.equalsIgnoreCase("buy"))
            {
                String comp = in.next().toLowerCase();
                int qty = in.nextInt();
                double price = in.nextDouble();

                //after buying if map doesn't contain the comp key yet create it, then add data to map
                if(!stock.containsKey(comp))
                {
                    stock.put(comp,  new LinkedList<>());
                }

                stock.get(comp).add(new Block(qty, price));
                System.out.printf("\n%d shares of %s bought at $%.2f each.\n", qty, comp, price);
            }
            else if(command.equalsIgnoreCase("sell"))
            {
                String comp = in.next().toLowerCase();
                int qty = in.nextInt();
                double price = in.nextDouble();

                // verify that the company is in the map, then that enough stock is owned in that company to sell
                if(stock.containsKey(comp))
                {
                    owned = 0;
                    for(Block b : stock.get(comp))
                    {
                        owned += b.getQuantity();
                    }

                    if(qty <= owned)
                    {
                        System.out.printf("\n%d shares of %s sold at $%.2f each.\n", qty, comp, price);

                        double cost = 0;
                        double gain = qty * price;

                        while(qty > 0)
                        {
                            if(stock.get(comp).peek().getQuantity() > qty)
                            {
                                cost += qty * stock.get(comp).peek().getPrice();
                                stock.get(comp).peek().setQuantity(stock.get(comp).peek().getQuantity() - qty);
                                qty = 0;
                            }
                            else
                            {
                                cost += stock.get(comp).peek().getQuantity() * stock.get(comp).peek().getPrice();
                                qty = qty - stock.get(comp).peek().getQuantity();
                                stock.get(comp).remove();
                            }
                        }

                        System.out.printf("Your profit is $%.2f.\n", gain - cost);
                    }
                    else
                    {
                        System.out.println("\nYou don't own that much stock in " + comp + ".");
                    }
                }
                else
                {
                    System.out.println("\nYou don't own that much stock in " + comp + ".");
                }
            }
            System.out.println("\nYou now own");
            for(String key : stock.keySet())
            {
                owned = 0;
                for(Block b : stock.get(key))
                {
                    owned += b.getQuantity();
                }
                System.out.println(owned + " shares in " + key + ",");
            }

        } while(!command.equalsIgnoreCase("quit"));
    }
}
