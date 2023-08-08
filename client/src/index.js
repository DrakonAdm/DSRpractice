import React, {createContext} from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import UserStore from "./store/UserStore";
import AnotherUserStore from "./store/AnotherUserStore";

export const Context = createContext(null)

ReactDOM.render(
    <Context.Provider value={{
        user: new UserStore(),
        anotherUser: new AnotherUserStore()
    }}>
        <App />
    </Context.Provider>,
    document.getElementById('root')
);
