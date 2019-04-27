export class Question {
    id: number;
    content: string;

    constructor(newId: number, newContent: string) {
        this.id = newId;
        this.content = newContent;
    }
}