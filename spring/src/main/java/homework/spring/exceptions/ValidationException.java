package homework.spring.exceptions;

public class ValidationException extends RuntimeException {
	public ValidationException(String message) {
		super(message);
	}
}

