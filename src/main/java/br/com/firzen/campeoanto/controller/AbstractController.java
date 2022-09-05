package br.com.firzen.campeoanto.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.firzen.campeoanto.services.AbstractService;

public class AbstractController<T> {
	
	protected final static String URL_PAGE = "";
		
	protected final static String URL_LIST = "/list";
	
	protected final static String URL_VIEW = "/view";
	
	protected final static String URL_FORM = "/form";
	
	protected final static String URL_EDIT = "/edit";
	
	protected final static String URL_DELETE = "/delete";

	@Autowired
	public AbstractService<T> service;
}


