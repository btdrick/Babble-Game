

package edu.westga.cs.babble.controllers;

import java.io.InputStreamReader;
import java.io.Reader;

import pt.tumba.spell.SpellChecker;

/**
 * Spell checker for words we attempt to make with Babble
 * @author lewisb
 *
 */
public class WordDictionary {

	private SpellChecker checker;
	
	/**
	 * Creates a new WordDictionary
	 */
	public WordDictionary() {
		this.checker = new SpellChecker();

			try {
				Reader reader = new InputStreamReader(WordDictionary.class.getResourceAsStream("/english.txt"));
				this.checker.initialize(reader);
			} catch (Exception e) {
				System.err.println("Wrong path to dictionary file");
				e.printStackTrace();
			}
	}
	
	/**
	 * Determines if a word is a real word or not.
	 * 
	 * @param word the word to check
	 * @return true if a valid word, false if gibberish
	 */
	public boolean isValidWord(String word) {
		if (word == null) {
			throw new IllegalArgumentException();
		}
		String check = this.checker.spellCheckWord(word);
		return check.contains("<plain>");
	}
}
