package com.example.questionnaire.entity;

public class Questiontotal {

	private int id;

	private String title;

	public Questiontotal() {
		super();
	}

	public Questiontotal(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
