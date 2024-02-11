import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */
public class CA3_Question10
{
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, HashSet<DistanceTo>> distances = new HashMap<>();

        Scanner in = new Scanner(new File("Cities.txt"));
        while(in.hasNext())
        {
            String from = in.next();
            String to = in.next();
            int dist = Integer.parseInt(in.next());

            if(!distances.containsKey(from))
            {
                distances.put(from, new HashSet<>());
            }
            distances.get(from).add(new DistanceTo(to, dist));
        }

        Scanner keyboard = new Scanner(System.in);
        System.out.print("\nCities: Pendleton, Pierre, Pueblo, Phoenix, Peoria, Pittsburgh, Pensacola, Princeton." +
                "\nGet shortest distances to all other cities from: ");
        String from = keyboard.next().trim();
        from = from.substring(0,1).toUpperCase()+from.substring(1).toLowerCase();

        PriorityQueue<DistanceTo> queue = new PriorityQueue<>();
        queue.add(new DistanceTo(from, 0));

        TreeMap<String, Integer> shortest = new TreeMap<>();

        while(!queue.isEmpty())
        {
            DistanceTo small = queue.remove();
            int d = 0;
            if(!shortest.containsKey(small.getTarget()))
            {
                d = small.getDistance();
                shortest.put(small.getTarget(), d);
                for (Map.Entry<String, HashSet<DistanceTo>> entry: distances.entrySet())
                {
                    if(entry.getKey().equals(small.getTarget()))
                    {
                        for (DistanceTo next : entry.getValue()) {
                            queue.add(new DistanceTo(next.getTarget(), next.getDistance() + d));
                        }
                    }
                }
            }
        }
        for(Map.Entry<String, Integer> entry: shortest.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
