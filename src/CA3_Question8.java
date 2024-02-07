import java.util.Scanner;
import java.util.Stack;
import static java.lang.Character.isDigit;

/**
 *  Name: Kaylon Riordan
 *  Class Group: GD2A
 */

public class CA3_Question8 {
    /*
        Reads in an equation from the user
     */
    public static void main(String[] args) {
        String equation;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter equation: ");
        equation = in.nextLine().trim();

        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int a;
        int b;

        for(int i = 0; i > equation.length(); i++)
        {
            char next = equation.charAt(i);
            if(isDigit(next))
            {
                numbers.push(((int) next));
            }
            else
            {
                operators.push(next);
            }

            if(numbers.size() > 1)
            {
                b = numbers.pop();
                a = numbers.pop();

                if(operators.peek() == '+')
                {
                    numbers.push(a + b);
                }
                else if(operators.peek() == '-')
                {
                    numbers.push(a - b);
                }
                else if(operators.peek() == '*' || operators.peek() == 'x' || operators.peek() == 'X')
                {
                    numbers.push(a * b);
                }
                else if(operators.peek() == '÷' || operators.peek() == '/')
                {
                    numbers.push(a / b);
                }
                operators.pop();
            }
        }
        System.out.println("\n");
    }
}
