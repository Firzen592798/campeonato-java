package br.com.firzen.campeoanto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "video not found")
@NoArgsConstructor
public class NotFoundException extends RuntimeException {
	public NotFoundException(String msg) {
		super(msg);
	}
}
