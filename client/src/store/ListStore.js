import {makeAutoObservable} from "mobx";
import listFriend from "../pages/person/ListFriend";
import listInvite from "../pages/person/ListInvite";
import listMessage from "../pages/message/ListMessage";

export default class ListStore {
    constructor() {
        this._listFriend = [
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
        ]

        this._listInvite = [
            {id: 1, personId: 4, name: 'Женя', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, personId: 6, name: 'Моня', surname: 'Кириленко', date: '2004-9-15'},
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
            {id: 1, name: 'Кирилл', surname: 'Смородинов', date: '2002-11-27'},
            {id: 2, name: 'Иван', surname: 'Кириленко', date: '2004-9-15'},
        ]

        this._listMessage = [
            {id: 1, chatId: 4, senderId: 6, recipientId: 8, name: 'Женя', surname: 'Смородинов', date: '2002-11-27'},
        ]

        this._listSearch = [
            {id: 1, name: 'Петр', surname: 'Кудинов', date: 2002-11-27},
            {id: 2, name: 'Костя', surname: 'Кириленко', date: 2004-9-15}
        ]
        makeAutoObservable(this)
    }


    get listFriend() {
        return this._listFriend;
    }

    setListFriend(listFriend) {
        this._listFriend = listFriend;
    }

    get listInvite() {
        return this._listInvite;
    }

    setListInvite(listInvite) {
        this._listInvite = listInvite;
    }

    get listMessage() {
        return this._listMessage;
    }

    setListMessage(listMessage) {
        this._listMessage = listMessage;
    }


    get listSearch() {
        return this._listSearch;
    }

    setListSearch(value) {
        this._listSearch = value;
    }
}