package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.views.BabbleInvalidWordErrorDialog;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

/**
 * This class controls the operations
 * of the GUI.
 * 
 * @author Brandon Drick
 * @version 08/26/2021
 */
public class BabbleController implements Initializable {
	@FXML private ListView<Tile> tileList;
	@FXML private ListView<Tile> wordList;
	@FXML private Button resetWord;
	@FXML private Button playWord;
	@FXML private TextField scoreField;
	
	private TileBag tileBag;
	private TileRack tileRack;
	private WordDictionary spellChecker;
	private int currentScore;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.spellChecker = new WordDictionary();
		this.tileBag = new TileBag();
		this.tileRack = new TileRack();
		this.currentScore = 0;
		this.configureGUI();
	}
	
	/**
	 * Configures the nodes within the Scene
	 * to perform their actions.
	 */
	private void configureGUI() {
		this.initializeListViews();
		this.initializeButtons();
		this.updateCurrentScoreField(0);
	}
	
	/**
	 * Initializes the ListViews
	 * containing the contents of 
	 * TileRack and PlayedWord.
	 */
	private void initializeListViews() {
		this.initializeTileRackListView();
		this.initializeWordListView();
	}
	
	/**
	 * Initializes the ListView displaying 
	 * the contents of the Tile Rack
	 */
	private void initializeTileRackListView() {
		this.fillTileRackListView();
		this.setTileCellFactoryForListView(this.tileList);

		this.tileList.setOnMouseClicked(event -> {
			Tile selectedTile = this.tileList.getSelectionModel().getSelectedItem();
			this.moveTileFromTileRackToWordList(selectedTile);
		});
	}
	
	/**
	 * Adds Tiles to TileRack until full
	 * or bag is empty.
	 */
	private void fillTileRackListView() {
		while (this.tileRack.getNumberOfTilesNeeded() > 0) {
			try {
				Tile drawnTile = this.tileBag.drawTile();
				this.tileRack.append(drawnTile);
				this.tileList.getItems().addAll(drawnTile);
			} catch (EmptyTileBagException etbe) {
				etbe.printStackTrace();
			}
		}
	}
	
	/**
	 * Removes a Tile from the TileRack ListView
	 * and adds it to the current word ListView
	 * @param selectedTile the Tile from the ListView
	 */
	private void moveTileFromTileRackToWordList(Tile selectedTile) {
		try {
			this.wordList.getItems().add(selectedTile);
			this.tileList.getItems().remove(selectedTile);
			this.tileRack.remove(selectedTile);
		} catch (TileNotInGroupException tnige) {
			tnige.printStackTrace();
		}
	}
	
	/**
	 * Initializes the ListView displaying
	 * the contents of the current word
	 */
	private void initializeWordListView() {
		this.setTileCellFactoryForListView(this.wordList);
		
		this.wordList.setOnMouseClicked(event -> {
			Tile selectedTile = this.wordList.getSelectionModel().getSelectedItem();
			this.moveTileFromWordListToTileRack(selectedTile);
		});
	}
	
	/**
	 * Moves a Tile from the current word ListView
	 * to the TileRack ListView
	 * @param selectedTile the Tile from the word ListView
	 */
	public void moveTileFromWordListToTileRack(Tile selectedTile) {
		this.tileList.getItems().add(selectedTile);
		this.wordList.getItems().remove(selectedTile);
		this.tileRack.append(selectedTile);
	}
	
	/**
	 * Sets the cell factory for a ListView
	 * to display a Tile object
	 * @param theListView displaying Tile objects
	 */
	private void setTileCellFactoryForListView(ListView<Tile> theListView) {
		theListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> tiles) {
				return new TileCell();
			}
		});
	}
	
	/**
	 * Configures a Tile to be displayed
	 * as a String for the ListView.
	 * 
	 * @author Brandon Drick
	 * @version 08/27/2021
	 */
	private static class TileCell extends ListCell<Tile> {
		@Override
		public void updateItem(Tile tile, boolean empty) {
			super.updateItem(tile, empty);
			
			if (empty || tile == null) {
				setText(null);
				setGraphic(null);
			} else {
				char tileContents = tile.getLetter();
				String letter = Character.toString(tileContents);
				super.setText(letter);
			}
		}
	}
	
	/**
	 * Initializes the reset button
	 * and the play word button.
	 */
	private void initializeButtons() {
		this.initializeResetWordButton();
		this.initializePlayWordButton();
	}
	
	/**
	 * Configures the reset word button.
	 */
	private void initializeResetWordButton() {
		this.resetWord.setOnAction(new ResetWordListener());
	}
	
	/**
	 * Event handler for Reset Word button
	 * @author Brandon Drick
	 * @version 08/27/2021
	 */
	private class ResetWordListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ObservableList<Tile> currentHand = BabbleController.this.wordList.getItems();
			for (Tile current : currentHand) {
				BabbleController.this.tileRack.append(current);
			}
			BabbleController.this.tileList.getItems().addAll(currentHand);
			BabbleController.this.wordList.getItems().clear();
		}	
	}
	
	/**
	 * Configures the play word button.
	 */
	private void initializePlayWordButton() {
		this.playWord.setOnAction(new PlayWordListener());
	}
	
	/**
	 * Event handler for Play Word button.
	 * @author Brandon Drick
	 * @version 08/27/2021
	 */
	private class PlayWordListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			PlayedWord playedWord = BabbleController.this.getCurrentPlayedWord();
			String wordToPlay = playedWord.getHand();
			
			if (BabbleController.this.spellChecker.isValidWord(wordToPlay)) {
				int numericScore = playedWord.getScore();
				BabbleController.this.updateCurrentScoreField(numericScore);
				BabbleController.this.fillTileRackListView();
				BabbleController.this.wordList.getItems().clear();
			} else {
				BabbleInvalidWordErrorDialog.showErrorDialog();
			}
		}
	}
	
	/**
	 * Returns a PlayedWord object
	 * for the current word
	 * @return playedWord object
	 */
	private PlayedWord getCurrentPlayedWord() {
		ObservableList<Tile> currentHand = this.wordList.getItems();
		PlayedWord playedWord = new PlayedWord();
		
		for (Tile current : currentHand) {
			playedWord.append(current);
		}
		
		return playedWord;
	}
	
	/**
	 * Binds the score TextField
	 * to the current score
	 * @param scoreToAdd points to add
	 */
	private void updateCurrentScoreField(int scoreToAdd) {
		this.currentScore += scoreToAdd;
		IntegerProperty integerProperty = new SimpleIntegerProperty(this.currentScore);
		
		this.scoreField.textProperty()
			.bindBidirectional(integerProperty, new NumberStringConverter());
	}
}
