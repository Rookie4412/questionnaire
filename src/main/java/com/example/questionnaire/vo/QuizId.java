package com.example.questionnaire.vo;

import java.util.List;

public class QuizId {

	private List<Integer> qnList;

	private List<Integer> quList;

	private int qnId;

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
