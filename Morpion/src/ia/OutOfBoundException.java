package ia;

public class OutOfBoundException extends Exception {
	
	private String Message;
	
	public OutOfBoundException() {
		Message = "Selection hors de la grille";
	}
	
	@Override
	public String getMessage() {
		return Message;
	}
}
