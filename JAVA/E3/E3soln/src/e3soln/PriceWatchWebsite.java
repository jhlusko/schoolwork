package e3soln;

import java.util.Observable;
import java.util.Observer;

public class PriceWatchWebsite extends Observable implements Observer{

	private String url;
	
	public PriceWatchWebsite(String url){
		this.url = url;
	}
	
	@Override
	public void update(Observable o, Object message) {
		System.out.println("You are subscribed to " + this.url + ". It was notified about a price change.");
		System.out.println("   Notification: " + message);

	}

	
}
