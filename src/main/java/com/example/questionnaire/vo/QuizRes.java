package com.example.questionnaire.vo;

import java.util.List;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.User;

public class QuizRes {

	private List<User> userList;

	private List<QuizVo> quizVoList;

	private RtnCode rtnCode;

	public QuizRes() {
		super();
	}

	public QuizRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public QuizRes(RtnCode rtnCode, List<User> userList) {
		super();
		this.userList = userList;
		this.rtnCode = rtnCode;
	}

	public QuizRes(List<QuizVo> quizVoList, RtnCode rtnCode) {
		super();
		this.quizVoList = quizVoList;
		this.rtnCode = rtnCode;
	}

	public QuizRes(List<QuizVo> quizVoList) {
		super();
		this.quizVoList = quizVoList;
	}

	public List<QuizVo> getQuizVoList() {
		return quizVoList;
	}

	public void setQuizVoList(List<QuizVo> quizVoList) {
		this.quizVoList = quizVoList;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
