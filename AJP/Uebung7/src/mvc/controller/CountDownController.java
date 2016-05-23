package mvc.controller;

import java.util.Observer;

/**
 * The interface for the controller class of a {@code CountDownView}. Can listen
 * to a {@code CountDownModel} via Java's implementation of the Observer
 * pattern.
 * 
 * @author Simon Harrer, Joerg Lenhard
 * 
 */
public interface CountDownController extends Observer {

	/**
	 * This method triggers the execution of the count down with the according
	 * time in a {@code CountDownModel} object. It is called by a
	 * {@code CountDownView} when its button is clicked.
	 */
	public void startCountDown(int seconds);

}
