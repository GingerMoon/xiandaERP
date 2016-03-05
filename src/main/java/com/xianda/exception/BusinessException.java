/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xianda.exception;

/**
 *
 * @author Sanath Kodikara
 */
public class BusinessException extends Exception {

	public BusinessException() {
		super("Expense exception occured due to business logic failure");
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Exception exception) {
		super(message, exception);
	}
}
