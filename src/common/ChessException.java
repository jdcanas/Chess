/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package common;

public class ChessException extends Exception
{
	/**
	 * Every instance of this exception must have a message describing the exception.
	 * 
	 * @param message
	 *            the string describing the error causing the exception
	 */
	public ChessException(String message)
	{
		super(message);
	}

	/**
	 * An exception that was caused by some other exception.
	 * 
	 * @param message
	 *            the string describing the error causing the exception
	 * @param cause
	 *            the error that caused this exception
	 */
	public ChessException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
