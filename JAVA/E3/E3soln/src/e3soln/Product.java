package e3soln;

import java.util.Observable;

public class Product extends Observable{

	private String name;
	private double price;
	private String store;
	
	public Product(String name, double price, String store){
		this.name = name;
		this.price = price;
		this.store = store;
		
	}
	
	public void changePrice(double newPrice){
		if (this.price < newPrice){
			setChanged();
			notifyObservers(this.toString() + " The price increased to " + String.format("%.2f", newPrice) + ".");
		}
		if (this.price > newPrice){
			setChanged();
			notifyObservers(this.toString() + " The price decreased to " + String.format("%.2f", newPrice) + ".");
		}
		
		this.price = newPrice;
	}
	
	public String toString(){
		return "The price of " + this.name + " was " + String.format("%.2f", this.price) + " at " + this.store + ".";
	}
	
	
	
}
