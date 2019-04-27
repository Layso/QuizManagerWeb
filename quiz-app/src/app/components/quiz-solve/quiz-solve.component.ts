import { Component, OnInit } from '@angular/core';
import { QuestionService } from "../../services/question.service";
import { Question } from '../../datamodel/question';
import { Choice } from '../../datamodel/choice';

@Component({
  selector: 'app-quiz-solve',
  templateUrl: './quiz-solve.component.html',
  styleUrls: ['./quiz-solve.component.css']
})
export class QuizSolveComponent implements OnInit {
  quizName: string
  currentQuestion: number
  correctAnswerCount: number
  questions: Question[]
  choices: Choice[]

  choiceDisplay1: Choice = new Choice(0, '', false, null)
  choiceDisplay2: Choice = new Choice(0, '', false, null)
  choiceDisplay3: Choice = new Choice(0, '', false, null)
  choiceDisplay4: Choice = new Choice(0, '', false, null)
  questionDisplay: Question = new Question(0, '')
  infoDisplay: string = ''
  totalQuestionDisplay: number
  currentQuestionDisplay: number

  constructor(private questionService: QuestionService) { }

  ngOnInit() {
    this.questionService.setQuizSolveComponent(this)
  }


  prepareQuiz(newQuizName: string) {
    this.quizName = newQuizName
    this.currentQuestion = 0
    this.questions = []
    this.choices = []
    
    this.questionService.getQuestionForQuiz(newQuizName).then((data) => {
      data.forEach(question => {
        this.questions.push(question)
        this.questionService.getChoicesForQuestion(question).then((data) => {
          data.forEach(choice => {
            this.choices.push(choice)
          })
        }).then(data => {
          if (this.questions.length * 4 == this.choices.length) {
            this.infoDisplay = ''
            this.correctAnswerCount = 0
            this.currentQuestionDisplay = 0
            this.totalQuestionDisplay = this.questions.length
            this.setDisplay()
          }
        }).catch(error => {
          this.infoDisplay = 'Please update base URL for REST service'
        })
      })
    }).catch(error => {
      this.infoDisplay = 'Please update base URL for REST service'
    })
  }

  setDisplay() {
    this.currentQuestionDisplay++
    this.questionDisplay = this.questions[this.currentQuestion]
    let choiceList = this.getChoicesForQuestion(this.questionDisplay) 

    this.choiceDisplay1 = this.pickRandomChoice(choiceList)
    this.choiceDisplay2 = this.pickRandomChoice(choiceList)
    this.choiceDisplay3 = this.pickRandomChoice(choiceList)
    this.choiceDisplay4 = this.pickRandomChoice(choiceList)
  }

  pickRandomChoice(list: Choice[]): Choice {
    let randomIndex = Math.floor(Math.random() * list.length)
    let result = list[randomIndex]
    list.splice(randomIndex, 1)
    
    return result
  }

  getChoicesForQuestion(question: Question): Choice[] {
    let result = []

    this.choices.forEach (choice => {
      if (choice.question.id == question.id)
        result.push(choice)
    })
    
    return result
  }
  

  selectChoice(choice: string) {
    if (choice != '') {
      // Correction check
      let correctChoice = this.getCorrectChoice()
      if (correctChoice.text == choice) {
        this.correctAnswerCount++
        this.infoDisplay = 'Correct answer!'
      } else {
        this.infoDisplay = 'Correct answer was: ' + correctChoice.text
      }
      
      // Quiz end check
      if (++this.currentQuestion == this.questions.length) {
        this.choiceDisplay1 = new Choice(0, '', false, null)
        this.choiceDisplay2 = new Choice(0, '', false, null)
        this.choiceDisplay3 = new Choice(0, '', false, null)
        this.choiceDisplay4 = new Choice(0, '', false, null)
        this.questionDisplay = new Question(0, '')
        this.infoDisplay = 'Quiz finished:' + this.correctAnswerCount + ' correct answer'
      } else {

        this.setDisplay()
      }
    }
  }

  getCorrectChoice(): Choice {
    let result = null
    this.getChoicesForQuestion(this.questionDisplay).forEach(choice => {
      if (choice.valid)
        result = choice
    })

    return result
  }
}
