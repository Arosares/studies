package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.view;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.controller.IssueTrackerController;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;

public class IssueTrackerMenu {

	private IssueTrackerController controller;
	File currentDir = new File(System.getProperty("user.dir"));

	public IssueTrackerMenu(IssueTrackerController controller) {
		this.controller = controller;
	}

	private MenuBar createMenuBar() {
		// Generates our only Menu, Data:
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(currentDir);

		final Menu menuData = new Menu("Data");
		MenuItem menuNew = new MenuItem("New");
		MenuItem menuLoad = new MenuItem("Load");
		MenuItem menuSave = new MenuItem("Save As");
		MenuItem menuExit = new MenuItem("Exit");

		menuData.getItems().addAll(menuNew);
		menuData.getItems().addAll(menuLoad);
		menuData.getItems().addAll(menuSave);
		menuData.getItems().addAll(menuExit);

		menuNew.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				controller.newProject();
			}
		});

		menuLoad.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fileChooser.setTitle("Load Project");
				fileChooser.getExtensionFilters().add(
						new ExtensionFilter("Xml Files", "*.xml"));
				File file = fileChooser.showOpenDialog(null);
				if (file == null) {
					event.consume();
				} else {
					String loadDestination = file.getPath().toString();
					controller.loadProject(loadDestination);
				}
				;
			}
		});

		menuSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fileChooser.setTitle("Save Project");
				fileChooser.getExtensionFilters().add(
						new ExtensionFilter("Xml Files", "*.xml"));
				File file = fileChooser.showSaveDialog(null);
				if (file != null) {
					try {
						String saveDestination = file.getPath().toString();
						controller.saveProject(saveDestination);
					} catch (IssueTrackingException e) {
						e.printStackTrace();
					}
				} else {
					event.consume();
				}
			}
		});

		menuExit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
		});
		// Bar containing the menu:
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuData);
		menuBar.setStyle("-fx-background-color: #336699;"); 

		return menuBar;
	}

	MenuBar getTopMenuBar() {
		return createMenuBar();
	}
}
