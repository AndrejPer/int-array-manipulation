import java.util.*;


public class Project_1 {
	
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		//arraylist for storing all the inputed elements
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		//array for storing unique elements
		ArrayList<Integer> arrayUnique = new ArrayList<Integer>();
		
		//array for storing repetitions for respective elements in "arrayUnique", where on index i, it stores the repetition of element at the same index in arrayUnique
		ArrayList<Integer> arrayRepetitions = new ArrayList<Integer>();
		
		System.out.printf("Please insert the elements! \n(Insert any nonnumeric charachter when you wish to stop)\n");
		
		
		boolean stop = false;
		Integer element;
		while(!stop) {
			//if users inputs a number, than append it
			if(scan.hasNextInt()) {
				element = scan.nextInt();
				array.add(element);
				//if the given number hasn't been inputed before, add it to arrayUnique
				if(!arrayUnique.contains(element)) {
					arrayUnique.add(element);
					//adding 1 to arrayRepetiotions, since new element is added to arrayUnique whose repetition at the moment is one;
					//this way, for every element in arrayUnique, arrayRepetiotion stores the number of repetitions on the same index
					arrayRepetitions.add(1);
				}
				
				//else, incrementing the value of repetitions of number "element" by one
				else arrayRepetitions.set(arrayUnique.indexOf(element), arrayRepetitions.get(arrayUnique.indexOf(element)) + 1);
		
			}
			
			//else, if user inputs something beside a number, stop the dynamic input
			else stop = true;
		}
		
		if(array.isEmpty()) {
			System.out.println("No entries...");
			return;
		}
		
		System.out.println("");
		System.out.println("You inserted the following array: " + array);
		
		
		
		//basic info
		System.out.println("");
		System.out.println("Number of elements: " + array.size());
		System.out.println("Number of unique elements: " + arrayUnique.size());
		System.out.println("Number of even elements: " + numOfEven(array));
		System.out.println("Number of odd elements: " + numOfOdd(array));
		System.out.printf("Sum of elements: %.0f\n", sum(array));
		
		System.out.print("Second smallest element: ");
		if(array.size() < 2) System.out.println("none");
		else System.out.println(secondSmallest(arrayUnique));
		
		
		System.out.println("Element(s) with maximum occurrence: " + maxOccurrence(arrayUnique, arrayRepetitions));
		System.out.printf("\b\n");
		repetitions(arrayUnique, arrayRepetitions); //call of function for info about repetitions
		System.out.printf("Element with max value: %d\n", max(arrayUnique));
		
		
		
		
		
		//statistics part
		
		//printing the value returned by "sum" method divided by the size of "array"
		System.out.printf("\nAverage of elements is %.2f\n", sum(array)/(double)array.size());
		System.out.printf("Standard deviation is %.2f\n", deviation(array));
		System.out.println("Median element is: " + median(array));
		
		
		
		
		//palindrome part
		ArrayList<Integer> palindromes = palindromArray(array); //initialising array with all palindromic numbers
		System.out.println("Number of palindromic elements is: " + palindromes.size());
		
		Integer palindromeLessThanMax = biggestPalindrome(palindromes, max(arrayUnique));
		if(palindromeLessThanMax != null) System.out.printf("The biggest palindrome smaller than maximum element %d is %d\n", max(arrayUnique), palindromeLessThanMax);
		else System.out.printf("There is no palindrome smaller than maximum element %d!\n", max(arrayUnique));
		
		
		
		//printing in reverse order
		Collections.reverse(array);
		System.out.println("Array in reverse order: " + array);
		
		
	}
	
/*
	Desc: calculates the number of even elements in an ArrayList
	Param: arr - array whose even elements are to be counted
	Pre: arr has to be a reference to a dynamic array
	Post: RESULT >= 0
	Result: number of even elements
	Env: -
*/
	public static int numOfEven(ArrayList<Integer> arr) {
		if(arr.isEmpty()) return 0;
		int i, even = 0;
		for(i = 0; i < arr.size(); i++ ) {
			if(arr.get(i) % 2 == 0) even++;
			}
		
		return even;
	}
	
/*
	Desc: calculates the number of odd elements in an ArrayList
	Param: arr - array whose even elements are to be counted
	Pre: arr has to be a reference to a dynamic array
	Post: RESULT >= 0
	Result: number of odd elements
	Env: -
*/
	
	public static int numOfOdd(ArrayList<Integer> arr) {
		if(arr.isEmpty()) return 0;
		int i, odd = 0;
		for(i = 0; i < arr.size(); i++ ) {
			if(arr.get(i) % 2 != 0) odd++;
			}
		
		return odd;
	}
	
	
	
	/*
	Desc: calculates the sum of elements of an array
	Param: arr - array whose even elements are to be summed
	Pre: arr has to be a reference to a dynamic array
	Post: 
	Result: sum of all elements
	Env: -
	*/
	public static double sum(ArrayList<Integer> arr) {
		if(arr.isEmpty()) return 0;
		double sum = 0;
		for(int i = 0; i < arr.size(); i++) {
			sum += (double)arr.get(i);
		}
		
		return sum;
	}
	
	
	/*
	Desc: makes a copy of the array, arranges the elements in ascending and returns the second one
	Param: arr - array of integer
	Pre: 
	Post: null, if list is empty or has only one element otherwise an integer number
	Result: the value of the second smallest number
	Env: -
	*/
	public static Integer secondSmallest(ArrayList<Integer> arr) {
		if(arr.size() < 2) return null;

		//initialising a temporary array, to avoid sorting the first array
		ArrayList<Integer> temporaryArray = new ArrayList<Integer>(arr);
		
		//first, sorting elements in increasing order
		Collections.sort(temporaryArray);
		//returning the value of element at position 1, since that is the second smallest now
		return temporaryArray.get(1);
		
	}
	
	//TODO force it to send an array with such elements
	/*
	Desc: makes an array of elements with maximum  occurrence
	Param: arr - array with unique entries, arrReps array with corresponding repetitions
	Pre: 
	Post: null if arr is empty, else an ArrayList
	Result: array with elements with max occurrance
	Env: -
	*/
	public static ArrayList<Integer> maxOccurrence(ArrayList<Integer> arr, ArrayList<Integer> arrReps) {
		if(arr.isEmpty())  return null;
		Integer maxOccurrence = max(arrReps); //the value of maximum repetitions
		ArrayList<Integer> arrayMaxOccurr = new ArrayList<Integer>(); //array for storing elements with such occurrence, in case there are multiple
			
		//storing those elements in "arrayMaxOccurr"
		for(int i = 0; i < arr.size(); i++) {
			//if the element at index "i" in arrReps equals "maxOccurrence", then the element at index "i" in "arr" has max occurrence, so we append it 
			if(arrReps.get(i) == maxOccurrence) arrayMaxOccurr.add(arr.get(i));
		}
			
		return arrayMaxOccurr;
	}
		
	/*
	Desc: finds the largest number in array
	Param: arr - dynamic array
	Pre: 
	Post: 
	Result: the value of the maximum element
	Env: -
	*/
	public static Integer max(ArrayList<Integer> arr) {
		if(arr.isEmpty())  return null;
		
		//initialising max to the value of the first element
		//setting i (index) to 1, since no point in comparing max with 0th element
		Integer max = arr.get(0), i = 0;
		
		//going through entire array to set variable max to the value of the biggest element
		while(i++ < arr.size() - 1) if(arr.get(i) > max) max = arr.get(i);
		
		return max;
	}
	
	
	
	//Repetitions in percentage terms
	/*
	Desc: prints out the information about the repetition of elements in percentage terms
	Param: arr - array with unique entries, arrReps - array with repetition
	Pre: arr - contains unique entries, arrReps contains repetitions
	Post: 
	Result: information about repetitions printed out
	Env: -
	*/
	public static void repetitions(ArrayList<Integer> arr, ArrayList<Integer> arrReps) {
		double length = sum(arrReps); //sum of elements of arrReps is the number of user entries
		System.out.printf("Repetiotions:\n");
		for(int i = 0; i < arr.size(); i++) {
			System.out.printf("Number of repetiotions of %d in percentage terms is %.1f%%!\n", 
					arr.get(i), (double) arrReps.get(i) / length * 100);
		}
		System.out.println("");
	}
	
	
	
	/*
	Desc: calculates the standard deviation of elements in array
	Param: arr - dynamic array
	Pre: 
	Post: RESULT >= 0
	Result: sqrt(((x1 - mean)^2 + (x2 - mean)^2 + ... + (xn - mean)^2) / N)
	Env: -
	*/
	public static double deviation(ArrayList<Integer> arr) {
		//initialising mean
		double mean = sum(arr) / arr.size();
		//initialising variable for storing the sum of squares as double, to avoid integer overflow
		double sqrSum = 0;
		
		//going through all elements and summing the square of difference with mean
		for(int i = 0; i < arr.size(); i++) {
			sqrSum += Math.pow((arr.get(i) - mean), 2);
		}
			
		//calculating and returning the standard deviation in the same line
		return Math.sqrt(sqrSum/arr.size());
	}
		
	/*
	Desc: calculated the meadian of an array
	Param: arr - dynamic array
	Pre: arr is dynamic array of Integers
	Post: 
	Result: X(n/2) or ((X(n/2 - 1) + X(n/2))/2
	Env: -
	*/
	public static double median(ArrayList<Integer> arr) {
		//initialising a temporary array, to avoid sorting the first array
		ArrayList<Integer> tmp = new ArrayList<Integer>(arr);
			
		//sorting elements in increasing order
		Collections.sort(tmp);
			
		//if there is an even number of elements, calculate the median
		if(tmp.size() % 2 == 0) return (double)(tmp.get(tmp.size() / 2) + tmp.get(tmp.size() / 2 - 1)) / 2;
			
		//if there is an odd number of elements, return the middle one
		else return (double) tmp.get(tmp.size() / 2);
			
	}
		
		
	//making a dynamic list of palindorme numbers
	/*
	Desc: finds all the palindromic numbers in array
	Param: arr - dynamic array of integers
	Pre: arr nonempty array
	Post: dynamic array of integers
	Result: array of palindromic numbers
	Env: -
	*/
	public static ArrayList<Integer> palindromArray(ArrayList<Integer> arr) {
		ArrayList<Integer> tmpArray = new ArrayList<Integer>();
		for(int i = 0; i < arr.size(); i++) {
			Integer num = arr.get(i);
			if(isPalindrom(num.toString()) && !tmpArray.contains(num)) tmpArray.add(num);
		}
			
		return tmpArray;
	}
		
			

	/*
	Desc: finds the biggest palindromic number in an array
	Param: arr - dynamic array 
	Pre: arr is a dynamic array of integers, max is a number
	Post: RESULT < max
	Result: palindrome smaller than biggest element in array
	Env: -
	*/
	public static Integer biggestPalindrome(ArrayList<Integer> arr, int max) {
		//if the list is empty, return null right away
		if(arr.isEmpty()) return null;
		Integer maxPalindrome = Integer.MIN_VALUE; //Integer
		//Integer maxElement = max;
		for(int i = 0; i < arr.size(); i++) {
			//checking if the given palindrome is less than maximum element and greater than current max palindrome
			if(arr.get(i) < max && arr.get(i) > maxPalindrome) maxPalindrome = arr.get(i);
		}
			
		//if there is no such palindrome, return null
		if(maxPalindrome == Integer.MIN_VALUE) return null;
			
		//else, return the palinrome found
		return maxPalindrome;
	}
		
		
	/*
	Desc: determines whether a string is a palindrome
	Param: str - a string
	Pre: nonempty string
	Post: true or false
	Result: boolean (str is palindorme)
	Env: -
	*/	
	public static boolean isPalindrom(String str) {
		//in case str is a representation of a negative number, we will drop the minus
		if(str.charAt(0) == '-') str = str.substring(1);
		for(int i = 0; i < str.length() / 2; i++) {
			if(str.charAt(i) != str.charAt(str.length() - 1 - i)) return false;
		}
		return true;
	}	
	
}


