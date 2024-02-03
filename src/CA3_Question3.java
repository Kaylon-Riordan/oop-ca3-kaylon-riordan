import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

public class CA3_Question3
{
    public static void readFile(String fileName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));

        int count = 0;
        String line, ident;

        TreeMap<String, ArrayList<String>> identifiers = new TreeMap<>();

        while(in.hasNextLine())
        {
            count++;
            line = in.nextLine();

            Scanner scan = new Scanner(line);
            scan.useDelimiter("[^A-Za-z0-9_]+");

            while(scan.hasNext())
            {
                ident = scan.next();
                if(!identifiers.containsKey(ident))
                {
                    identifiers.put(ident, new ArrayList<>());
                }
                identifiers.get(ident).add(count + ":  " + line);
            }
        }

        display(identifiers);
    }

    public static void display(TreeMap<String, ArrayList<String>> identifiers)
    {
        for (Map.Entry<String, ArrayList<String>> entry: identifiers.entrySet())
        {
            System.out.println("\n\nIdentifier '" + entry.getKey() + "' is used in these line(s): ");
            for(int i = 0; i < entry.getValue().size(); i++)
            {
                System.out.println(entry.getValue().get(i));
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile("src/CA3_Question1.java");
    }
}
