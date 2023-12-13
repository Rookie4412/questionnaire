package com.example.questionnaire.vo;

import java.util.List;

public class QuizId {

	private List<Integer> qnList;

	private List<Integer> quList;

	private int qnId;

	public QuizId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizId(List<Integer> qnList) {
		super();
		this.qnList = qnList;
	}

	public QuizId(List<Integer> quList, int qnId) {
		super();
		this.quList = quList;
		this.qnId = qnId;
	}

	public QuizId(List<Integer> qnList, List<Integer> quList, int qnId) {
		super();
		this.qnList = qnList;
		this.quList = quList;
		this.qnId = qnId;
	}

	public List<Integer> getQnList() {
		return qnList;
	}

	public void setQnList(List<Integer> qnList) {
		this.qnList = qnList;
	}

	public List<Integer> getQuList() {
		return quList;
	}

	public void setQuList(List<Integer> quList) {
		this.quList = quList;
	}

	public int getQnId() {
		return qnId;
	}

	public void setQnId(int qnId) {
		this.qnId = qnId;
	}

}
