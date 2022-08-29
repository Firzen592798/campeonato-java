package br.com.firzen.campeoanto.config;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;

import br.com.firzen.campeoanto.exceptions.InvalidOperationException;
import br.com.firzen.campeoanto.exceptions.NotFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //APENAS PRA REST
public class ExceptionHandlerAdvice {
	@ResponseBody
	@ExceptionHandler(NotFoundException.class) // Tudo q for EmployeeNotFoundException é tratado aqui
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ResponseEntity<String> employeeNotFoundHandler(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(InvalidOperationException.class) // Tudo q for EmployeeNotFoundException é tratado aqui
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<String> handleAPIException(ServerErrorException exception, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getLocalizedMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getConstraintViolations().forEach((error) -> {
			String fieldName = "field";
			String errorMessage = error.getMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	

	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentExceptionExceptions(IllegalArgumentException exception) {
		return "/error/404";
	}
}
