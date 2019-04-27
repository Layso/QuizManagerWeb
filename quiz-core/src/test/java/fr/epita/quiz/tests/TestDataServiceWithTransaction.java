package fr.epita.quiz.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.dataaccess.ChoiceJPADAO;
import fr.epita.quiz.services.dataaccess.QuizDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestDataServiceWithTransaction {
	
	@Inject
	QuizDataService quizDS;
	
	@Inject
	ChoiceJPADAO choiceDAO;
	
	@Test
	public void testQuestionWithChoicesInsertion() {
		/*
		//given
		Question question = new Question();
		question.setContent("What is maven ?");
		Choice choice1 = new Choice();
		String firstChoiceText = "it is a dependency injection framework";
		choice1.setText(firstChoiceText);
		choice1.setValid(false);
		Choice choice2 = new Choice();
		choice2.setText("it is including a dependency management tool");
		choice2.setValid(true);
		
		//when
		quizDS.createQuestionAndChoices(question, choice1, choice2);
		
		//then
		Choice criteria = new Choice();
		criteria.setQuestion(question);
		List<Choice> searchResults = choiceDAO.search(criteria);
		Assert.assertTrue(!searchResults.isEmpty());
		
		
		
		List<Choice> subList = searchResults.stream()
				.filter(c -> c.getText().equals(firstChoiceText))
				.collect(Collectors.toList());
		
		Assert.assertEquals(1, subList.size());
		*/
		
		/*
		User user = new User();
		user.setNick("layso");
		
		int questionCount = 3;
		List<Question> qList = new ArrayList<Question>();
		List<Choice> cList = new ArrayList<Choice>();
		
		
		for (int i=0; i<questionCount; ++i) {
			Question newQuestion = new Question();
			newQuestion.setContent("Question " + i + "for second test");
			qList.add(newQuestion);
			
			for (int j=0; j<Question.CHOICE_COUNT; ++j) {
				Choice newChoice = new Choice();
				newChoice.setText("Choice for question " + i);
				newChoice.setValid(j == questionCount%4);
				cList.add(newChoice);
			}
		}
		
		
		quizDS.CreateQuiz(user, "Bu da ikinci", qList, cList);*/
		
		/*
		Quiz squiz = new Quiz();
		//squiz.setName("e");
		List<Quiz> quizs = quizDS.GetAllQuizLike(squiz);
		System.out.println(quizs.size());
		for (Quiz quiz : quizs) {
			System.out.println(quiz.getName());
		}
		*/
		
		/*
		User user = new User();
		user.setId(1);
		user.setNick("layso");
		
		Quiz quiz = new Quiz();
		quiz.setId(3);
		quiz.setName("Bu da ikinci");
		quiz.setUser(user);
		
		List<Question> questions = quizDS.GetAllQuestionsOfQuiz(quiz);
		for (Question q : questions) {
			System.out.println(q.getContent());
			
			for (Choice choice : quizDS.GetAllChoicesOfQuestion(q)) {
				System.out.println(choice.getText() + " / " + choice.getValid());
			}
		}
		*/
		
		Quiz criteria = new Quiz();
		criteria.setName("");
		for (Quiz q: quizDS.GetAllQuizLike(criteria)) {
			System.out.println(q.getName());
		}
		
		
		
		
	}

	
	
}
