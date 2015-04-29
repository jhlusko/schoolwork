package a1soln;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Contains tests for each method in review
 * @author Jamie
 *
 */
public class TestReview {

	private boolean thumbsUp;
	private Reviewer reviewer;
	private String comments;
	private String response = null;
	private int reviewID;
	private static int numReviews = 1;

	@Before
	public void setUp() throws Exception {
		thumbsUp = true;
		reviewer = new Reviewer("Mr. Critic", "critic@email.com");
		comments = "This restaurant is terrible.";
		reviewID = numReviews;
		numReviews ++;
	}

	@After
	public void tearDown() throws Exception {
		thumbsUp = true; 
		reviewer = null;
		comments = null;
		reviewID = 1; 
		numReviews = 1;
		}

	@Test
	public void testReviewMethods() {
		Reviewer Critic = new Reviewer("Mr. Critic", "critic@email.com");
		//Constructs a review of McDonalds.
		Review McDonaldsReview = new Review(
				Critic,
				thumbsUp,
				"McDonalds sucks.");
		
		//Tests getComments method.
		assertTrue(McDonaldsReview.getComments() == "McDonalds sucks.");
		
		//Tests getReviewer method.
		assertTrue(McDonaldsReview.getReviewer() == Critic);
		
		//Tests getReviewID method.
		assertTrue(McDonaldsReview.getReviewID() == 1);
		
		//Tests getThumbsUp method.
		assertTrue(McDonaldsReview.getThumbsUp() == true);
		
		//Tests getResponse method.
		assertTrue(McDonaldsReview.getResponse() == null);
		
		//Tests setResponse method.
		McDonaldsReview.setResponse("The reviewer was unfair.");
		assertTrue(McDonaldsReview.getResponse() == "The reviewer was unfair.");
		
		
	}

//	@Test
//	public void testGetThumbsUp() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetReviewer() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetComments() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetResponse() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetReviewID() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetResponse() {
//		fail("Not yet implemented");
//	}

}
