import { Injectable } from '@angular/core';
import { Question } from '../datamodel/question';
import { Quiz } from '../datamodel/quiz'
import { HttpClient } from '@angular/common/http';
import { Choice } from '../datamodel/choice';
import { QuizSolveComponent } from '../components/quiz-solve/quiz-solve.component'


@Injectable({
  providedIn: 'root'
})

export class QuestionService {
  quizSolveComponent: QuizSolveComponent

  

  questionList: Question[] = []
  quizList: Quiz[] = []
  choiceList: Choice[] = []
  baseLink:string = "http://localhost:8080/quiz-rest-services/rest/quiz/"
  quizSearchLink: string = "search?name="
  questionGetLink: string = "getq?name="
  choiceGetLink: string = "getc"
  questionSaveLink: string = "saveq"
  choiceSaveLink: string = "savec"
  quizSaveLink: string = "save?"
  quizDeleteLink: string = "delete?"


  constructor(private http: HttpClient) {
    
  }

  
  
  getQuizList(name: string): Promise<Quiz[]> {
    let link = this.baseLink + this.quizSearchLink + name
    return this.http.get<Quiz[]>(link).toPromise()
  }



  public getQuestionForQuiz(quizName: string): Promise<Question[]> {
    let link = this.baseLink + this.questionGetLink + quizName
    return this.http.get<Question[]>(link).toPromise()
  }
  

  saveQuestions(questions: Question[]): Promise<Object> {
    return this.http.post(this.baseLink + this.questionSaveLink, questions).toPromise()
  }


  saveChoices(choices: Choice[]): Promise<Object> {
    return this.http.post(this.baseLink + this.choiceSaveLink, choices).toPromise()
  }


  saveQuiz(userNick: string, quizName: string): Promise<Boolean> {
    let link = this.baseLink + this.quizSaveLink + 'nick=' + userNick + '&name=' + quizName
    return this.http.get<Boolean>(link).toPromise()
  }


  deleteQuiz(userNick: string, quizName: string): Promise<Boolean> {
    let link = this.baseLink + this.quizDeleteLink + 'nick=' + userNick + '&name=' + quizName
    return this.http.get<Boolean>(link).toPromise()
  }

  getChoicesForQuestion(question: Question): Promise<Choice[]> {
    return this.http.post<Choice[]>(this.baseLink + this.choiceGetLink, question).toPromise()
  }



  solveQuiz(quizName: string) {
    this.quizSolveComponent.prepareQuiz(quizName)
  }


  setQuizSolveComponent(component: QuizSolveComponent) {
    this.quizSolveComponent = component
  }

  setBaseLinkURL(url: string) {
    this.baseLink = url
  }
}