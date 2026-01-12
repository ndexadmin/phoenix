package com.ndexsystems.phoenix.util;
/**
 * <code>FatalError</code> represents the extension of <code>RunTimeException</code> implemented for use within an Ndex application.
 */
public class FatalError extends Error
{
	private static final long serialVersionUID = -7133054955227537590L;

	
	/**
	 * Creates a FatalError object.
	 */
	public FatalError()
	{
		super();
	}

	/**
	 * FatalError constructed with the message of a runtime exception..
	 * 
	 * @param s
	 *            holds the message value for FatalError.
	 */
	public FatalError(String aMessage)
	{
		super(aMessage);
	}

	/**
	 * This method created a Fatal error from an existing runtime exception.
	 * 
	 * @param e
	 *            hold the exception from which FatalException will be built.
	 */
	public FatalError(String aMessage, Throwable aThrowable)
	{
		super(aMessage, aThrowable);
	}

	/**
	 * This method created a Fatal error from an existing runtime exception.
	 * 
	 * @param e
	 *            hold the exception from which FatalException will be built.
	 */
	public FatalError(Throwable aThrowable)
	{
		super("A fatal error has occured", aThrowable);
	}

	/**
     *
     */

}