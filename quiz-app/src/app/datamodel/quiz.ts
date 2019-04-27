import { User } from "./user";

export class Quiz {
    id: number;
    name: string;
    user: User;

    constructor(newId: number, newName: string, newUser: User) {
        this.id = newId;
        this.name = newName;
        this.user = newUser;
    }
}