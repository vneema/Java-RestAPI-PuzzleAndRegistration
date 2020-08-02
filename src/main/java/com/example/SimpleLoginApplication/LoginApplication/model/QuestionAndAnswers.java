package com.example.SimpleLoginApplication.LoginApplication.model;

import java.util.List;

public class QuestionAndAnswers {

	public QuestionAndAnswers(String question, String answer) {
		
		this.question=question;
		this.answer=answer;
	}

	public QuestionAndAnswers() {
		// TODO Auto-generated constructor stub
	}

	private String question;
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
