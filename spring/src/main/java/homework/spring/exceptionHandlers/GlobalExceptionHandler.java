package homework.spring.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import homework.spring.exceptions.EntityNotFoundException;
import homework.spring.exceptions.LogicException;
import homework.spring.exceptions.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
    public ResponseEntity<AppError> catchEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler
    public ResponseEntity<AppError> catchValidationException(ValidationException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler
    public ResponseEntity<AppError> catchLogicException(LogicException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
