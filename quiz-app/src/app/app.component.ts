import { Component } from '@angular/core';
import { QuestionService } from './services/question.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  newUrl: string = ''
  title = 'Quizmania';

  constructor(private questionService: QuestionService) {

  }

  update() {
    this.questionService.setBaseLinkURL(this.newUrl)
  }

  reset() {
    this.questionService.setBaseLinkURL('http://localhost:8080/quiz-rest-services/rest/quiz/')
  }
}
