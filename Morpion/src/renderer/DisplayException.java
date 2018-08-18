package renderer;

public class DisplayException extends Exception {
	public DisplayException(String err) {
		System.err.println(err);
	}
}
