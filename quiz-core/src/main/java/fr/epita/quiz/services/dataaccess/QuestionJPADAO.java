package fr.epita.quiz.services.dataaccess;

import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Question;

@Repository
public class QuestionJPADAO extends GenericDAO<Question>{
	
	@Override
	public void prepareSearch(Question criteria, QueryHolder<Question> holder) {
		holder.setQueryString("from Question as q where q.quiz = :quiz"); 
		holder.setClassName(Question.class);
		holder.putParameter("quiz", criteria.getQuiz());
	}
}