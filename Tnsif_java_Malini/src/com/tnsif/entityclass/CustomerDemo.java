package com.tnsif.entityclass;

public class CustomerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer c1=new Customer();
		c1.setId(101);
		c1.setCname("Malini");
		c1.setCity("Cuddalore");
		System.out.println(c1);
		Customer c2=new Customer();
		c2.setId(102);
		c2.setCname("Dharshini");
		c2.setCity("Cuddalore")		;
		System.out.println(c2);
	}

}
