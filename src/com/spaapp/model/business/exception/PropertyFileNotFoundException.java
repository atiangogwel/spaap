package com.spaapp.model.business.exception;


public class PropertyFileNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;

	public PropertyFileNotFoundException(final String inMessage, final Throwable inNestedException)
    {
        super(inMessage, inNestedException);
    }
	
} 