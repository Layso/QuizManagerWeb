import { Question } from "./question";

export class Choice {
    id: number;
    text: string;
    valid: boolean;
    question: Question;


    constructor(newId: number, newText: string, newValid: boolean, newQuestion: Question) {
        this.id = newId;
        this.text = newText;
        this.valid = newValid;
        this.question = newQuestion;
    }
}