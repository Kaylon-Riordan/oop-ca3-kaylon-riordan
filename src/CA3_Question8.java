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

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        String num = "";

        for(int i = 0; i < equation.length(); i++)
        {
            char next = equation.charAt(i);

            if(isDigit(next))
            {
                num = num + next;
                if(i == equation.length()-1 || !isDigit(equation.charAt(i+1)))
                {
                    numbers.push(Double.parseDouble(num));
                    num = "";
                }
            }
            else if(next == '(')
            {
                operators.push(next);
            }
            else if(next == ')')
            {
                while(operators.peek() != '(' && numbers.size() > 1)
                {
                    numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                }
                operators.pop();
            }
            else
            {
                while(!operators.isEmpty() && operators.peek() != '(' && precedence(operators.peek()) >= precedence(next) && numbers.size() > 1)
                {
                    numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                }
                operators.push(next);
            }


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
