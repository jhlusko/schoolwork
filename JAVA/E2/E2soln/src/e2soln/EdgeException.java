package e2soln;

/*An exception class EdgeException must be a checked exception, and needs only two members: a no-
argument constructor, and a one-argument constructor that takes a String. Each constructor should simply
call the corresponding constructor of the parent class.
*/
public class EdgeException extends Exception{
	public EdgeException() throws Exception{
		throw new Exception();
	}
	public EdgeException(String string) throws Exception{
		throw new Exception();
	}
	
}
