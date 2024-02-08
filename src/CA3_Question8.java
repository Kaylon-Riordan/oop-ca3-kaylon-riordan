import java.sql.SQLOutput;
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

        String num = "";

        for(int i = 0; i < equation.length(); i++)
        {
            char next = equation.charAt(i);

            if(isDigit(next))
            {
                num = num + next;
                if(i == equation.length()-1 || !isDigit(equation.charAt(i+1)))
                {
                    numbers.push(Integer.parseInt(num));
                    num = "";
                }
            }
            else if(next == '(')
            {
                operators.push(next);
            }
            else if(next == ')')
            {
                while(operators.peek() != '(')
                {
                    numbers.push(evaluate(numbers.pop(), numbers.pop(), operators.pop()));
                }
                operators.pop();
            }
            else
            {
                while(!operators.isEmpty() && operators.peek() > precedence(next))
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
        System.out.println("\nAnswer = " + numbers.peek());
    }

    public static int evaluate(int b, int a, char op)
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
        if (op == '/' || op == '*' || op == 'x' || op == 'X')
        {
            return(2);
        }
        else if(op == '+' || op == '-')
        {
            return(1);
        }
        else
        {
            return(0);
        }
    }
}

//
//import java.util.Scanner;
//        import java.util.Stack;
//        import static java.lang.Character.isDigit;
//
///**
// *  Name: Kaylon Riordan
// *  Class Group: GD2A
// */

//public class CA3_Question8 {
//    /*
//        Reads in an equation from the user
//     */
//    public static void main(String[] args) {
//        String equation;
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter equation: ");
//        equation = in.nextLine().trim();
//
//        Stack<Integer> numbers = new Stack<>();
//        Stack<Character> operators = new Stack<>();
//
//        int a, b;
//        int priority = 0;
//        String num = "";
//
//
//        for(int i = 0; i < equation.length(); i++)
//        {
//            char next = equation.charAt(i);
//            if(next == '*' || next == 'x' || next == 'X' || next == '÷' || next == '/')
//            {
//                priority++;
//            }
//        }
//        for(int i = 0; i < equation.length(); i++)
//        {
//            char next = equation.charAt(i);
//
//            if(isDigit(next))
//            {
//                num = num + next;
//                if(i == equation.length()-1 && next != ')')
//                {
//                    numbers.push(Integer.parseInt(num));
//                }
//            }
//            else
//            {
//                operators.push(next);
//                if(num != "")
//                {
//                    numbers.push(Integer.parseInt(num));
//                    num = "";
//                }
//            }
//
//            System.out.println("\n"+numbers+"\n"+operators);
//
//            if(numbers.size() > 1)
//            {
//                b = numbers.pop();
//                a = numbers.pop();
//
//                if(operators.peek() == '+' && priority == 0)
//                {
//                    numbers.push(a + b);
//                }
//                else if(operators.peek() == '-' && priority == 0)
//                {
//                    numbers.push(a - b);
//                }
//                else if(operators.peek() == '*' || operators.peek() == 'x' || operators.peek() == 'X')
//                {
//                    numbers.push(a * b);
//                    priority--;
//                }
//                else if(operators.peek() == '÷' || operators.peek() == '/')
//                {
//                    numbers.push(a / b);
//                    priority--;
//                }
//                operators.pop();
//            }
//            System.out.println("\n"+numbers+"\n"+operators);
//        }
//        System.out.println("\nAnswer = " + numbers.peek());
//    }
//}
