package model;

import java.util.LinkedList;

public class Order extends LinkedList<Product> { 
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
