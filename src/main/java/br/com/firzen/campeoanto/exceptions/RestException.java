package br.com.firzen.campeoanto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Ocorreu um erro na execução")
public class RestException extends Exception {
	public RestException(String msg) {
		super(msg);
	}
	public RestException() {
		super();
	}
}
