package mvc.controller;

import java.util.Observable;

import mvc.model.CountDownModel;
import mvc.view.CountDownView;

//TODO: Implement the CountDownController
public class CountDownControllerImpl implements CountDownController {

	// TODO: create necessary instance variables
	private CountDownModel model;
	private CountDownView view;

	public CountDownControllerImpl(CountDownModel model) {
		/*
		 * TODO: Set up the connections of the different classes in the MVC.
		 * E.g. a view is needed and all observers need to be properly linked to
		 * the model
		 */
		
		view = new CountDownView(model, this);
		view.show();
		
		model.addObserver(this);
		this.model = model;
	}

	@Override
	public void startCountDown(int seconds) {
		/*
		 * TODO: Trigger the execution of the count down at the model. Prepare
		 * for Errors / Exceptions that might occur. Tell the view how to react
		 * properly in case something bad happens.
		 */
		if (!(seconds < 1)) {
			model.startCountDown(seconds);
			view.hideInputNodes();
		} else {
			view.showMessageToUser("Seconds has to be at least one!");
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		/*
		 * TODO: This method is inherited from java.util.Observer. If the
		 * CountDownControllerImpl has registered at the model it is notified by
		 * the model via this method. It might be reasonable to adjust the view,
		 * depending on the state of the model. E.g. hide, or show UI-elements.
		 */
		
		if (model.isExecuting()) {
			model.getSeconds();
		} else {
			view.showInputNodes();
		}
	}

}
