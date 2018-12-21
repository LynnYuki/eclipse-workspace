package com.ann.test;
@Description("I am an Inteface")
public interface Person {
	@Description("I am an interface method")
	public String name();
	public int age();
	@Deprecated
	public void sing();
	
}
