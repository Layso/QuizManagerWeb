package fr.epita.quiz.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.dataaccess.QuestionJPADAO;
import fr.epita.quiz.services.dataaccess.QuizDataService;

@Path("/quiz")
public class QuestionResource {
	private static List<Question> questions;
	private static List<Choice> choices;
	
	
	@Inject
	QuizDataService quizDS;
	
	
	@GET
	@Path("/search")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findAllQuiz(@QueryParam("name") String searchParam) {
		Quiz criteria = new Quiz();
		
		if (searchParam != null) {
			criteria.setName(searchParam);
		}
		
		return Response.ok(quizDS.GetAllQuizLike(criteria)).build();
	}
	
	
	@GET
	@Path("/getq")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findAllQuestions(@QueryParam("name") String searchParam) {
		Quiz criteria = new Quiz();
		List<Quiz> quizList;
		List<Question> questionList = new ArrayList<Question>();
		
		
		if (searchParam != null) {
			criteria.setName(searchParam);
			quizList = quizDS.GetAllQuizLike(criteria);
			questionList = quizList.size() > 0 ? quizDS.GetAllQuestionsOfQuiz(quizList.get(0)) : questionList;
		}
		
		return Response.ok(questionList).build();
	}

	
	@POST
	@Path("/getc")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response findAllChoices(Question question) throws URISyntaxException {
		return Response.ok(quizDS.GetAllChoicesOfQuestion(question)).build();
	}
	
	
	@POST
	@Path("/saveq")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response saveQuestions(Question[] newQuestions) throws URISyntaxException {
		questions = new ArrayList<Question>();
		for (Question question : newQuestions) {
			questions.add(question);
		}
		
		return Response.ok().build();
	}
	
	
	@POST
	@Path("/savec")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response saveChoices(Choice[] newChoices) throws URISyntaxException {
		choices = new ArrayList<Choice>();
		for (Choice choice : newChoices) {
			choices.add(choice);
		}

		return Response.ok().build();
	}
	
	
	@GET
	@Path("/save")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response createQuestion(@QueryParam("nick") String userNick, @QueryParam("name") String quizName) throws URISyntaxException {
		boolean result = false;
		User newUser = new User();
		
		if (choices != null && questions != null) {
			newUser.setNick(userNick);
			quizDS.CreateQuiz(newUser, quizName, questions, choices);
			questions = null;
			choices = null;
			result = true;
		}
		
		
		return Response.ok(result).build();
	}
	
	
	@GET
	@Path("/delete")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response deleteQuiz(@QueryParam("nick") String userNick, @QueryParam("name") String quizName) throws URISyntaxException {
		boolean result = false;

		Quiz criteria = new Quiz();
		criteria.setName(quizName);
		List<Quiz> list = quizDS.GetAllQuizLike(criteria);
		
		
		if (list.size() == 1 && list.get(0).getUser().getNick().equals(userNick)) {
			quizDS.RemoveQuiz(list.get(0));
			result = true;
		}
		
		
		
		return Response.ok(result).build();
	}
}