import java.util.Scanner;

public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
    	Scanner console = new Scanner(System.in);
    	System.out.println("Print an equation.");
    	String in = console.nextLine();
    	System.out.println(produceAnswer(in));
    	System.out.println("");
    	System.out.println("Print another equation. Type 'done' if you wish to close the program.");
    	String choice = console.nextLine();
    	if (choice.equals("done"))
    	{
    		System.out.println("Program Terminated.");
    		System.exit(0);
    	}
    	System.out.println(produceAnswer(choice));
    	while (!choice.equals("done"))
    	{
    		repeat();
    	}
    }
    
    public static void repeat() {
    	System.out.println("");
    	System.out.println("Print another equation. Type 'done' if you wish to close the program.");
    	Scanner console = new Scanner(System.in);
    	String in2 = console.nextLine();
    	if (in2.equals("done"))
		{
    		System.out.println("Program Terminated.");
			System.exit(0);
		}
    	System.out.println(produceAnswer(in2));
    }
    	
    	
    
    	
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
    	int x = input.indexOf(" ");
    	
    	String operand1 = input.substring(0, x);
    	String operand2 = input.substring(x+1, x+2);
    	String operand3 = input.substring(x+3, input.length());
    	
    	int w = operand3.indexOf("/");
    	int y = operand3.indexOf("_");
    	String z = ("");
    	
    	if (operand3.indexOf('_') > -1)
    	{
    	String wholeoperand3 = operand3.substring(0, y);
    	z += ("whole: " + wholeoperand3);

	    String demoperand3 = operand3.substring(w+1, operand3.length());
	    String numoperand3 = operand3.substring(y+1, w);
	    z += (" num: " + numoperand3);
	    z += (" dem: " + demoperand3);
	    return z;
	    	}
    
    	else if (operand3.indexOf('/') > -1)
    	{
    		String demoperand3 = operand3.substring(w+1, operand3.length());
    		String numoperand3 = operand3.substring(y+1, w);
    		z += (" num: " + numoperand3);
    		z += (" dem: " + demoperand3);
    		return (z);
    	}
    	else
    	{
    		return(operand3);
    	}
    	
    }
    	
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".

    // TODO: Fill in the space below with helper methods
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
