import React, {useContext} from 'react';
import {authRoutes, publicRoutes} from "../routes";
import {AUTH_ROUTE} from "../utils/consts";
import {Route, Routes, Navigate, BrowserRouter} from "react-router-dom";
import {Context} from "../index";

const AppRouter = () => {
    const {user} = useContext(Context)

    console.log(user)
    return (
        <Routes>
            { authRoutes.map(({path, Component}) => <Route key={path} path={path} element={<Component />} exact/>
            )}
            { publicRoutes.map(({path, Component}) => <Route key={path} path={path} element={<Component />} exact/>
            )}
            <Route path="/*" element={<Navigate replace to={AUTH_ROUTE} />} />
        </Routes>
    );
};

export default AppRouter;