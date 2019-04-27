export class User {
    id: number;
    nick: string;

    constructor(newId: number, newNick: string) {
        this.id = newId;
        this.nick = newNick;
    }
}