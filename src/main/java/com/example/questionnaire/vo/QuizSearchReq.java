package com.example.questionnaire.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizSearchReq {

	@JsonProperty("title")
	private String title;

	@JsonProperty("start_Date")
	private LocalDate startDate;

	@JsonProperty("end_Date")
	private LocalDate endDate;

	@JsonProperty("is_Published")
	private boolean Published;

	public QuizSearchReq() {
		super();
	}

	public QuizSearchReq(String title, LocalDate startDate, LocalDate endDate, boolean published) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		Published = published;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isPublished() {
		return Published;
	}

	public void setPublished(boolean published) {
		Published = published;
	}

}
