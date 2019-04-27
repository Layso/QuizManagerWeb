import { Component, OnInit } from '@angular/core';
import { Quiz } from '../../datamodel/quiz';
import { QuestionService } from '../../services/question.service';

@Component({
  selector: 'app-quiz-list',
  templateUrl: './quiz-list.component.html',
  styleUrls: ['./quiz-list.component.css']
})
export class QuizListComponent implements OnInit {
  quizList: Quiz[] = []
  quizName: string = ''
  infoText: string = ''


  constructor(private questionService : QuestionService) { }

  ngOnInit() {
    this.search()
  }

  search() {
    this.questionService.getQuizList(this.quizName).then(data => {
      this.quizList = []
      data.forEach (quiz => {
        this.quizList.push(quiz)
      })
      this.infoText = this.quizList.length + ' quiz found to display'
    }).catch(error => {
      this.infoText = 'Please update base URL for REST service'
    })
  }

  solveQuiz(quizName: string) {
    this.questionService.solveQuiz(quizName)
  }
}
