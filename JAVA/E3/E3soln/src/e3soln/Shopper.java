package e3soln;

import java.util.Observable;
import java.util.Observer;

public class Shopper implements Observer{
	
	private String name;
	
	public Shopper(String name){
		this.name = name;
		
	}

	@Override
	public void update(Observable product, Object message) {
		System.out.println(this.name + " was notified about a price change.");
		System.out.println("   Notification: " + message);
	}
}
