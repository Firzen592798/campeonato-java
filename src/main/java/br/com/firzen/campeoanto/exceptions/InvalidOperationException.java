package br.com.firzen.campeoanto.exceptions;

public class InvalidOperationException extends Exception{
	String mensagem;
	public InvalidOperationException(String mensagem) {
		super();
		this.mensagem = mensagem;
	}
}
