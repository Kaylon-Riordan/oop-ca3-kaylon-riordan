import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

public class CA3_Question4 {

    /*
        filename: name of the file to test.
     */

    public static boolean validate(String filename) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File(filename));

        Stack<String> tags = new Stack<>();

        while(in.hasNext())
        {
            String tag = in.next();
            if(!tags.isEmpty() && tag.equals(tags.peek().substring(0,1)+"/"+tags.peek().substring(1)))
            {
                tags.pop();
            }
            else
            {
                tags.push(tag);
            }
        }

        if(tags.isEmpty())
        {
            return true;
        }
        return false;
    }

    /*
        This function tests the files in the files array to see if
         they are valid.
         tags_valid.txt should return true;
         tags_invalid.txt should output as invalid;
     */

    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};
        for(String fName: files) {
            System.out.print(fName +": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } else {
                System.out.println("This file is invalid");
            }
        }
    }
}
