import java.util.Scanner;

public class FracCalc 
{

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
	
	// Ask for equations until "quit".
    public static void main(String[] args) 
    {
    	Scanner console = new Scanner(System.in);
    	System.out.println("Print an equation.");
    	String in = console.nextLine();
    	System.out.println(produceAnswer(in));
    	System.out.println("");
    	System.out.println("Print another equation. Type 'quit' to exit the program.");
    	String choice = console.nextLine();
    	if (choice.equals("quit"))
    	{
    		System.out.println("Program Terminated.");
    		System.exit(0);
    	}
    	System.out.println(produceAnswer(choice));
    	while (!choice.equals("quit"))
    	{
    		repeat();
    	}
    }
   // Repeat asking for equations.
    public static void repeat() 
    {
    	System.out.println("");
    	System.out.println("Print another equation. Type 'quit' to exit the program.");
    	Scanner console = new Scanner(System.in);
    	String in2 = console.nextLine();
    	if (in2.equals("quit"))
		{
    		System.out.println("Program Terminated.");
			System.exit(0);
		}
    	System.out.println(produceAnswer(in2));
    }
    	
    // Final operand 1
 	public static int whole1 = 0;
 	public static int num1 = 0;
 	public static int dem1 = 0;
 	public static int improper1 = 0;
 	// Final operator
 	public static String operator = "";
 	public static String answer = "";
 	// Final operand 2
 	public static int whole2 = 0;
 	public static int num2 = 0;
 	public static int dem2 = 0;
 	public static int improper2 = 0;
    	
    
    	
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
 	
 	
 	
 	public static String produceAnswer(String input)  // Use spaces to separate parts. Solve equation.
 	{
 		String[] array = input.split(" ");
 		Solve(array[1], array[0], array[2]); 
 		for (int i = 3; i < array.length; i += 2) 
 		{
 			Solve(array[i], answer, array[i + 1]);
 		}
 			return answer;
 	}

 	

public static void Solve(String operator, String operand1, String operand2) 
{
		analyzeFraction(true, operand1);
		analyzeFraction(false, operand2);

		if (operator.equals("+")) // check if operator is addition sign
		{
			Add();
		} 		
		else if (operator.equals("-")) // check if operator is minus sign
		{
			Subract();
		} 
		else if (operator.equals("*")) // check if operator is multiplication sign
		{
			Multiply();
		} 
		
		else if (operator.equals("/")) // check if operator is division sign
		{
			Divide();
		} 
}



public static void analyzeFraction(Boolean operand1, String operand) // check parts of operands
{
	String fraction = operand;
	String whole = "0";
	String num = "0";
	String dem = "0";
	String[] getwhole = fraction.split("_");
	
	if (getwhole.length == 2) // isolate parts of mixed fraction
	{
		whole = fraction.split("_")[0];
		num = fraction.split("_")[1].split("/")[0];
		dem = fraction.split("_")[1].split("/")[1];
	} 
	
	else // if there is no whole number ('_')
	{
		String[] separate = fraction.split("/");

		if (separate.length == 2) // 2 values = it's a fraction
		{
			num = separate[0];
			dem = separate[1];
		} 
		else // else it is two whole numbers
		{
			whole = operand;
			dem = "1";
		}
	}
checkIfNegative(operand1, Integer.parseInt(whole), Integer.parseInt(num), Integer.parseInt(dem));
}



public static void checkIfNegative(Boolean operand1, int whole, int num, int dem) // method to check if the operands are negative
{ 
if (operand1) { // for operand 1
	whole1 = whole;
	num1 = num;
	dem1 = dem;
	if (whole != 0) 
	{
		if (Integer.toString(whole).contains("-")) // checks if it has negative
		{
			whole = Integer.parseInt(Integer.toString(whole).split("-")[1]);
			improper1 = ((whole * dem) + num) * (-1); // make it negative again
			} 
		else 
			{
			improper1 = (whole * dem) + num;
			}
	} 
	else 
		{
		improper1 = num;
		}
} 
else // for operand 2
	{
	whole2 = whole;
	num2 = num;
	dem2 = dem;
	if (whole != 0) 
		{
		if (Integer.toString(whole).contains("-")) // checks if it has negative
			{
			whole = Integer.parseInt(Integer.toString(whole).split("-")[1]);
			improper2 = ((whole * dem) + num) * (-1); // make it negative again
			} 
		else 
			{
			improper2 = (whole * dem) + num;
			}
	} 
	else 
		{
		improper2 = num;
		}
	}
}



public static void result(int num, int dem) 
{
	if (num % dem == 0) // Check if fraction is greater than 1
	{
		answer = Integer.toString(num / dem);
	} 
	
	else // Simplify fraction if greater than 1
	{
		int gcd = greatestCommonDivisor(num, dem);
	
		if (Integer.toString(gcd).contains("-")) // get absolute value on GCD
		{
			gcd = Integer.parseInt(Integer.toString(gcd).split("-")[1]);
		}
		
		int finalnum = num / gcd;
		int finaldem = dem / gcd;
	
		answer = improperToMixed(finalnum, finaldem);
	}
}



public static String improperToMixed(int num, int dem) // convert improper fraction to mixed number
{
	Integer whole = num / dem;
	Integer remainder = num % dem;
	if (whole < 0) // Check if it has a negative sign, remove it, and show it only in the whole number
	{
		if (Integer.toString(remainder).contains("-")) 
		{
			remainder = Integer.parseInt(Integer.toString(remainder).split("-")[1]);
		}
	}
	if (whole != 0)
	{
		return (whole + "_" + remainder + "/" + dem);
	}
	else
	{
		return (remainder + "/" + dem);
	}
}



public static String Add() // Add operands together
{
	int tbdnum = 0; // to be determined numerator
	int lcm = 0;
	if (dem1 == dem2) // if denominators are the same, then add numerators
	{
		tbdnum = improper1 + improper2;
		result(tbdnum, dem1);
	} 
	else // otherwise, use least common multiple and add numerators
	{
		lcm = leastCommonMultiple(dem1, dem2);
		improper1 = improper1 * (lcm / dem1);
		improper2 = improper2 * (lcm / dem2);
		tbdnum = improper1 + improper2;
		result(tbdnum, lcm);
	}
	return answer;
}



public static String Multiply() { // Multiply operands together
int tbdnum = 0;
int tbddem = 0;
tbdnum = improper1 * improper2;
tbddem = dem1 * dem2; // to be determined denominator
result(tbdnum, tbddem);
return answer;
}



public static String Subract() { // Subtract operands
int tbdnum = 0;
int lcm = 0;
if (dem1 == dem2) // if denominators are the same, then subtract numerators
{
	tbdnum = improper1 - improper2;
	result(tbdnum, dem1);
} 
else // otherwise, use least common multiple and subtract numerators
{
	lcm = leastCommonMultiple(dem1, dem2);
	improper1 = improper1 * (lcm / dem1);
	improper2 = improper2 * (lcm / dem2);
	tbdnum = improper1 - improper2;
	result(tbdnum, lcm);
}
return answer;
}



public static String Divide() { // Divide operands by using operand 2's reciprocal
int tbdnum = 0;
int tbddem = 0;

if (improper2 < 0) // Check to see if remainder is negative, then invert operand 2
{
	improper2 = Integer.parseInt(Integer.toString(improper2).split("-")[1]);
	int tbdvalue = dem2;
	dem2 = improper2;
	improper2 = tbdvalue;
	tbdnum = (improper1 * -1) * improper2;
	tbddem = dem1 * dem2;
} 
else // Invert operand 2, then multiply
{
	int tbdvalue = dem2;
	dem2 = improper2;
	improper2 = tbdvalue;
	tbdnum = improper1 * improper2;
	tbddem = dem1 * dem2;
}
result(tbdnum, tbddem);
return answer;
}


    	
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 num:2 dem:4".
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
