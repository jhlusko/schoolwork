package a1soln;

import java.util.List;
import java.util.Date;
/**
 * A licensed restaurant is a type of restaurant which has a license which expires at some date.
 * @author Jamie
 *
 */
public class LicensedRestaurant extends Restaurant{

	private Date expiryDate;
	/**
	 * Constructs a restaurant with the usual paramaters plus an expiration date.
	 * @param name The name of the restaurant.
	 * @param hood The location of the restaurant.
	 * @param price The price range of the restaurant.
	 * @param cuisines A list of cuisines offered by the restaurant.
	 * @param reviews A map of reviews of the restaurant with the
	 * reviewID as key for the review.
	 * @param date The expiration date of the license.
	 */
	public LicensedRestaurant(String name, String hood, String price,
			List<String> cuisines, Date date) {
		super(name, hood, price, cuisines);
		this.expiryDate = date;
	}
	
	/**
	 * Returns the expiration date of the license.
	 * @return date The expiration date of the license.
	 */
	public java.util.Date getExpiryDate(){
		return expiryDate;
	}
	
	/**
	 * Updates the expiration date of the license.
	 * @param date The expiration date of the license.
	 */
	public void setExpiryDate(java.util.Date date){
		this.expiryDate = date;
	}

}
