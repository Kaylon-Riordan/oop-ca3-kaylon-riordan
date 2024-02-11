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
            // while the scanner has more tags, store them as a variable
            String tag = in.next();
            // if the tag taken in is a closing tag that matches the opening tag on top of the stack then pop the stack
            if(!tags.isEmpty() && tag.equals(tags.peek().substring(0,1)+"/"+tags.peek().substring(1)))
            {
                tags.pop();
            }
            // else push the tag taken in on top of the stack
            else
            {
                tags.push(tag);
            }
        }

        // once all tags have been read return valid if the stack is empty, or invalid if it's not
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
        // call the validate method once for each of the 2 text files
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
