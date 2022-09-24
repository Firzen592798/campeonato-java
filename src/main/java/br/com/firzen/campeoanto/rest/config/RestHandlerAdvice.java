package br.com.firzen.campeoanto.rest.config;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.firzen.campeoanto.exceptions.RestException;

@ControllerAdvice
public class RestHandlerAdvice extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;

	//Dando override no método que trata a exceção por padrão
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Problema.Campo> campos = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			campos.add(new Problema.Campo(((FieldError)error).getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale())));
		}
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(RestException.class)
	public ResponseEntity<Object> handleNegocio(RestException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST; //Código 400
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
//	@ResponseBody
//	@ExceptionHandler(RestException.class) // Tudo q for EmployeeNotFoundException é tratado aqui
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	ResponseEntity<?> handleRestException(RestException ex) {
//		Map<String, String> errors = new HashMap<String, String>();
//		errors.put("message", ex.getMessage());
//		errors.put("code", "500");
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
//	}
//
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//		Map<String, String> errors = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName = ((FieldError) error).getField();
//			String errorMessage = error.getDefaultMessage();
//			errors.put(fieldName, errorMessage);
//		});
//		return errors;
//	}
//	
//	@ExceptionHandler(ConstraintViolationException.class)
//	public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
//		Map<String, String> errors = new HashMap<>();
//		ex.getConstraintViolations().forEach((error) -> {
//			String fieldName = "field";
//			String errorMessage = error.getMessage();
//			errors.put(fieldName, errorMessage);
//		});
//		return errors;
//	}
}
