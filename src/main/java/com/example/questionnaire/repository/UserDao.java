package com.example.questionnaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	// In 是找List 才需要用
	public List<User> findAllByQnId(int qnId);

}
