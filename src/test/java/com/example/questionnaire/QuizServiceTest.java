package com.example.questionnaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.repository.QuestionDao;
import com.example.questionnaire.repository.QuestionnaireDao;
import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QuestionRes;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;

@SpringBootTest
public class QuizServiceTest {

	@Autowired
	private QuizService service;

	@Autowired
	private QuestionDao quDao;

	@Autowired
	private QuestionnaireDao questionnaireDao;

	@Test
	public void createTest() {

		Questionnaire qustionnaire = new Questionnaire("test1", "test", false, LocalDate.of(2023, 11, 17),
				LocalDate.of(2023, 11, 30));
		List<Question> questionList = new ArrayList<>();
		Question q1 = new Question(1, "zzz", "gg", false, "AAA;BBB;CCC");
		Question q2 = new Question(2, "aaa", "mm", false, "10;20;30;40");
		Question q3 = new Question(3, "qwe", "text", false, "ABC");
		questionList.addAll(Arrays.asList(q1, q2, q3));

		QuizReq req = new QuizReq(qustionnaire, questionList);
		QuizRes res = service.create(req);
		Assert.isTrue(res.getRtnCode().getCode() == 200, "create error");
	}

	@Test
	public void deleteQustionnaireTest() {
		List<Integer> qnidList = Arrays.asList(51);
		QuizRes res = service.deleteQustionnaire(qnidList);
		System.out.println(res.getRtnCode().getCode());
		System.out.println(res.getRtnCode().getMessage());
		Assert.isTrue(res.getRtnCode().getCode() == 200, "create error");
	}

	@Test
	public void deleteQustionTest() {
		List<Integer> qnidList = new ArrayList<>(Arrays.asList(1, 2));
		QuizRes res = service.deleteQustion(15, qnidList);
		System.out.println(res.getRtnCode().getCode());
		System.out.println(res.getRtnCode().getMessage());
		Assert.isTrue(res.getRtnCode().getCode() == 200, "create error");
	}

	@Test
	public void searchQuestionListTest() {
		QuestionRes res = service.searchQuestionList(20);
		List<Question> QuestionList = res.getQuestionList();
		for (Question item : QuestionList) {
			System.out.println(item.getqTitle());
		}
		Assert.isTrue(res.getRtnCode().getCode() == 200, "create error");
	}

	@Test
	public void questionnaireDao() {
		List<Questionnaire> res = questionnaireDao.findAll();
		res.get(0);
		System.out.println(res.get(0).getId());
	}

}
