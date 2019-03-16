package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class IncompatibleMonster extends RuntimeException {

	public IncompatibleMonster() {
	}

	public IncompatibleMonster(String message) {
		super(message);
	}
	
}
