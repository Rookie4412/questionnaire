package com.example.questionnaire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "question")
@IdClass(value = QuestionId.class)
public class Question {

	@Id
	@Column(name = "id")
	private int quId;

	@Id
	@Column(name = "qn_id")
	private int qnId;

	@Column(name = "q_title")
	private String qTitle;

	@Column(name = "option_type")
	private String OptionType;

	@Column(name = "is_necessary")
	private boolean necessary;

	@Column(name = "q_option")
	private String OpTion;

	public Question() {
		super();
	}

	public Question(int quId, String qTitle, String optionType, boolean necessary, String opTion) {
		super();
		this.quId = quId;
		this.qTitle = qTitle;
		OptionType = optionType;
		this.necessary = necessary;
		OpTion = opTion;
	}

	public Question(int quId, int qnId, String qTitle, String optionType, boolean necessary, String opTion) {
		super();
		this.quId = quId;
		this.qnId = qnId;
		this.qTitle = qTitle;
		OptionType = optionType;
		this.necessary = necessary;
		OpTion = opTion;
	}

	public int getQuId() {
		return quId;
	}

	public void setQuId(int quId) {
		this.quId = quId;
	}

	public int getQnId() {
		return qnId;
	}

	public void setQnId(int qnId) {
		this.qnId = qnId;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getOptionType() {
		return OptionType;
	}

	public void setOptionType(String optionType) {
		OptionType = optionType;
	}

	public boolean isNecessary() {
		return necessary;
	}

	public void setNecessary(boolean necessary) {
		this.necessary = necessary;
	}

	public String getOpTion() {
		return OpTion;
	}

	public void setOpTion(String opTion) {
		OpTion = opTion;
	}

}
