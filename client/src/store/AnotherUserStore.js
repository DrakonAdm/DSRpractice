import {makeAutoObservable} from "mobx";

export default class AnotherUserStore {
    constructor() {
        this._anotherUser = []
        makeAutoObservable(this)
    }

    setAnotherUser(anotherUser) {
        this._anotherUser = anotherUser
    }

    get anotherUser() {
        return this._anotherUser
    }
}