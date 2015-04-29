package a1soln;

import java.util.HashMap;
import java.util.Map;
import a1soln.Restaurant;

/**
 * Owners are a type of user which have restaurants and respond to reviews about their restaurants.
 * @author jhlusko
 */
public class Owner extends User{
	private Map<String, Restaurant> restaurants;  //from restaurant names to restaurants
    
	
	
	/**
     * Constructs a new User with a username and email.
     * @param username The Owner's username.
     * @param email The Owner's email.
     * @param restaurants A map which associates the restaurants the owner owns with the restaurants names.
     */
    public Owner (String username, String email) {
        
        super(username, email); 
        restaurants = new HashMap<String, Restaurant>(); //ask adam should it be this.restaurants
    }
    
    /**
     * Adds a restaurant object to the owner's list of restaurants.
     * @param restaurant Owner's new restaurant.
     */
    public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getName(), restaurant); //puts in <restaurant owner's name, restaurant>
    }
    
    /**
     * If owner owns restaurant return true, else false.
     * @param restaurantName The restaurant's name.
     * @return boolean
     */
    public boolean owns(String restaurantName){
    	return restaurants.containsKey(restaurantName); //looks up if the restaurantName is in the Owner's HashMap of restaurants
    }
    
    /**
     * Creates a response which is associated with (a member variable of) the review object.
     * @param reviewID The review (which is being responded to)'s identifying number.
     * @param restName The name of the restaurant which was the subject of the review.
     * @param response The content (text) of the review.
     */
    public void respondToReview(int reviewID, String restName, String response){
    	Restaurant restaurant = getRestaurant(restName); //finds the restaurant (object) with the correct name and assigns it to "restaurant"
    	Review review = restaurant.getReview(reviewID); //finds the review with the correct ID and assigns it to "review"
    	review.setResponse(response); //update's the review object's member variable "response" with new content 
    }
    
    /**
     * Returns a restaurant from its name.
     * @param name The restaurant's name
     * @return Restaurant
     */
    public Restaurant getRestaurant(String name){
    	return restaurants.get(name);
    }
    
}
