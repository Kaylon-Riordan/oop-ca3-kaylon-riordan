import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

        TreeMap<String, HashSet<Integer>> identifiers = new TreeMap<>();

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
                    identifiers.put(ident, new HashSet<>());
                }
                identifiers.get(ident).add(count);
            }
        }

        display(identifiers);
    }

    public static void display(TreeMap<String, HashSet<Integer>> identifiers)
    {
        for (Map.Entry<String, HashSet<Integer>> entry: identifiers.entrySet())
        {
            System.out.printf("%s : ", entry.getKey());
            Iterator iterator = entry.getValue().iterator();
            while(iterator.hasNext())
            {
                System.out.printf("%d, ", iterator.next());
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile("src/CA3_Question1.java");
    }
}
