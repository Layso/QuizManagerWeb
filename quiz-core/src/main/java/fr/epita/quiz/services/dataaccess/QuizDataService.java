package fr.epita.quiz.services.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.datamodel.User;


@Repository
public class QuizDataService {
	
	private static final Logger LOGGER = LogManager.getLogger(QuizDataService.class); 
	
	@Inject
	QuestionJPADAO questionDAO;
	
	@Inject
	ChoiceJPADAO choiceDAO;
	
	@Inject
	QuizJPADAO quizDAO;
	
	@Inject
	UserJPADAO userDAO;
	
	@PersistenceContext
	EntityManager em;
	
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void createQuestionAndChoices(Question question, Choice... choices) {
		
		LOGGER.info("entering the createQuestionAndChoices method");
		questionDAO.create(question);
		for (Choice choice : choices) {
			choice.setQuestion(question);
			choiceDAO.create(choice);
		}
		
	
		
		
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void CreateQuiz(User user, String title, List<Question> questions, List<Choice> choices) {
		// Create user if not exists
		
		
		List<User> userList = userDAO.search(user);
		if (userList.isEmpty()) {
			userDAO.create(user);
			LOGGER.info("New user created with nick: " + user.getNick());
		} else {
			user.setId(userList.get(0).getId());
			LOGGER.info("User found with nick: " + user.getNick());
		}
		
		
		// Create quiz associated with user
		Quiz quiz = new Quiz();
		quiz.setUser(user);
		quiz.setName(title);
		quizDAO.create(quiz);
		LOGGER.info("New quiz created with associated to user: " + user.getNick());
		
		
		// Create questions associated with quiz and 
		for (int i=0; i<questions.size(); ++i) {
			Question newQuestion = new Question();
			
			newQuestion.setContent(questions.get(i).getContent());
			newQuestion.setQuiz(quiz);
			questionDAO.create(newQuestion);
			LOGGER.info("Question " + i + " created, associated with quiz of: " + user.getNick());
			
			for (int j=0; j<Question.CHOICE_COUNT; ++j) {
				Choice newChoice = new Choice();
				
				newChoice.setQuestion(newQuestion);
				newChoice.setText(choices.get(i*Question.CHOICE_COUNT + j).getText());
				newChoice.setValid(choices.get(i*Question.CHOICE_COUNT + j).getValid());
				choiceDAO.create(newChoice);
				LOGGER.info("Choice " + j + " created associated to question " + i);
			}
		}
		LOGGER.info("Quiz creation completed");
	}

	
	@Transactional(Transactional.TxType.REQUIRED)
	public List<Quiz> GetAllQuizLike(Quiz criteria) {
		List<Quiz> list = quizDAO.search(criteria);
		
		LOGGER.info("Quiz search completed");
		
		return list;
	}
	
	
	@Transactional(Transactional.TxType.REQUIRED)
	public List<Question> GetAllQuestionsOfQuiz(Quiz quiz) {
		Question criteria = new Question();
		criteria.setQuiz(quiz);
		
		List<Question> list = questionDAO.search(criteria);
		LOGGER.info("Questions search completed");
		
		return list;
	}
	
	
	@Transactional(Transactional.TxType.REQUIRED)
	public List<Choice> GetAllChoicesOfQuestion(Question question) {
		Choice criteria = new Choice();
		criteria.setQuestion(question);
		
		List<Choice> list = choiceDAO.search(criteria);
		LOGGER.info("Choices search completed");
		
		return list;
	}
	
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void RemoveQuiz(Quiz quiz) {
		List<Question> questions = GetAllQuestionsOfQuiz(quiz);

		
		for (Question question : questions) {
			List<Choice> list = GetAllChoicesOfQuestion(question);
			for (Choice choice : list) {
				choiceDAO.delete(choice);				
			}
			
			questionDAO.delete(question);
		}
		
		quizDAO.delete(quiz);
	}
}
