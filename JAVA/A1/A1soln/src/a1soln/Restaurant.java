package a1soln;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Restaurants have a number of attributes such a name, an owner, a location, 
 * a priceRange, types of offered cuisine and are the subject of reviews.
 * @author jhlusko
 */
public class Restaurant {
	protected String name;
	protected String neighbourhood;
	protected String priceRange;
	protected List<String> cuisines;
	protected Map<Integer, Review> reviews;  //from ReviewID to review, note I'm using Integer as a wrapper for int.

	/**
	 * Constructs a restaurant with a name, neighbourhood, 
	 * price, list of offered cuisines.
	 * @param name The name of the restaurant.
	 * @param neighbourhood The location of the restaurant.
	 * @param priceRange The price range of the restaurant.
	 * @param cuisines A list of cuisines offered by the restaurant.
	 * @param reviews A map of reviews of the restaurant with the
	 * reviewID as key for the review.
	 */
	public Restaurant(String name, String hood, String price, List<String> cuisines){
		this.name = name;
		this.neighbourhood = hood;
		this.priceRange = price;
		this.cuisines = cuisines;
		reviews = new HashMap<Integer, Review>();
	}

	/**
	 * Adds a review to the map of reviews.
	 * @param review A review of the restaurant.
	 */
	public void addReview(Review review){
		reviews.put(review.getReviewID(), review);
	}

	/**
	 * Returns the name of the restaurant.
	 * @return name The name of the restaurant.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the location of the restaurant.
	 * @return neighbourhood The location of the restaurant.
	 */
	public String getNeighbourhood() {
		return neighbourhood;
	}

	/**
	 * Returns the price range of the restaurant.
	 * @return priceRange The price range of the restaurant.
	 */
	public String getPriceRange() {
		return priceRange;
	}

	/**
	 * Returns the types of cuisines offered by the restaurant.
	 * @return cuisines A list of cuisines offered by the restaurant.
	 */
	public List<String> getCuisines() {
		return cuisines;
	}

	/**
	 * Returns all the reviews about the restaurant.
	 * @return reviews A map of reviews of the restaurant with the
	 * reviewID as key for the review.
	 */
	public Map<Integer, Review> getReviews(){
		return reviews;//ask adam why this needed to be map, not hashmap
	}

	/**
	 * Returns the requested review.
	 * @param reviewID The review identifier.
	 * @return review The requested review.
	 */
	public Review getReview(int reviewID){
		return this.reviews.get(reviewID);
	}

	/**
	 * Returns the percentage of Reviews for that Restaurant for which the
	 * Reviewer gave a thumbs up, as a value between 0.0 and 100.0.
	 * @return double The percentage of the restaurant's reviews which were 
	 * positive.
	 */
	public double percentageThumbsUp(){
		//initialize counters for the number of positive and negative reviews
		int numThumbsUp = 0;
		int numThumbsDown = 0;
		
		/*turn the HashTable into a collection of its values (reviews)
		because collections are iterable */
		Collection<Review> collectionReviews = reviews.values();
		Iterator<Review> itr = collectionReviews.iterator(); // the iterator
	    /*cycle through the collection, evaluating each element (a review) for
	     * if it is a positive review and updating the counters accordingly
	     */
		while(itr.hasNext()){
	    	Review element = itr.next();
			if(element.getThumbsUp()){
				numThumbsUp ++;
			}
			else{
				numThumbsDown ++;
			}
		}
		return 100 * (numThumbsUp/(numThumbsUp + numThumbsDown)); 

	}

	/**
	 * Adds a new type of cuisine to the list of offered cuisines
	 * @param cuisine The new type of cuisine.
	 */
	public void addCuisine(String cuisine){
		cuisines.add(cuisine);
	}

	/**
	 * Removes a type of cuisine from the list of offered cuisines.
	 * @param cuisine The type of cuisine to be removed from the list of
	 * offered cuisines.
	 */
	public void removeCuisine(String cuisine){
		cuisines.remove(cuisine);
	}

}
