package a1soln;

/**
 * Reviews are created by reviewers and refer to restaurants.
 * @author jhlusko
 */
public class Review {

	private boolean thumbsUp;
	private Reviewer reviewer;
	private String comments;
	private String response = null;
	private int reviewID;
	private static int numReviews = 1;

	/**
	 * Constructs a review which is about a restaurant either does or does not
	 * give it a thumbsUp and gives it some comments (text).
	 * @param response The restaurant owner can update this variable from its
	 * default null to a string containing their response to the review.
	 * @param numReviews A static variable which tracks how many reviews have
	 * been made for the purpose of creating reviewIDs for new reviews.
	 * @param reviewID A unique review identifier.
	 * @param reviewer The user who created the review.
	 * @param thumbsUp A boolean to reflect the reviewer's opinion of the restaurant.
	 * @param comments The content (text) of the review.
	 */
	public Review(Reviewer reviewer, boolean thumbsUp, String comments){
		this.reviewer = reviewer;
		this.thumbsUp = thumbsUp;
		this.comments = comments;
		this.reviewID = Review.numReviews;
		Review.numReviews++;
	}
	
	/**
	 * Returns the value of the thumbsUp variable.
	 * @return the value of the thumbsUp variable.
	 */
	public boolean getThumbsUp(){
		return this.thumbsUp;
	}

	/**
	 * Returns the reviewer who created the review.
	 * @return the reviewer who created the review.
	 */
	public Reviewer getReviewer(){
		return this.reviewer;
	}

	/**
	 * Returns the comment section of the review.
	 * @return the comment section of the review.
	 */
	public String getComments(){
		return this.comments;
	}

	/**
	 * Returns the response associated with the review.
	 * @return the response associated with the review.
	 */
	public String getResponse(){
		return this.response;
	}

	/**
	 * Returns the review identifier.
	 * @return the review identifier.
	 */
	public int getReviewID() {
		return reviewID;
	}

	/**
	 * Updates the response variable.
	 * @param response The owner's response to their restaurant review.
	 */
	public void setResponse(String response){
		this.response = response;
	}

}

