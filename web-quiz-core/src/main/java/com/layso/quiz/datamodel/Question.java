package com.layso.quiz.datamodel;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public abstract class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String label;
	private List<String> topics;
	private int difficulty;
	
	
	/**
	 * Constructor to initialize the Question object
	 * @param label			Question text that's going to be shown to user
	 * @param topics		Topics of the question
	 * @param difficulty	Difficulty of the question
	 * @param type			Question type
	 */
	protected Question(String label, List<String> topics, int difficulty) {
		this.label = label;
		this.topics = topics;
		this.difficulty = difficulty;
	}
	
	
	/**
	 * Getter for question id
	 * @return ID of the question as int
	 */
	public int GetID() {
		return id;
	}
	
	/**
	 * Getter for question label
	 * @return Question label as string
	 */
	public String GetLabel() {
		return label;
	}
	
	/**
	 * Getter for question topics
	 * @return Topics of the question as string list
	 */
	public List<String> GetTopics() {
		return topics;
	}
	
	/**
	 * Getter for question difficulty
	 * @return Difficulty of the question as integer
	 */
	public int GetDifficulty() {
		return difficulty;
	}
}
