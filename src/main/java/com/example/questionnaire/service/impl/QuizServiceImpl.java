package com.example.questionnaire.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.repository.QuestionDao;
import com.example.questionnaire.repository.QuestionnaireDao;
import com.example.questionnaire.service.ifs.QuizService;
import com.example.questionnaire.vo.QuestionRes;
import com.example.questionnaire.vo.QuestionnaireRes;
import com.example.questionnaire.vo.QuizReq;
import com.example.questionnaire.vo.QuizRes;
import com.example.questionnaire.vo.QuizVo;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuestionnaireDao qnDao;

	@Autowired
	private QuestionDao quDao;

//	@Cacheable(cacheNames = "" , key ="#account")
//	@Transactional ���i�H�g�b�̭��u��b�o �A �@�άO�������i�H�����򳣤������
	@Transactional
	@Override
	public QuizRes create(QuizReq req) {

		QuizRes checkResult = checkParam(req);
		if (checkResult != null) {
			return checkResult;
		}
		int quId = qnDao.save(req.getQuestionnaire()).getId();

		List<Question> quList = req.getQuestionList();
		if (quList.isEmpty()) {
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		for (Question qu : quList) {
			qu.setQnId(quId);
		}
		quDao.saveAll(quList);
		return new QuizRes(RtnCode.SUCCESSFUL);

	}

	public QuizRes checkParam(QuizReq req) {
		Questionnaire qn = req.getQuestionnaire();
		if (!StringUtils.hasText(qn.getTitle()) || !StringUtils.hasText(qn.getDescription())
				|| qn.getStartDate() == null || qn.getEndDate() == null || qn.getStartDate().isAfter(qn.getEndDate())) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_PARAM_ERROR);
		}
		List<Question> quList = req.getQuestionList();
		for (Question qu : quList) {
			if (qu.getQuId() <= 0 || !StringUtils.hasText(qu.getqTitle()) || !StringUtils.hasText(qu.getOpTion())
					|| !StringUtils.hasText(qu.getOptionType())) {
				return new QuizRes(RtnCode.QUESTION_PARAM_ERROR);
			}
		}
		return null;
	}

	@Transactional
	@Override
	// �ק�
	public QuizRes update(QuizReq req) {
		QuizRes checkResult = checkParam(req);
		if (checkResult != null) {
			return checkResult;
		}
		checkResult = checkQuestionnaireId(req);
		if (checkResult != null) {
			return checkResult;
		}
		Optional<Questionnaire> qnOp = qnDao.findById(req.getQuestionnaire().getId());
		if (qnOp.isEmpty()) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_NOT_FOUND);
		}
		// collect deleted _question_id
		Questionnaire qn = qnOp.get();
//		�i�H�ק諸����
//		1. �|���o�� : is_published == false, �i�H�ק�
//		2. �w�o�����|���}�l : is_published == true + ��e�ɶ������p�� strat_data
		//qn.isPublished() == false  �g�k���� !qn.isPublished()
		if (qn.isPublished() == false || (qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate()))) {
			qnDao.save(req.getQuestionnaire());
			quDao.saveAll(req.getQuestionList());
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		return new QuizRes(RtnCode.UPDATE_ERROR);
	}

	private QuizRes checkQuestionnaireId(QuizReq req) {
		if (req.getQuestionnaire().getId() <= 0) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
		}
		List<Question> quList = req.getQuestionList();
		for (Question qu : quList) {
			// �p�G�ݨ��W��ID�������D��ID
			if (qu.getQnId() != req.getQuestionnaire().getId()) {
				return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
			}
		}
		return null;
	}
	
	@Transactional
	@Override
	public QuizRes deleteQustionnaire(List<Integer> qnIdList) {
		// ����ݨ�Id �A
		List<Questionnaire> qnList = qnDao.findByIdIn(qnIdList);
		// �p�G���h���ݨ��i�@���R���� ���ݨ�
		List<Integer> idList = new ArrayList<>();
		//
		for (Questionnaire qn : qnList) {
			if (!qn.isPublished() || qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate())) {
				idList.add(qn.getId());
			}
		}
		if (!idList.isEmpty()) {
			qnDao.deleteAllById(idList);
			quDao.deleteAllByQnIdIn(idList);// �R���ݨ������D��
		}
		return new QuizRes(RtnCode.SUCCESSFUL);
	}
	
	@Transactional
	@Override
	public QuizRes deleteQustion(int qnId, List<Integer> quIdList) {
		Optional<Questionnaire> qnOp = qnDao.findById(qnId);
		if (qnOp.isEmpty()) {
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		Questionnaire qn = qnOp.get();
		if (!qn.isPublished() || qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate())) {
//			quDao.deleteAllByQnIdIn(quIdList);
			quDao.deleteAllByQnIdAndQuIdIn(qnId, quIdList);
		}
		return new QuizRes(RtnCode.SUCCESSFUL);
	}

	@Cacheable(cacheNames = "search",
			// key = "test_2023-11-10"
			key = "#title.concat('_').concat(#startDate.toString()).concat('_').concat(#endDate.toString())",
			unless = "#result.rtnCode.code != 200")
	@Override
	// �ɶ�
	public QuizRes search(String title, LocalDate startDate, LocalDate endDate) {
		// �p�Gtitle�O�Ŧr�� ���F��^�_tile �S���N�Ŧr��
		title = StringUtils.hasText(title) ? title : "";
		startDate = startDate != null ? startDate : LocalDate.of(1971, 1, 1);
		endDate = endDate != null ? endDate : LocalDate.of(2099, 12, 31);
		List<Questionnaire> qnList = qnDao
				.findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(title, startDate, endDate);
		List<Integer> qnIds = new ArrayList<>();
		for (Questionnaire qu : qnList) {
			qnIds.add(qu.getId());
		}
		List<Question> quList = quDao.findAllByQnIdIn(qnIds);
		List<QuizVo> quizVoList = new ArrayList<>();
		// �Ĥ@��for�O�j�ݨ�
		for (Questionnaire qn : qnList) {
			QuizVo vo = new QuizVo();
			// vo �O�s���ҿ諸 qnList �̪��Ҧ����
			vo.setQuestionnaire(qn);
			// �o��List�p�G��bfor�^�餺�|�]�h���ҥH��b�~��
			List<Question> questionList = new ArrayList<>();
			// �ĤG��for�O 2�j�ݨ������p���D
			for (Question qu : quList) {
				// Id�t��
				if (qu.getQnId() == qn.getId()) {
					questionList.add(qu);
				}
			}
			// vo ��^�h quizVoList
			vo.setQuestionList(questionList);
			quizVoList.add(vo);
		}
		return new QuizRes(quizVoList, RtnCode.SUCCESSFUL);
	}

	// �~�h���D�C��
	@Override
	public QuestionnaireRes searchQuestionnaireList(String title, LocalDate startDate, LocalDate endDate,
			boolean isAll) {
//		title = StringUtils.hasText(title) ? title : "";
//		startDate = startDate != null ? startDate : LocalDate.of(1971, 1, 1);
//		endDate = endDate != null ? endDate : LocalDate.of(2099, 12, 31);
		List<Questionnaire> qnList = new ArrayList<>();
		if (!isAll) {
			// �w�o���ɵ��e��
			qnList = qnDao.findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndPublishedTrue(
					title, startDate, endDate);
			return new QuestionnaireRes(qnList, RtnCode.SUCCESSFUL);
		} else {
			qnList = qnDao.findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(title, startDate,
					endDate);
			return new QuestionnaireRes(qnList, RtnCode.SUCCESSFUL);
		}
	}

	// ���h �\�h�p���D
	@Override
	public QuestionRes searchQuestionList(int qnId) {
		if (qnId <= 0) {
			return new QuestionRes(null, RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
		}
		List<Question> quList = quDao.findAllByQnIdIn(Arrays.asList(qnId));
		return new QuestionRes(quList, RtnCode.SUCCESSFUL);
	}

//	@Override
//	public QuizRes searchFuzzy(String title, LocalDate startDate, LocalDate endDate) {
//		
//		return new QuestionRes(quList, RtnCode.SUCCESSFUL);
//	}

}
