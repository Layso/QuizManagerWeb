package com.layso.quiz.datamodel;

import java.util.List;

import javax.persistence.Entity;


@Entity
public class MultipleChoiceQuestion extends Question {
	private List<String> choices;
	private String correctChoice;
	
	
	/**
	 * Constructor to initialize the Question object
	 * @param label			Question text that's going to be shown to user
	 * @param topics		Topics of the question
	 * @param difficulty	Difficulty of the question
	 * @param type			Question type
	 * @param choices		Choice list for question
	 * @param correctChoice	Correct choice of the question
	 */
	public MultipleChoiceQuestion(String label, List<String> topics, int difficulty, List<String> choices, String correctChoice) {
		super(label, topics, difficulty);
		this.choices = choices;
		this.correctChoice = correctChoice;
	}
	
	
	/**
	 * Getter for the choices
	 * @return Choices of the question as string list
	 */
	public List<String> GetChoices() {
		return choices;
	}
	
	/**
	 * Getter for the correct choice
	 * @return Correct choice of the question
	 */
	public String GetCorrectChoice() {
		return correctChoice;
	}
}
