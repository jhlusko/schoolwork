/**
 * 
 */
package a1soln;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jamie
 * Contains tests for each method of Owner and User.
 */
public class TestOwner {

	private String username;
	private String email;
	private Owner Bob;

	@Before
	public void setUp() throws Exception {
		username = new String("Bob");
		email = new String("bob@email.com");
		Bob = new Owner(username, email);

	}

	@After
	public void tearDown() throws Exception {
		username = null;
		email = null;
		Bob = null;
	}

	/**
	 * Tests the addRestaurant method.
	 */
	@Test
	public void testOwnerMethods() {
		//Tests getName method
		//assertTrue("Bob" == Bob.getName());
		assertEquals("Bob", Bob.getUsername());
		
		//Tests getEmail method
		assertEquals("bob@email.com", Bob.getEmail());
		
		//Tests setEmail method
		Bob.setEmail("new@email.com");
		assertEquals("new@email.com", Bob.getEmail());
		
		//Tests owns method when Owner owns 0 restaurants
		assertFalse(Bob.owns("McDonalds"));
		
		//Creates restaurant
		//how else could i have initialized this say to contain "Burgers"?
		// i had: new List<String>("Burgers") but that returned an error - what do.
		List<String> cuisines = new LinkedList();
		cuisines.add("Burgers");
		Restaurant mcDonalds = new Restaurant(
				"McDonalds", 
				"Toronto", 
				"$", 
				cuisines);
		Bob.addRestaurant(mcDonalds);
		
		//Tests owns method when Owner owns 1 restaurant
		assertTrue(Bob.owns("McDonalds"));
		
		//Tests getRestaurant method when Owner owns 1 restaurant
		assertEquals(Bob.getRestaurant("McDonalds"), mcDonalds);

		//Adds a second restaurant, Subway
		Restaurant Subway = new Restaurant(
				new String("Subway"), 
				new String("Toronto"), 
				new String("$"), 
				cuisines);

		Bob.addRestaurant(Subway);
		
		//Tests owns method and getRestauarnt method when Owner owns 2 restaurant
		assertTrue(Bob.owns("Subway"));
		assertEquals(Bob.getRestaurant("Subway"), Subway);

		//Makes sure McDonalds is still available
		assertTrue(Bob.owns("McDonalds"));
		assertEquals(Bob.getRestaurant("McDonalds"), mcDonalds);

		//Creates a review of McDonalds
		Review McDonaldsReview = new Review(
				new Reviewer("Mr. Critic", "critic@email.com"), 
				true, 
				"McDonalds is an awful restaurant.");
		//note reviewID should be 1.
		mcDonalds.addReview(McDonaldsReview);

		//Tests respondToReview method
		Bob.respondToReview(1, "McDonalds", "Your review is unfair and unsupported.");
		assertEquals(McDonaldsReview.getResponse(), "Your review is unfair and unsupported.");


	}

	



}
