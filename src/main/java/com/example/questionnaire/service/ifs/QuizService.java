package com.example.questionnaire.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.questionnaire.vo.QuestionRes;
import com.example.questionnaire.vo.QuestionnaireRes;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;

public interface QuizService {

	public QuizRes create(QuizReq req);

	public QuizRes update(QuizReq req);

	public QuizRes deleteQustionnaire(List<Integer> qnIdList);

	public QuizRes deleteQustion(int qnId, List<Integer> quIdList);
	
	public QuizRes search(String title, LocalDate startDate, LocalDate endDate);
	
//	public QuizRes searchFuzzy(String title, LocalDate startDate, LocalDate endDate);
	
	public QuestionnaireRes searchQuestionnaireList(String title, LocalDate startDate, LocalDate endDate , boolean isPublished);
	
	public QuestionRes searchQuestionList(int qnId);
}
