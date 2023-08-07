import React, {useContext} from 'react';
import {Context} from "../../index";
import {Navigate} from "react-router-dom";
import {AUTH_ROUTE} from "../../utils/consts";

const Search = () => {
    const { user, list } = useContext(Context);

    if (!user.isAuth){
        return <Navigate to={AUTH_ROUTE} replace/>
    }

    return (
        <div>
            Поиск друзей
        </div>
    );
};

export default Search;