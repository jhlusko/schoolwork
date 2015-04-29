package a1soln;

/**
 * An interface to constrain takeout restaurants.
 * @author Jamie
 *
 */
public interface Takeout {
	
	/**
	 * Take out restaurants must have the getAvgWaitTime method which returns
	 * the average wait time for receiving food at that restaurant.
	 * @return int The average wait time for receiving food at the restaurant.
	 */
	public int getAvgWaitTime();

}
