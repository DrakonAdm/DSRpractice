import {makeAutoObservable} from "mobx";

export default class UserStore {
    constructor() {
        this._isAuth = false
        this._user = {}
        makeAutoObservable(this)
    }

// {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27', username: 'email', phone: '8800',
//     city: 'Воронеж', country: 'Россия', description: 'Всё Хорошо', password: 'пароль'}

    setIsAuth(bool) {
        this._isAuth = bool
    }
    setUser(user) {
        this._user = user
    }

    get isAuth() {
        return this._isAuth
    }
    get user() {
        return this._user
    }
}