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

        // Take in file input and add it all to the distances map
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

        // take in user input for starting city and format properly
        Scanner keyboard = new Scanner(System.in);
        System.out.print("\nCities: Pendleton, Pierre, Pueblo, Phoenix, Peoria, Pittsburgh, Pensacola, Princeton." +
                "\nGet shortest distances to all other cities from: ");
        String from = keyboard.next().trim();
        from = from.substring(0,1).toUpperCase()+from.substring(1).toLowerCase();

        // create a queue to store possible distances to cities from starting point
        PriorityQueue<DistanceTo> queue = new PriorityQueue<>();
        queue.add(new DistanceTo(from, 0));

        // create a map to store the confirmed shortest paths
        TreeMap<String, Integer> shortest = new TreeMap<>();

        while(!queue.isEmpty())
        {
            // until the possible shortest queue is empty remove the top of the queue and store its data as variables
            DistanceTo small = queue.remove();
            int d = 0;
            if(!shortest.containsKey(small.getTarget()))
            {
                // if the shortest path to the target hasn't already been found add it to the shortest map
                d = small.getDistance();
                shortest.put(small.getTarget(), d);
                // for all cities attached to the target add them to the possible queue with the distance equal to the distance of the target to the origin + the target to the next city
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
        // at the end print the shortest distance to each city from the original
        for(Map.Entry<String, Integer> entry: shortest.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
