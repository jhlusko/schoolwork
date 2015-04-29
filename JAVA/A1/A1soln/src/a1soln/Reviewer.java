package a1soln;

/**
 * Reviewers are a type of User which write reviews of restaurants.
 * @author jhlusko
 */
public class Reviewer extends User{
	
	/**
	 * Constructs a new Reviewer with a username and email.
	 * @param username The reviewer's username.
	 * @param email The reviewer's email.
	 */
	public Reviewer(String username, String email) {
        
        super(username, email);
    }
	
	/**
	 * Creates a review object and associates it with the restaurant 
	 * it refers to.
	 * @param restaurant The restaurant which the review is about.
	 * @param thumbsUp A boolean to reflect the reviewer's 
	 * opinion of the restaurant.
	 * @param comments The text of the review.
	 */
	public void writeReview(
			Restaurant restaurant, 
			boolean thumbsUp, 
			String comments){
		//makes a review object
		Review review = new Review(this, thumbsUp, comments);  
		restaurant.addReview(review); //adds the review to the restaurant
		
	}
	
}
	
	
