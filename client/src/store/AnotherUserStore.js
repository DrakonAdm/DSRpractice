import {makeAutoObservable} from "mobx";

export default class AnotherUserStore {
    constructor() {
        this._anotherUser = {
            id: 2,
            name: 'Витя',
            surname: 'Новиков',
            date: '2001-11-27',
            phone: '8800',
            city: 'Воронеж',
            country: 'Россия',
            description: 'Всё Хорошо',
            isFriend: false,
        };
        makeAutoObservable(this)
    }

    setAnotherUser(anotherUser) {
        this._anotherUser = anotherUser
    }

    get anotherUser() {
        return this._anotherUser
    }
}