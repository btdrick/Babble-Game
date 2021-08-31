package edu.westga.cs.babble.views;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Defines the alert error box that
 * explains to the user they have
 * tried to submit an invalid word.
 * 
 * @author Brandon Drick
 * @version 08/27/2021
 */
public class BabbleInvalidWordErrorDialog {
	
	/**
	 * Displays an Alert for an invalid word
	 */
	public static void showErrorDialog() {
		Alert message = new Alert(AlertType.ERROR);
		message.setTitle("Babble: Invalid Word");
				
		message.setHeaderText("Error");
		message.setContentText("Invalid Word");
		message.showAndWait();
	}
}
