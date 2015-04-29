package a1soln;

/**
 * This class is abstract: it cannot be instantiated. It will be used to create Owners and Reviewers which are instantiated.
 * @author jhlusko
 */

public abstract class User {
	
	protected String username;
    protected String email;
    

	/**
	 * Constructs a User with a username and email.
	 * @param username //user's username
	 * @param email // user's email
	 */
	
	public User (String username, String email){
		this.username = username;
		this.email = email;
	}
	
	/**
     * Returns this User's username.
     * @return The username of this User.
     */
    public String getUsername() {
        return this.username;
    }
    
    
    /**
     * Returns this User's email.
     * @return The email of this User.
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Sets the email of this Person to email.
     * @param email The new email of this Person.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
    


