package com.example.questionnaire.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Questionnaire;

@Repository
public interface QuestionnaireDao extends JpaRepository<Questionnaire, Integer> {

	public List<Questionnaire> findByIdIn(List<Integer> idList);

	public List<Questionnaire> findByIdInAndPublishedFalse(List<Integer> idList);

	public List<Questionnaire> findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String title,
			LocalDate startDate, LocalDate endDate);

	public List<Questionnaire> findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndPublishedTrue(
			String title, LocalDate startDate, LocalDate endDate);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO questionnaire(title,description,is_published,start_date,end_date)"
			+ "values(:title,:desp,:ispublished,:startDate,:endDate)", nativeQuery = true)

	public int insert(@Param("title") String title, //
			@Param("desp") String descrption, //
			@Param("ispublished") boolean ispublished, //
			@Param("startDate") LocalDate startDate, //
			@Param("endDate") LocalDate endDate);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO questionnaire(title,description,is_published,start_date,end_date)"
			+ "values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)

	public int insertDate(String title, String descrption, boolean ispublished, LocalDate startDate, LocalDate endDate);

	@Modifying
	@Transactional
	@Query(value = "UPDATE questionnaire set title = :title,description = :desp" +"where id = :id", nativeQuery = true)

	public int update(@Param("id") int id, //
			@Param("title") String title, //
			@Param("desp") String descrption);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE Questionnaire set title = :title,description = :desp,startDate = :startDate"
			+" where id = :id")
	public int updateData(
			@Param("id")int id, //
			@Param("title")String title, //
			@Param("desp")String descrption,//
			@Param("startDate")LocalDate startDate); 

	@Query(value = "SELECT * from questionnare" + " where start_date > :startDate", nativeQuery = true)
	public List<Questionnaire> findByStartDate(@Param("startDate") LocalDate StartDate);
	
//	@Query(value = "SELECT new Questionnare(id,title,description,published,startDate,endDate)" 
//	+"from Questionnare where startDate > :startDate")
//	public List<Questionnaire> findByStartDate1(@Param("startDate") LocalDate StartDate);
	
	
}
