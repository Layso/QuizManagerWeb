import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../../services/question.service'


@Component({
  selector: 'app-quiz-delete',
  templateUrl: './quiz-delete.component.html',
  styleUrls: ['./quiz-delete.component.css']
})
export class QuizDeleteComponent implements OnInit {
  userNick: string = ''
  quizName: string = ''
  infoText: string = ''


  constructor(private questionService: QuestionService) { }

  ngOnInit() {
  }

  delete() {
    this.questionService.deleteQuiz(this.userNick, this.quizName).then(data => {
      if (data) {
        this.infoText = 'Delete ' + this.quizName + ' created by: ' + this.userNick
      } else {
        this.infoText = 'No quiz named ' + this.quizName + ' found created by: ' + this.userNick + ' to delete'
      }
    }).catch(error => {
      this.infoText = 'Please update base URL for REST service'
    })
  }
}
