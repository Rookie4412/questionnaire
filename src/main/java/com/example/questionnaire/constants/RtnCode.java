package com.example.questionnaire.constants;

//列舉
public enum RtnCode {

	// 200 只 ok 的意思
	SUCCESSFUL(200, "successful"), //
	QUESTION_PARAM_ERROR(400, "Question Param error!!"),//
	QUESTIONNAIRE_PARAM_ERROR(400, "Questionnaire Param error!!"),//
	QUESTIONNAIRE_ID_PARAM_ERROR(400, "Questionnaire Id Param error!!"),//
	QUESTIONNAIRE_ID_NOT_FOUND(404, "Questionnaire Id Not Foud!!"),//
	UPDATE_ERROR(400,"Update error")
	;

	private int code;

	private String message;

	private RtnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	// 只用 get 而已
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
