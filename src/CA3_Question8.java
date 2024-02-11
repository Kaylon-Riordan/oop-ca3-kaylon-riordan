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
        System.out.println("Use the operators '+', '-', '*', '/', '(', ')', and numerical digits.\nPlease enter equation: ");
        equation = in.nextLine().trim();

        // create stacks for operators and numbers
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        String num = "";

        for(int i = 0; i < equation.length(); i++)
        {
            char next = equation.charAt(i);

            // if the input is a number, keep making the num string longer until the next character isn't a number, then add the number to the number stack
            // this step makes multiple digit numbers viable
            if(isDigit(next))
            {
                num = num + next;
                if(i == equation.length()-1 || !isDigit(equation.charAt(i+1)))
                {
                    numbers.push(Double.parseDouble(num));
                    num = "";
                }
            }
            // push open brackets onto the operator stack
            else if(next == '(')
            {
                operators.push(next);
            }
            // if ( is detected keep evaluating the stacks until ) is on top of the operator stack then pop it
            else if(next == ')')
            {
                while(operators.peek() != '(' && numbers.size() > 1)
                {
                    numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                }
                operators.pop();
            }
            // if any other operator is read, keep evaluating until one of the stacks is empty, a bracket is on top of the stack or the stack's top operator has higher precedence than the current one
            // then push the operator onto the stack
            else
            {
                while(!operators.isEmpty() && operators.peek() != '(' && precedence(operators.peek()) >= precedence(next) && numbers.size() > 1)
                {
                    numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                }
                operators.push(next);
            }

            // if there are no more inputs to read, keep evaluating until a stack is empty
            if(i == equation.length()-1)
            {
                while(!operators.isEmpty() && numbers.size() > 1)
                {
                    numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                }
            }
        }
        System.out.printf("\nAnswer = %.2f", numbers.peek());
    }

    // read in a sign and 2 numbers then perform the appropriate calculation
    public static double evaluate(double b, double a, char op)
    {
        if(op == '+')
        {
            return(a + b);
        }
        else if(op == '-')
        {
            return(a - b);
        }
        else if(op == '/')
        {
            return(a / b);
        }
        else
        {
            return(a * b);
        }
    }

    // assign * and / as higher precedence operators than the others
    public static int precedence(char op)
    {
        if(op == '+' || op == '-')
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }
}
