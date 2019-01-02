package Code;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.Scanner;

public class SorthingAlgorithms {
	
	static Integer[] array;
	static Random rand = new Random();
	static int number1, number2, holdNumber1, holdNumber2, max, min, answer, range, pivotPosition,rangeMax, rangeMin;
	static long timeStart = 0, timeTotal = 0;
	static Scanner input = new Scanner(System.in);
	static Boolean flag;
	
	public static void setArray()
	{
		System.out.print("What is the minimun value of the array?: ");
		min = input.nextInt();
		
		System.out.print("What is the maximum value of the array?: ");
		max = input.nextInt();
		
		max += 1;
		
		range = max - min;
		
		array = new Integer[range];
		
		int inputValue;
		
		for(int i = 0; i < range; i++)
		{
			inputValue = min + i;
			array[i] = inputValue;
		}
	}
	
	/*
	 * Generates a random integer
	 */
	public static int generateNumber()
	{
		int randomNum;
		randomNum = rand.nextInt(range);
		return randomNum;
	}
	
	/*
	 * Randomize the array
	 */
	public static void Randomize()
	{
		for(int i = 0; i < range*2; i++)
		{
			number1 = generateNumber();
			number2 = generateNumber();
			holdNumber1 = array[number1];
			holdNumber2 = array[number2];
			array[number1] = holdNumber2;
			array[number2] = holdNumber1;
		}
	}
	
	/*
	 * Display the array
	 */
	public static void Display() 
	{
		for( int i = 0; i < range; i++) 
			System.out.println(array[i]);
	}
	
	/*
	 * Return the smallest number in the array
	 */
	public static void smallestNumber()
	{
		int smallestNum = max*2;
		for(int i = 0; i < range; i++)
			if(smallestNum > array[i])
				smallestNum = array[i];
		
		System.out.println("The smallest number is " + smallestNum);
	}
		
	/*
	 * Return largest number in array
	 */
	public static void largestNumber()
	{
		int largestNum = min*2;
		for(int i = 0; i < range; i++)
			if (largestNum < array[i])
				largestNum = array[i];

		System.out.println("The largest number is " + largestNum);
	}
	
	/*
	 * Bubble sort the array
	 * Compares one cell to its next and if the next cell is smaller than the previous,
	 * swap the two cells' values. The sorting will continue as long as there was
	 * a swap done in the previous run
	 */
	public static void bubbleSort()
	{
		System.out.println("Bubble sorting...");//Print initiation of sorting
		do//Continue sorting the array as long as there was a swap 
		{
		flag = true;
		
			for(int i = 0; i < range; i++)
			{
				int k = i + 1;
				if(k == range)
				{
					break;
				}
				else if(array[i] > array[k]){//if previous cell if larger than next cell, sway values of the two cells
					holdNumber1 = array[i];//Hold cells' values in variables
					holdNumber2 = array[k];
					array[i] = holdNumber2;//Change cells' values
					array[k] = holdNumber1;
					flag = false;
				}
			}
		}while(flag == false);
	}
	
	/*
	 * Insertion sort the array
	 * The sort will go through the entire array and find the smallest number and place it at the start,
	 * continuing this until it reaches the end. Before the founded number is placed into position,
	 * the entire array is shifted by one until the position of the smallest number, then the founded
	 * number is placed into position.
	 */
	public static void insertionSort()
	{
		System.out.println("Insertion sorting...");
		int holdingNumber, holdingPosition;
		
		for(int i = 0; i < range; i++)//start from the beginning or array and continue till the end
		{
			holdingNumber = array[i];
			holdingPosition = i;
			for(int x = i; x < range; x++)//loop to find the next smallest number
			{
				if(holdingNumber > array[x]) //Find the current smallest number
				{
					holdingNumber = array[x];
					holdingPosition = x;
				}
			}
			if(holdingPosition == i)//if the current position of the array already has the smallest number, do not do anything
			{
				continue;
			}
			else//if not, continue sorting for this cycle
			{
				for(int y = holdingPosition; y > i; y--)//shift the array from current sort position to found next smalest number
				{
					int k = y - 1;
					if(k < 0)
					{
						break;
					}
					else
					{
						array[y] = array[k];
					}
				}
				array[i] = holdingNumber; //place smallest number to current sorting position
			}
		}
	}
	
	/*
	 * Binary search the array
	 * Access the middle of the array and compare the search number to the middle cell value.
	 * If search value smaller, than half the left half and do the same until cell is found with
	 * the search value
	 */
	public static int binarySearch(int searchValue) throws InterruptedException
	{
		int highRange = range, lowRange = 0, checkPosition = range;
		while(true)
		{
			checkPosition=(highRange + lowRange)/2;
			if(array[checkPosition] == searchValue)
				break;
			else if (array[checkPosition] > searchValue)
			{
				highRange = checkPosition;
			}
			else if (array[checkPosition] < searchValue)
			{
				lowRange = checkPosition;
			}
		}	
		return checkPosition;
	}
	
	public static int linearSearch(int searchValue)
	{
		int checkPosition = 1;
		
		for(int i = 0; i < range; i++)
		{
			if(array[i] == searchValue)
			{
				checkPosition = i;
				break;
			}
		}
		return checkPosition;
	}
	
	public static void selectionSort()
	{
		System.out.println("Selection sorting...");
		
		int holdingNumber, holdingPosition = 0, swappingNumber, x;
		for(int i = 0; i < range; i++)
		{
			holdingNumber = array[i];
			for(x = i; x < range; x++)
			{
				if(holdingNumber >= array[x]) //Find the current smallest number
				{
					holdingNumber = array[x];
					holdingPosition = x;
				}
			}
//			System.out.println("The smallest number is :" +holdingNumber+" in position: "+holdingPosition);
			if(holdingPosition == i)
				continue;
			else //Swapping number positions
			{
				swappingNumber = array[i];
				array[i] = holdingNumber;
				array[holdingPosition] = swappingNumber;
			}
		}
	}
	
	public static void radixSort() 
	{
		
		Integer[] arrayHolding = new Integer[range];
		double testingNumber = 1;
		int i, highestSigDig;
		for(i = 1; testingNumber != 0; i++)
		{
			testingNumber = (range+1) / (java.lang.Math.pow(10,i));
			if(testingNumber < 10)
				testingNumber = 0;
		}
		
		highestSigDig = i;
		
		for (i = 0; i <= highestSigDig; i++)
		{
			int pointer = 0;
			for(int x = 0; x < 10; x++)
			{
				for(int z = 0; z < range; z++)
				{
					if(array[z] == (Integer) null)
						continue;
					else if(digitInPlace(i, z) == x)
						{
							arrayHolding[pointer] = array[z];
							array[z] = null;
							pointer++;
						}
				}
			}
			for(int y = 0; y < range; y++)
			{
				array[y] = arrayHolding[y];
			}
		}
	}
	
	public static int digitInPlace(int digitPosition, int arrayPosition)
	{
		double sigDigPlace = java.lang.Math.pow(10,(digitPosition));
		int foundNumber = (int) ((array[arrayPosition]/sigDigPlace)%10);
		return Math.abs(foundNumber);
	}
	
	public static void quickSort()
	{
		int pivot = array[range-1], pivotPosition = range -1;
		rangeMax = range -1;
		rangeMin = 0;
//		while(rangeMax != rangeMin)
//		{
			System.out.println("The rangeMax is "+rangeMax +" and the rangeMin is "+rangeMin);
//			quickSorting(rangeMin, rangeMax, pivot, pivotPosition);
			System.out.println("The next part of the array will be sorted between "+rangeMin+" and "+rangeMax);
//		}
//		rangeMin = 0;
//		rangeMax = (range-1)/2;
//		while(rangeMax != rangeMin)
//		{
//			quickSorting(rangeMin, rangeMax, pivot, pivotPosition);
//			rangeMin *= 2;
//		}
	}
	
	public static void quickSorting(int beginPointer, int endPointer) throws InterruptedException
	{
		int pivotPosition = (endPointer+beginPointer)/2;
		int swappingNumber, rightPointer = endPointer, leftPointer = beginPointer;
		while(leftPointer != rightPointer)
		{
			while(array[leftPointer] < array[pivotPosition] && leftPointer < pivotPosition)
				 leftPointer++;
			while(array[rightPointer] > array[pivotPosition] && rightPointer > pivotPosition)
				rightPointer--;
			 if(leftPointer != rightPointer)
			 {
				swappingNumber = array[leftPointer];
				array[leftPointer] = array[rightPointer];
				array[rightPointer] = swappingNumber;
			 }
			 if(leftPointer == pivotPosition)
				pivotPosition = rightPointer;
			 else if(rightPointer == pivotPosition)
				pivotPosition = leftPointer;
		}
		if((pivotPosition - beginPointer) > 1)
			quickSorting(beginPointer, pivotPosition - 1);
		if((endPointer - pivotPosition) > 1)
			quickSorting(pivotPosition + 1, endPointer);
	}

	public static void main(String[] args) throws InterruptedException {
		
		setArray();
		
		while(true){
			timeTotal = java.lang.System.currentTimeMillis() - timeStart;
			System.out.print("\n Would you like to do with the array? \n 0. Exit \n 1. Randomize \n 2. Bubble Sort \n 3. Insertion Sort \n 4. Selection Sort \n 5. Radix Sort \n 6. Quick Sort \n 7. Display \n 8. Smallest Number \n 9. Largest Number \n 10. Binary Search \n 11. Linear Search \n 12. Create New Array\n 13. Digit in Place\n 14. Time it took to do previous action \n : ");
			answer = input.nextInt();
			timeStart = java.lang.System.currentTimeMillis();
			if(answer == 1)//Randomize the array
				Randomize();
			else if (answer == 2)//Bubble sort the array
				bubbleSort();
			else if (answer == 3)//Insertion sort the array
				insertionSort();
			else if (answer == 4)//Selection sort the array
				selectionSort();
			else if (answer == 5)//Radix sort the array
				radixSort();
			else if (answer == 6)//Quick sort the array
				quickSorting(0, range - 1);
			else if (answer == 7)//Display the array
				Display();
			else if (answer == 8)//Find the smallest number in the array
				smallestNumber();
			else if (answer == 9)//find the largest number in the array
				largestNumber();
			else if (answer == 10)//Search for specific value through binary search
			{
				System.out.print("What value would you like to search for?: ");
				int searchValue = input.nextInt();
				System.out.print(searchValue + " is in position " + binarySearch(searchValue));
			}
			else if (answer == 11)//search for specific value through linear search
			{
				System.out.print("What value would you like to search for?: ");
				int searchValue = input.nextInt();
				System.out.print(searchValue + " is in position " + linearSearch(searchValue));
			}
			else if (answer == 12)//Create new array
				setArray();
			else if (answer == 13)
			{
				System.out.print("What digit place do you want to look for?: ");
				int digitPlace = input.nextInt();
				System.out.print("What place in the array are you looking for?: ");
				int arrayPosition = input.nextInt();
				System.out.print("The digit is: "+digitInPlace(digitPlace, arrayPosition));
			}
			else if(answer == 14)//How long it took to do the previous action
				System.out.println("It took : "+timeTotal+" milliseconds");
			else if(answer== 0)
				break;
		}
		System.out.println("Goodbye");
		
	}
}