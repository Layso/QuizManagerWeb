import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { QuizListComponent } from './components/quiz-list/quiz-list.component';
import { QuizSolveComponent } from './components/quiz-solve/quiz-solve.component';
import { QuizCreateComponent } from './components/quiz-create/quiz-create.component';
import { QuizDeleteComponent } from './components/quiz-delete/quiz-delete.component';

@NgModule({
  declarations: [
    AppComponent,
    QuizListComponent,
    QuizSolveComponent,
    QuizCreateComponent,
    QuizDeleteComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
