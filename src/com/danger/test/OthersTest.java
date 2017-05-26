package com.danger.test;


public class OthersTest {
	
	private String name;
	private String address;
	private int age;
	
	public OthersTest(String name,String address,int age){
		this.name = name;
		this.address = address;
		this.age = age;
	}
	
	public OthersTest(String name,int age,String address){
		this.name = name;
		this.address = address;
		this.age = age;
	}
	/**
	 * 参数顺序不一样，也算重载....
	 * function(String,String,int)
	 * function(String,int,String)
	 * @param name
	 * @param address
	 * @param age
	 */
	public void doSometion(String name,String address,int age){
		System.out.println("exectuion -> doSometion(String name,String address,int age)");
	}
	public void doSometion(String name,int age,String address){
		System.out.println("exectuion -> doSometion(String name,int age,String address)");
	}
	
	public static void main(String[] args) {
		OthersTest othersTest = new OthersTest("danger", "chengdu", 16);
		othersTest.doSometion("danger", "chengdu", 16);
		othersTest.doSometion("danger", 16 , "chengdu");
	}
}
