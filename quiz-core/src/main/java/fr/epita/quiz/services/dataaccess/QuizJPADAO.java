package fr.epita.quiz.services.dataaccess;

import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Quiz;

@Repository
public class QuizJPADAO extends GenericDAO<Quiz> {
	
	@Override
	public void prepareSearch(Quiz criteria, QueryHolder<Quiz> holder) {
		holder.setQueryString("from Quiz as q where q.name like :name"); 
		holder.setClassName(Quiz.class);
		holder.putParameter("name", "%" +  criteria.getName() + "%");
	}
}
