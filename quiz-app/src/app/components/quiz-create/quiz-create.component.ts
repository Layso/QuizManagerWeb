import { Component, OnInit } from '@angular/core';
import { Question } from '../../datamodel/question';
import { QuestionService } from '../../services/question.service';
import { Choice } from '../../datamodel/choice';

@Component({
  selector: 'app-quiz-create',
  templateUrl: './quiz-create.component.html',
  styleUrls: ['./quiz-create.component.css']
})

export class QuizCreateComponent implements OnInit {
  questions: Question[] = []
  choices: Choice[] = []
  quizName: string = ''
  userNick: string = ''
  choice1: string = ''
  choice2: string = ''
  choice3: string = ''
  choice4: string = ''
  questionContent: string = ''
  informationText: string = ''


  constructor(private questionService: QuestionService) { }

  ngOnInit() {

  }

  saveQuestion() {
    if (this.questionContent == '' || this.choice1 == '' || this.choice2 == '' || this.choice3 == '' || this.choice4 == '') {
      this.informationText = 'Please fill all information to save question'
    } else {
      let question = new Question(0, this.questionContent)
      this.questions.push(question)
      this.choices.push(new Choice(0, this.choice1, true, null))
      this.choices.push(new Choice(0, this.choice2, false, null))
      this.choices.push(new Choice(0, this.choice3, false, null))
      this.choices.push(new Choice(0, this.choice4, false, null))
      this.informationText = ''
      this.clearQuestion()
    }
  }


  saveQuiz() {
    let unique = false

    if (this.quizName != '') {
      this.questionService.getQuizList(this.quizName).then(data => {
        console.log(data)

        if (data.length != 0  || this.userNick == '' || this.questions.length == 0) {
          this.informationText = 'Please fill all information (with unique quiz name) and save a question to save quiz'
        } else {
          this.informationText = ''
          this.questionService.saveQuestions(this.questions).then((data) => {
            this.questionService.saveChoices(this.choices).then((data) => {
              this.questionService.saveQuiz(this.userNick, this.quizName).then((data) => {
                this.clearQuiz()
                this.clearQuestion()
              }).catch(error => {
                this.informationText = 'Please update base URL for REST service'
              })
            }).catch(error => {
              this.informationText = 'Please update base URL for REST service'
            })
          }).catch(error => {
            this.informationText = 'Please update base URL for REST service'
          })
        }
      }).catch(error => {
        this.informationText = 'Please update base URL for REST service'
      })
    } else {
      this.informationText = 'Please fill all information (with unique quiz name) and save a question to save quiz'
    }
  }


  clearQuiz() {
    this.quizName = ''
    this.userNick = ''
    this.questions = []
    this.choices = []
  }

  
  clearQuestion() {
    this.questionContent = ''
    this.choice1 = ''
    this.choice2 = ''
    this.choice3 = ''
    this.choice4 = ''
  }
}
