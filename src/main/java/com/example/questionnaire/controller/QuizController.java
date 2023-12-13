package com.example.questionnaire.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.User;
import com.example.questionnaire.repository.QuestionnaireDao;
import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QuestionRes;
import com.example.questionnaire.vo.QuestionnaireRes;
import com.example.questionnaire.vo.QuizId;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;
import com.example.questionnaire.vo.QuizSearchReq;
import com.example.questionnaire.vo.QuizVo;
import com.example.questionnaire.vo.UserRes;

@RestController
@CrossOrigin
public class QuizController {

	@Autowired
	private QuizService service;

	@Autowired
	private QuestionnaireDao quDao;

	// ��J��Ʈw
	@PostMapping(value = "api/quiz/create")
	public QuizRes create(@RequestBody QuizReq req) {
		return service.create(req);
	}

	// �ק���
	@PostMapping(value = "api/quiz/update")
	public QuizRes update(@RequestBody QuizReq req) {
		return service.update(req);
	}

	// �R���ݨ�
	@PostMapping(value = "api/quiz/deleteQustionnaire")
	public QuizRes deleteQustionnaire(@RequestBody QuizId quizid) {
		return service.deleteQustionnaire(quizid.getQnList());
	}

	// �R���ݨ��̪����D
	@PostMapping(value = "api/quiz/deleteQustion")
	public QuizRes deleteQustion(@RequestBody QuizId quizid) {
		return service.deleteQustion(quizid.getQnId(), quizid.getQuList());
	}

	// �j�M��Ʈw�����
	@GetMapping(value = "api/quiz/search")
	public QuizRes search(@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam(value = "isPublished", required = false) boolean isPublished) {
		title = StringUtils.hasText(title) ? title : "";
		startDate = startDate != null ? startDate : LocalDate.of(1971, 01, 01);
		endDate = endDate != null ? endDate : LocalDate.of(2099, 01, 01);

		return service.search(title, startDate, endDate);
	}

	// �j�M��Ʈw�̰ݨ����
	@GetMapping(value = "api/quiz/searchQuestionnaireList")
	public QuestionnaireRes searchQuestionnaireList(@RequestBody QuizSearchReq req) {
		return service.searchQuestionnaireList(req.getTitle(), req.getStartDate(), req.getEndDate(), req.isPublished());
	}

	// �j�M�ݨ��z���D���
	@GetMapping(value = "api/quiz/searchQuestionList")
	public QuestionRes searchQuestionList(@RequestParam int qnId) {
		return service.searchQuestionList(qnId);
	}

	@PostMapping(value = "api/quiz/setUser")
	public QuizRes setUser(@RequestBody UserRes userList) {
		return service.setUser(userList.getUserList());
	}

	@GetMapping(value = "api/quiz/getUser")
	public UserRes getUser(@RequestParam int qnId) {
		return service.getUser(qnId);
	}
}
