import java.util.Scanner;
import java.util.*;

public class spiral_of_numbers {
	static Scanner in = new Scanner(System.in);
	//right - 0, down - 1, left - 2, up - 3 
	static int inputNumber = 8, array[][], direction = 0, hor = 0, ver = 0, spin = 1;
	
	public static void main(String []args) {
		// Testing numbers from 10 till 100
		/*
		for (int p = 10; p < 100; p++) {
			inputNumber = p;
			direction = 3;
			spin = 1;
		*/
		//input number
		try {
			System.out.print("Number (must be number > 1)\nEnter: ");
			inputNumber = in.nextInt();
			System.out.print("Spin direction (0 - clockwise, 1 - counter-clockwise)\nEnter: ");
			spin = in.nextInt();
			System.out.print("Direction (0 - right, 1 - down, 2 - left, 3 - up)\nEnter: ");
			direction = in.nextInt();
			if (inputNumber < 2 | inputNumber > 100000 | spin < 0 | spin > 1 | direction < 0 | direction > 3)
				throw new Exception();
		} catch (Exception exc) { 
			System.out.println("Wrong input, parameters will be set by default.\n Number - 8, spin -  clockwise, direction - right.");
			inputNumber = 8;
			spin = 0;
			direction = 0;
			exc.printStackTrace(); 
		}
		
		int UpperSquare = (int)Math.pow((int)Math.sqrt(inputNumber) + 1, 2);
		int Square = (int)Math.pow((int)Math.sqrt(inputNumber), 2);
		
		int UpperSquareSqrt = (int)Math.sqrt(UpperSquare);
		int SquareSqrt = (int)Math.sqrt(Square);
		
		//determining size of rectangle or square
		if ( inputNumber > Square && inputNumber <= Square + SquareSqrt ) { 
			if (direction%2 == 0) 
				array = new int[ SquareSqrt ][ UpperSquareSqrt ];
			else
				array = new int[ UpperSquareSqrt ][ SquareSqrt ];
		} else if (inputNumber > Square + SquareSqrt) {
			array = new int[ UpperSquareSqrt ][ UpperSquareSqrt ];
		} else {
			array = new int[ SquareSqrt ][ SquareSqrt ];
		}
		
		int halfArrHeight = (int)(array.length/2);
		int halfArrWidth = (int)(array[0].length/2);
		
		//different spin direction - different position of '1'  
		if (spin == 0) {
			//determining vertical start position
			if (array.length%2 != 0) 
				ver = halfArrHeight;
			else 
				if (direction == 0 | direction == 1)
					ver = halfArrHeight - 1;
				else
					ver = halfArrHeight;
			
			//determining horizontal start position
			if (array[0].length%2 != 0) 
				hor = halfArrWidth;
			else 
				if (direction == 0 | direction == 3)
					hor = halfArrWidth - 1;
				else
					hor = halfArrWidth;
		} else {
			//determining vertical start position
			if (array.length%2 != 0) 
				ver = halfArrHeight;
			else 
				if (direction == 0 | direction == 3)
					ver = halfArrHeight;
				else
					ver = halfArrHeight - 1;
			
			//determining horizontal start position
			if (array[0].length%2 != 0) 
				hor = halfArrWidth;
			else 
				if (direction == 0 | direction == 1)
					hor = halfArrWidth - 1;
				else
					hor = halfArrWidth;
		}
		
		//O(array.length*array[0].length)
		//changing direction by this scheme:
		// 1 1    2   2      3     3        4       4
		//[0-1 - 2-2-3-3 - 0-0-0-1-1-1 - 2-2-2-2-3-3-3-3 -...]
		for (int i = 0, step = 1, spiral = 1, cdStep = step, cdSpiral = cdStep*2; i < array.length*array[0].length; i++) {
			try {
			array[ver][hor] = (inputNumber >= i+1)?i + 1 : 0;
			} catch (ArrayIndexOutOfBoundsException exc) {
				//in case of an error the coordinates will be printed
				System.out.println(inputNumber + " " + (array.length-1) + " " + (array[0].length-1) + " " + ver + " " + hor + "\t\t\t---------------------------------");
				break;
			}
			move();
			cdStep--;
			cdSpiral--;
			if (cdSpiral == 0) {
				cdSpiral = (++spiral)*2;
				step++;
			}
			if (cdStep == 0) {
				changeDir();
				cdStep = step;
			}
		}
		
		//O(array.length*array[0].length)
		for (int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++) 
				System.out.print(array[i][j] + "\t");
			System.out.println();
		}
		
	}
	
	public static void changeDir() {
		if (spin == 0) 
			switch (direction) {
			case 0:
				direction++;
				break;
			case 1:
				direction++;
				break;
			case 2:
				direction++;
				break;
			case 3:
				direction = 0;
				break;
			}
		else 
			switch (direction) {
			case 0:
				direction = 3;
				break;
			case 1:
				direction--;
				break;
			case 2:
				direction--;
				break;
			case 3:
				direction--;
				break;
			}
	}
	
	public static void move() {
		switch (direction) {
		case 0:
			hor++;
			break;
		case 1:
			ver++;
			break;
		case 2:
			hor--;
			break;
		case 3:
			ver--;
			break;
		}
	}
}
//	TEST
//System.out.println(ver + " " + hor + " " + (inputNumber < (int)((UpperSquare + LowerSquare)/2)+1));
//System.out.println(UpperSquare+ " " + LowerSquare + " " + array.length + " " + array[0].length);
//System.out.println("beg " + (i+1) + ", dir " + direction + ", ver " + ver + ", hor " + hor);