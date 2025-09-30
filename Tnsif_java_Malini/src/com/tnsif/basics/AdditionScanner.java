package com.tnsif.basics;
import java.util.*;

public class AdditionScanner {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner  s=new Scanner(System.in);
		System.out.println("Enter the numbers:");
		int a=s.nextInt();
		int b=s.nextInt();
		int c=a+b;
		System.out.println("Addition of a="+a+" and b="+b+" is"+c);
	}

}
