package a1soln;

import java.util.List;

/**
 * Takeout restaurants are a type of restaurant in which food can be ordered to go.
 * @author Jamie
 */
public class TakeoutRestaurant extends Restaurant implements Takeout{
	private int avgWaitTime;

	/**
	 * Constructs a restaurant with the usual parameters and additionally an average wait time for food in the restaurant.
	 * @param name The name of the restaurant.
	 * @param hood The location of the restaurant.
	 * @param price The price range of the restaurant.
	 * @param cuisines A list of cuisines offered by the restaurant.
	 * @param reviews A map of reviews of the restaurant with the
	 * @param avgWaitTime The average wait time for food in the restaurant.
	 */
	public TakeoutRestaurant(
			String name,
			String hood,
			String price,
			List<String> cuisines,
			int avgWaitTime) {
		super(name, hood, price, cuisines);
		this.avgWaitTime = avgWaitTime;
	}

	/**
	 * Returns the average wait time for food in the restaurant.
	 * @return avgWaitTime The average wait time for food in the restaurant.
	 */
	public int getAvgWaitTime() {
		return avgWaitTime;
	}



}
