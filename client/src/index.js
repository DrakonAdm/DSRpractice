import React, {createContext} from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import UserStore from "./store/UserStore";
import ListStore from "./store/ListStore";
import AnotherProfile from "./pages/person/AnotherProfile";
import AnotherUserStore from "./store/AnotherUserStore";

export const Context = createContext(null)

ReactDOM.render(
    <Context.Provider value={{
        user: new UserStore(),
        lists: new ListStore(),
        anotherUser: new AnotherUserStore()
    }}>
        <App />
    </Context.Provider>,
    document.getElementById('root')
);
