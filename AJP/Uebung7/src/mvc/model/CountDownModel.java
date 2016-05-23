package mvc.model;

import java.util.Observable;
import java.util.concurrent.Semaphore;

/**
 * Implements a simple second-based count down. Can either be in state
 * "executing", when counting down, or "idle", when doing nothing. Can be
 * observed for count down events in a pull-fashion. I.e., {@code Observers} are
 * only notified that a change to the internal state has taken place. They can
 * invoke {@code getSeconds} or {@code isExecuting} to gather information about
 * this change.
 * 
 * @author Simon Harrer, Joerg Lenhard, Michael Traeger, Gabriel Nikol
 * 
 */
public class CountDownModel extends Observable {

	private volatile int seconds;

	private Semaphore semaphore;

	/**
	 * Creates a new {@code CountDown}.
	 */
	public CountDownModel() {
		seconds = 0;
		semaphore = new Semaphore(1);
	}

	/**
	 * Returns the number of seconds for which the {@code CountDownModel} is
	 * still executing. If the {@code CountDown} is idle, this value is 0.
	 * 
	 * @return the amount of seconds for which the {@code CountDown} is still
	 *         executing, or 0 if the {@code CountDown} is idle.
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * Returns the current state of this {@code CountDown} which can be
	 * "executing" (true) or "idle" (false).
	 * 
	 * @return true if this {@code CountDown} is currently executing, false
	 *         otherwise
	 */
	public boolean isExecuting() {
		return seconds != 0;
	}

	/**
	 * Triggers the execution of the count down for the given amount of seconds
	 * and returns immediately. The count down will execute for the amount of
	 * seconds specified and only stop when this value has reached zero. Each
	 * second, the current amount of seconds for this {@code CountDownModel} is
	 * decremented and the {@code Observers} of this {@code CountDownModel} are
	 * notified of this change. The notification also takes place on starting
	 * and ending the execution.
	 * 
	 * @param timeInSeconds
	 *            The amount of seconds for which the {@code CountDownModel}
	 *            should execute
	 * @throws IllegalArgumentException
	 *             If {@code timeInSeconds} is not positive
	 * @throws IllegalStateException
	 *             If this {@code CountDownModel} is already executing at the
	 *             moment
	 */
	public void startCountDown(int timeInSeconds)
			throws IllegalArgumentException, IllegalStateException {
		// check seconds
		if (!isSecondsValid(timeInSeconds)) {
			throw new IllegalArgumentException(
					"Amount of seconds must be positive");
		}

		// check state
		if (isExecuting()) {
			throw new IllegalStateException("Count down is already executing");
		}

		semaphore.acquireUninterruptibly();
		this.seconds = timeInSeconds;

		new Thread() {

			@Override
			public void run() {
				// notify the observers of the start of the execution
				triggerNotification();

				while (seconds > 0) {

					try {
						Thread.sleep(1000);
					} catch (InterruptedException ignored) {
						// empty by intent
					}

					// notify the observers of the change of seconds
					seconds--;
					triggerNotification();
				}

				// notify the observers of the end of the computation
				triggerNotification();
				semaphore.release();
			}

			public void triggerNotification() {
				setChanged();
				notifyObservers();
			}

		}.start();

	}

	private boolean isSecondsValid(int seconds) {
		return seconds > 0;
	}

}
